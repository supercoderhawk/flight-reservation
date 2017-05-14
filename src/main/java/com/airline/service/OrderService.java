package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.bean.Order;
import com.airline.dao.OrderDao;
import com.airline.utils.Constant.OrderStatus;
import com.airline.utils.Operation;

import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/11.
 * 订单业务处理类
 */
public class OrderService extends OrderDao {
  private FlightService flightService;
  private PassengerService passengerService;

  public OrderService(DataSource dataSource, FlightService flightService, PassengerService passengerService) {
    super(dataSource);
    this.flightService = flightService;
    this.passengerService = passengerService;
  }

  public OperationResult<Order> reserveTicket(Order order) {
    OperationResult<Flight> res = dataSource.getOrderCheck();
    if (!res.isStatus()) {
      return Operation.fail(res.getMsg());
    }
    createOrder(order);
    passengerService.addOrderToPassenger(order);
    flightService.addPassengerToFlight(order);
    return Operation.success(order);
  }

  public OperationResult<Order> payOrder(Order order) {
    Order oldOrder = getOrderByID(order.getOrderID());
    if (oldOrder == null) {
      return Operation.fail(reply.getOrderIDNotExist());
    }
    if (order.getOrderStatus() == OrderStatus.PAID) {
      oldOrder.setOrderStatus(OrderStatus.PAID);
    } else if (order.getOrderStatus() == OrderStatus.CANCEL) {
      oldOrder.setOrderStatus(OrderStatus.CANCEL);
      flightService.removePassengerFromFlight(order);
      return Operation.fail(reply.getOrderPayCancel());
    }

    return Operation.success(new Order(oldOrder));
  }


}
