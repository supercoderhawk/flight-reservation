package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.bean.Order;
import com.airline.utils.Constant;
import com.airline.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by airline on 2017/5/14.
 * 订单业务单元测试类
 */
public class OrderServiceTest {
  @Test
  public void unsubscribeFlight() throws Exception {

  }

  private OrderService orderService;
  private FlightService flightService;
  private DataSource dataSource;
  private OperationResult<Order> orderRes;
  private Flight flight = new Flight("BJ3001", "112", "19:00:00", "22:00:00", "Beijing", "Hangzhou", "2017-06-13", 10, 10);

  @Test
  public void unsubscribleFlight() throws Exception {
    Order order = new Order();
    order.setPassengerID(12345678);
    order.setFlightSerial("2");
    flightService.publishAllFlights();
    // 同用户同航班多个订单不指定订单号
    orderRes = orderService.unsubscribeFlight(order);
    assertEquals(orderRes.isStatus(), false);
    order.setOrderID(22L);
    orderRes = orderService.unsubscribeFlight(order);
    assertEquals(orderRes.isStatus(), true);
    // 验证航班满客后退订恢复为可预订
    ArrayList<Flight> res1 = dataSource.getFlights().stream().filter(f -> f.getFlightSerial().equals("2"))
        .collect(Collectors.toCollection(ArrayList::new));
    assertEquals(res1.size(),1);
    System.out.println(res1.get(0).getFreeSeats());
    assertEquals(res1.get(0).getFlightStatus(), Constant.FlightStatus.AVAILABLE);
    order.setOrderID(null);
    orderRes = orderService.unsubscribeFlight(order);
    assertEquals(orderRes.isStatus(), true);
  }

  @Before
  public void setUp() throws Exception {
    dataSource = Util.loadFileToObject("init.json", DataSource.class);
    flightService = new FlightService(dataSource);
    PassengerService passengerService = new PassengerService(dataSource);
    orderService = new OrderService(dataSource, flightService, passengerService);
    for (Flight flight : dataSource.getFlights()) {
      if (flight.getCurrentPassengers().equals(flight.getSeatCapacity())) {
        flight.setFlightStatus(Constant.FlightStatus.FULL);
      } else {
        Util.addSeats(flight.getFreeSeats(), flight.getSeatCapacity() - flight.getCurrentPassengers());
      }

      if (StringUtils.isEmpty(flight.getArrivalDate())) {
        flight.setArrivalDate(flight.getDepartureDate());
      }
    }
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
    Order order1 = new Order();
    order1.setPassengerID(12345678);
    order1.setFlightSerial("1");
    flightService.publishAllFlights();
    // 验证预定后航班状态自动变为FULL
    orderRes = orderService.reserveTicket(order1);
    assertEquals(orderRes.isStatus(), true);
    ArrayList<Flight> res1 = dataSource.getFlights().stream().filter(f -> f.getFlightSerial().equals("1"))
        .collect(Collectors.toCollection(ArrayList::new));
    assertEquals(res1.size(), 1);
    assertEquals(res1.get(0).getFlightStatus(), Constant.FlightStatus.FULL);
    // 验证订满后无法再订
    orderRes = orderService.reserveTicket(order1);
    assertEquals(orderRes.isStatus(), false);
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

    assertEquals(orderRes.isStatus(), true);
    assertEquals(orderRes.getData().getOrderStatus(), Constant.OrderStatus.PAID);
  }
}