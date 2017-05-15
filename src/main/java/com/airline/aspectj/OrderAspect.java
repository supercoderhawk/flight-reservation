package com.airline.aspectj;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.bean.Order;
import com.airline.service.OrderService;
import com.airline.utils.Constant;
import com.airline.utils.Constant.FlightStatus;
import com.airline.utils.Operation;
import com.airline.utils.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalDateTime;

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
    DataSource dataSource = ((OrderService)joinPoint.getTarget()).getDataSource();
    OperationResult<Flight> res = isFlightExist(dataSource, order.getFlightSerial());
    if (!res.isStatus()) {
      dataSource.setOrderCheck(Operation.fail(reply.getFlightNoFlight()));
      return;
    }
    Flight flight = res.getData();
    if (flight.getFlightStatus() != FlightStatus.AVAILABLE) {
      dataSource.setOrderCheck(Operation.fail(reply.getOrderFlightCantReserve()));
      return;
    }
    order.setOrderStatus(Constant.OrderStatus.UNPAID);
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
    DataSource dataSource = ((OrderService)joinPoint.getTarget()).getDataSource();
    OperationResult<Flight> res = isFlightExist(dataSource, order.getFlightSerial());
    if (!res.isStatus()) {
      dataSource.setOrderCheck(Operation.fail(reply.getFlightNoFlight()));
      return;
    }
    Flight flight = res.getData();

    dataSource.setOrderCheck(Operation.success(flight));
  }

  @After("payOrderPointcut(com.airline.bean.Order) && args(order)")
  public void processAfterPaid(JoinPoint joinPoint, Order order) {
    DataSource dataSource = ((OrderService)joinPoint.getTarget()).getDataSource();
    OperationResult<Flight> res = isFlightExist(dataSource, order.getFlightSerial());
    if (!res.isStatus()) {
      return;
    }
    Flight flight = res.getData();
    if (flight.getFreeSeats().size() == 0) {
      flight.setFlightStatus(FlightStatus.FULL);
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

}
