package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.bean.Order;
import com.airline.utils.Constant;
import com.airline.utils.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by airline on 2017/5/14.
 * 订单业务单元测试类
 */
public class OrderServiceTest {
  private OrderService orderService;
  private FlightService flightService;
  private DataSource dataSource;
  private OperationResult<Order> orderRes;
  private Flight flight = new Flight("BJ3001", "2", "19:00:00", "22:00:00", "Beijing", "Hangzhou", "2017-06-13", 10, 10);

  @Test
  public void unsubscribleFlight() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    dataSource = Util.loadFileToObject("init.json", DataSource.class);
    flightService = new FlightService(dataSource);
    PassengerService passengerService = new PassengerService(dataSource);
    orderService = new OrderService(dataSource, flightService, passengerService);

  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void reserveTicket() throws Exception {
    Order order = new Order();
    order.setFlightSerial(flight.getFlightSerial());
    order.setPassengerID(12345678);
    orderRes = orderService.reserveTicket(order);
    assertEquals(orderRes.isStatus(), false);
    OperationResult<Flight> res = flightService.createFlight(flight);
    assertEquals(res.isStatus(), true);
    if (!res.isStatus()) {
      return;
    }
    res = flightService.publishFlight(flight.getFlightSerial());
    assertEquals(res.isStatus(), true);
    if (!res.isStatus()) {
      return;
    }
    ArrayList<Order> orders = dataSource.getPassengers().get(0).getOrderList();
    int count = orders.size();
    flightService.publishFlight(flight.getFlightSerial());
    orderRes = orderService.reserveTicket(order);
    assertEquals(orderRes.isStatus(), true);
    assertEquals(orderRes.getData().getOrderStatus(), Constant.OrderStatus.UNPAID);
    assertEquals(orders.size(), count + 1);
  }

  @Test
  public void payOrder() throws Exception {
    Order order = new Order();
    order.setFlightSerial(flight.getFlightSerial());
    order.setPassengerID(12345678);
    OperationResult<Flight> res = flightService.createFlight(flight);
    assertEquals(res.isStatus(), true);
    if (!res.isStatus()) {
      return;
    }
    res = flightService.publishFlight(flight.getFlightSerial());
    assertEquals(res.isStatus(), true);
    if (!res.isStatus()) {
      return;
    }
    orderRes = orderService.reserveTicket(order);
    order = orderRes.getData();
    order.setOrderStatus(Constant.OrderStatus.PAID);
    orderRes = orderService.payOrder(order);
    Order order2 = new Order();
    order2.setFlightSerial(flight.getFlightSerial());
    order2.setPassengerID(12345678);

    assertEquals(orderRes.isStatus(),true);
    assertEquals(orderRes.getData().getOrderStatus(), Constant.OrderStatus.PAID);
  }

}