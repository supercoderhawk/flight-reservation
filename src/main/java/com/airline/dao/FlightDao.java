package com.airline.dao;

import com.airline.DataSource;
import com.airline.bean.Flight;

/**
 * Created by airline on 2017/5/10.
 * 存取航班数据
 */
public class FlightDao extends BaseDao{
  public FlightDao(DataSource dataSource) {
    super(dataSource);
  }

  public void addFlight(Flight flight){
    dataSource.getFlights().add(new Flight(flight.getFlightID(),flight.getStartTime(),flight.getArrivalTime(),flight
        .getStartCity(),flight.getArrivalCity(),flight.getDepartureDate(),flight.getPrice(),flight.getSeatCapacity()));
  }

  public Flight getFlightByID(String flightID){
    for(Flight fight:dataSource.getFlights()){
      if(fight.getFlightID().equals(flightID)){
        return fight;
      }
    }
    return null;
  }
}
