package com.airline.dao;

import com.airline.DataSource;
import com.airline.bean.Flight;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by airline on 2017/5/10.
 * 存取航班数据
 */
public class FlightDao extends BaseDao {
  public FlightDao(DataSource dataSource) {
    super(dataSource);
  }

  protected void addFlight(Flight flight) {
    dataSource.getFlights().add(new Flight(flight));
  }

  protected ArrayList<Flight> getFlightByID(String flightID) {

    return dataSource.getFlights().stream().filter(f -> f.getFlightID().equals(flightID))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  protected Flight getFlightBySerial(String serial) {
    for (Flight fight : dataSource.getFlights()) {
      if (fight.getFlightSerial().equals(serial)) {
        return fight;
      }
    }
    return null;
  }

  protected void removeFlightBySerial(String flightSerial) {
    ArrayList<Flight> flights = dataSource.getFlights();
    for (int i = 0; i < flights.size(); i++) {
      if (flights.get(i).getFlightSerial().equals(flightSerial)) {
        flights.remove(i);
        break;
      }
    }
  }

}
