package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.OperationResult;
import com.airline.bean.Passenger;
import com.airline.utils.Util;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by airline on 2017/5/14.
 * 乘客业务单元测试类
 */
public class PassengerServiceTest {
  private Passenger passenger;
  private PassengerService passengerService;
  OperationResult<Passenger> res;

  @Before
  public void setUp() throws Exception {
    passenger = new Passenger("xyb", "88888888", Util.encrypt("123456"));
    DataSource dataSource = Util.loadFileToObject("init.json", DataSource.class);
    passengerService = new PassengerService(dataSource);
  }

  @Test
  public void registerPassenger() throws Exception {
    res = passengerService.registerPassenger(passenger);
    assertEquals(res.isStatus(), true);
    passenger = new Passenger("aaa", "12345678", Util.encrypt("123"));
    res = passengerService.registerPassenger(passenger);
    assertEquals(res.isStatus(), false);
    passenger.setIdentityID("");
    res = passengerService.registerPassenger(passenger);
    assertEquals(res.isStatus(), false);
    passenger.setIdentityID("12345678");
    passenger.setPassword("");
    res = passengerService.registerPassenger(passenger);
    assertEquals(res.isStatus(), false);
  }

  @Test
  public void login() throws Exception {
    passenger = new Passenger("xyb", "12345678", Util.encrypt("123456"));
    res = passengerService.login(passenger);
    assertEquals(res.isStatus(),true);
  }

  @Test
  public void addOrderToPassenger() throws Exception {
  }

}