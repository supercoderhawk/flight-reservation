package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.OperationResult;
import com.airline.bean.Passenger;
import com.airline.utils.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by airline on 2017/5/14.
 * 乘客业务单元测试类
 */
public class PassengerServiceTest {
  private Passenger passenger;
  private PassengerService passengerService;
  @Before
  public void setUp() throws Exception {
    passenger = new Passenger("xyb", "88888888", Util.encrypt("123456"));
    DataSource dataSource = Util.loadFileToObject("init.json", DataSource.class);
    passengerService = new PassengerService(dataSource);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void registerPassenger() throws Exception {
    OperationResult<Passenger> res = passengerService.registerPassenger(passenger);

    //System.out.println(res.getMsg());
  }

  @Test
  public void login() throws Exception {
  }

  @Test
  public void addOrderToPassenger() throws Exception {
  }

}