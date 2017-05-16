package com.airline.aspectj;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.bean.Order;
import com.airline.bean.Passenger;
import com.airline.service.OrderService;
import com.airline.utils.Constant;
import com.airline.utils.Constant.FlightStatus;
import com.airline.utils.Operation;
import com.airline.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.airline.utils.Constant.flightStatusMap;
import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/13.
 * 订单业务横切类
 */
@Aspect
public class OrderAspect {
  @Pointcut("execution(* com.airline.service.OrderService.reserveTicket(com.airline.bean.Order)) && args(order)")
  public void validateOrderAtReservePointcut(Order order) {
  }

  @Before("validateOrderAtReservePointcut(com.airline.bean.Order) && args(order)")
  public void validateOrderAtReserve(JoinPoint joinPoint, Order order) {
    DataSource dataSource = ((OrderService) joinPoint.getTarget()).getDataSource();
    if (order == null) {
      dataSource.setOrderCheck(Operation.fail(reply.getGlobalParameterEmpty()));
      return;
    }
    order.setOrderStatus(Constant.OrderStatus.UNPAID);
    OperationResult<Flight> res = isFlightExist(dataSource, order.getFlightSerial());
    if (!res.isStatus()) {
      dataSource.setOrderCheck(Operation.fail(reply.getFlightNoFlight()));
      return;
    }
    Flight flight = res.getData();
    if (flight.getFlightStatus() != FlightStatus.AVAILABLE) {
      String str = String.format(reply.getOrderFlightCantReserve(), flightStatusMap.get(flight.getFlightStatus()));
      dataSource.setOrderCheck(Operation.fail(str));
      order.setOrderStatus(Constant.OrderStatus.FAIL);
      return;
    }
    OperationResult<Passenger> passRes = isPassengerExist(dataSource, order.getPassengerID());
    if (!passRes.isStatus()) {
      dataSource.setOrderCheck(Operation.fail(passRes.getMsg()));
      return;
    }
    String seat = flight.getFreeSeats().get(0);
    order.setSeat(seat);
    flight.getSeatArrange().put(seat, passRes.getData().getPassengerID());
    LocalDateTime time = LocalDateTime.now();
    order.setCreateDate(time);
    order.setOrderID(Util.generateOrderID(time));
    dataSource.setOrderCheck(Operation.success(flight));
  }

  @Pointcut("execution(* com.airline.service.OrderService.payOrder(com.airline.bean.Order)) && args(order)")
  public void payOrderPointcut(Order order) {
  }

  @Before("payOrderPointcut(com.airline.bean.Order) && args(order)")
  public void validateOrderBeforePay(JoinPoint joinPoint, Order order) {
    DataSource dataSource = ((OrderService) joinPoint.getTarget()).getDataSource();
    OperationResult<Flight> res = isFlightExist(dataSource, order.getFlightSerial());
    if (!res.isStatus()) {
      dataSource.setOrderCheck(Operation.fail(reply.getFlightNoFlight()));
      return;
    }
    Flight flight = res.getData();

    dataSource.setOrderCheck(Operation.success(flight));
  }

  @Pointcut("execution(* com.airline.service.OrderService.unsubscribeFlight(com.airline.bean.Order)) && args(order)")
  public void unsubscribePointcut(Order order) {
  }

  /**
   * <p>在退订前对订单进行检查</p>
   * <p><b>只有未支付和已支付订单可以退订</b></p>
   * <p>要求：</p>
   * <ol>
   * <li>航班序列号和乘客ID不能为空</li>
   * <li>如果乘客在同一航班下有多个可退订订单，必须指定退订的订单号</li>
   * </ol>
   *
   * @param joinPoint: 连接点对象
   * @param order:     订单对象
   */
  @Before("unsubscribePointcut(com.airline.bean.Order) && args(order)")
  public void validateOrderBeforeUnsubscribe(JoinPoint joinPoint, Order order) {
    DataSource dataSource = ((OrderService) joinPoint.getTarget()).getDataSource();
    if (order == null || StringUtils.isEmpty(order.getFlightSerial()) || order.getPassengerID() == null) {
      dataSource.setCancelOrderCheck(Operation.fail(reply.getGlobalParameterEmpty()));
      return;
    }
    ArrayList<Order> orders = dataSource.getOrders().stream()
        .filter(o->o.getFlightSerial().equals(order.getFlightSerial()))
        .filter(o -> o.getPassengerID().equals(order.getPassengerID()))
        .filter(o -> o.getOrderStatus() == Constant.OrderStatus.UNPAID || o.getOrderStatus() == Constant.OrderStatus.PAID)
        .collect(Collectors.toCollection(ArrayList::new));
    if (orders.size() == 0) {
      dataSource.setCancelOrderCheck(Operation.fail(reply.getOrderNoOrdersToUnsubscribe()));
      return;
    }

    // 同一乘客同一航班多订单
    if (orders.size() > 1) {
      if (order.getOrderID() == null) {
        dataSource.setCancelOrderCheck(Operation.fail(reply.getOrderNoOrderID()));
        return;
      }
      orders = orders.stream().filter(o -> o.getOrderID().equals(order.getOrderID()))
          .collect(Collectors.toCollection(ArrayList::new));
      if (orders.size() == 0) {
        dataSource.setCancelOrderCheck(Operation.fail(reply.getOrderInvalidateIDToPassenger()));
        return;
      }
    }
    dataSource.setCancelOrderCheck(Operation.success(orders.get(0)));
  }

  @Pointcut("execution(* *.*SWITCH_TABLE*(..)) || execution(* *.*lambda*(..))")
  public void excludeMethod() {
  }

  @Pointcut("execution(* com.airline.service.OrderService.*(com.airline.bean.Order)) && args(order) && !excludeMethod()")
  public void allMethod(Order order) {
  }

  /**
   * <p>对所有的订单操作完整后进行检查，确保：</p>
   * <ol>
   *   <li>航班订满（没有空座）后转变为订满状态</li>
   *   <li>订满并退订后变为可预订状态</li>
   * </ol>
   * @param joinPoint
   * @param order
   */
  @After("allMethod(com.airline.bean.Order) && args(order)")
  public void processAfterPaid(JoinPoint joinPoint, Order order) {
    DataSource dataSource = ((OrderService) joinPoint.getTarget()).getDataSource();
    OperationResult<Flight> res = isFlightExist(dataSource, order.getFlightSerial());
    if (!res.isStatus()) {
      return;
    }
    Flight flight = res.getData();
    if (flight.getFreeSeats().size() == 0) {
      flight.setFlightStatus(FlightStatus.FULL);
    }else if(flight.getFreeSeats().size() >0 && flight.getFlightStatus() == FlightStatus.FULL){
      flight.setFlightStatus(FlightStatus.AVAILABLE);
    }
  }

  private static OperationResult<Flight> isFlightExist(DataSource dataSource, String flightSerial) {
    for (Flight flight : dataSource.getFlights()) {
      if (flight.getFlightSerial().equals(flightSerial)) {
        return Operation.success(flight);
      }
    }
    return Operation.fail(reply.getFlightNoFlight());
  }

  private static OperationResult<Passenger> isPassengerExist(DataSource dataSource, Integer passengerID) {
    if (passengerID == null) {
      return Operation.fail(reply.getPassengerNameEmpty());
    }
    for (Passenger passenger : dataSource.getPassengers()) {
      if (passenger.getPassengerID().equals(passengerID)) {
        return Operation.success(passenger);
      }
    }
    return Operation.fail(reply.getPassengerNotExist());
  }

}
