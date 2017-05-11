package com.airline.dao;

import com.airline.DataSource;
import com.airline.bean.Flight;

import java.util.ArrayList;

/**
 * Created by airline on 2017/5/10.
 * 存取航班数据
 */
public class FlightDao extends BaseDao{
  public FlightDao(DataSource dataSource) {
    super(dataSource);
  }

  protected void addFlight(Flight flight){
    dataSource.getFlights().add(new Flight(flight.getFlightID(),flight.getStartTime(),flight.getArrivalTime(),flight
        .getStartCity(),flight.getArrivalCity(),flight.getDepartureDate(),flight.getPrice(),flight.getSeatCapacity()));
  }

  protected Flight getFlightByID(String flightID){
    for(Flight fight:dataSource.getFlights()){
      if(fight.getFlightID().equals(flightID)){
        return fight;
      }
    }
    return null;
  }

  protected void updateFlightInfo(Flight flight){
    Flight oldFlight = getFlightByID(flight.getFlightID());
    oldFlight.setArrivalCity(flight.getArrivalCity());

  }

  protected void addFlightPassenger(){

  }

  protected void removeFlightByID(String flightID){
    ArrayList<Flight> flights = dataSource.getFlights();
    for(int i =0; i<flights.size();i++){
      if(flights.get(i).getFlightID().equals(flightID)){
        flights.remove(i);
        break;
      }
    }
  }

}
