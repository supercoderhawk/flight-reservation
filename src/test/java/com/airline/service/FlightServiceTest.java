package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.Result.FlightResult;
import com.airline.utils.util;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by airline on 2017/5/11.
 */
public class FlightServiceTest {
  private DataSource dataSource = null;
  private FlightService flightService = null;
  @Before
  public void setUp() throws Exception {
    //String path = getClass().getClassLoader().getResource("init.json").getFile();
    dataSource = util.loadDataFromFile("init.json");
    flightService = new FlightService(dataSource);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void createFlight() throws Exception {
    Flight flight = new Flight("BJ1001","10:00:00","12:00:00","Beijing","Hangzhou","2017-05-13",10,10);
    FlightResult res = flightService.createFlight(flight);
    assertEquals(res.isStatus(),false);
    flight.setFlightID("BJ4001");
    res = flightService.createFlight(flight);
    assertEquals(res.isStatus(),true);
    System.out.println(dataSource.getFlights().size());

  }

  @Test
  public void updateFlight() throws Exception {
  }

  @Test
  public void deleteFlight() throws Exception {
  }

}