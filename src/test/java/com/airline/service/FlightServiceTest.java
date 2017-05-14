package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.utils.util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.airline.utils.Constant.reply;
import static org.junit.Assert.assertEquals;

/**
 * Created by airline on 2017/5/11.
 * 航班业务类测试文件
 */
public class FlightServiceTest {

  private FlightService flightService;
  private Flight flight;

  @Before
  public void setUp() throws Exception {
    DataSource dataSource = util.loadFileToObject("init.json", DataSource.class);
    flightService = new FlightService(dataSource);
    flight = new Flight("BJ1001", "AAA170512-BJ1001", "10:00:00", "12:00:00", "Beijing", "Hangzhou", "2017-05-13", 10, 10);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void createFlight() throws Exception {
    OperationResult<Flight> res = flightService.createFlight(flight);
    assertEquals(res.isStatus(), false);
    flight.setFlightSerial("AAA170514-BJ3001");
    res = flightService.createFlight(flight);
    assertEquals(res.isStatus(), true);
    Flight tmpFlight = new Flight();
    res = flightService.createFlight(tmpFlight);
    assertEquals(res.isStatus(),false);
    assertEquals(res.getMsg(),reply.getFlightFlightIDEmpty());
    tmpFlight.setFlightID("BJ1234");
    tmpFlight.setFlightSerial("AAA123-BJ1234");
    res = flightService.createFlight(tmpFlight);
    assertEquals(res.isStatus(),false);
    //assertEquals(res);
    System.out.println(res.getMsg());
  }

  @Test
  public void publishFlight() throws Exception {
    OperationResult<Flight> res = flightService.publishFlight("BJ1001");
    assertEquals(res.isStatus(), false);
    assertEquals(res.getMsg(), reply.getFlightNoFlight());
    res = flightService.publishFlight("AAA170512-BJ1001");
    assertEquals(res.isStatus(), true);
    res = flightService.publishFlight("AAA170512-BJ1001");
    assertEquals(res.isStatus(), false);

  }

  @Test
  public void updateFlight() throws Exception {
  }

  @Test
  public void deleteFlight() throws Exception {
  }

}