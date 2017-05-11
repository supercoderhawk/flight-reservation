package com.airline.bean.Result;

import com.airline.bean.Flight;

/**
 * Created by airline on 2017/5/11.
 */
public class FlightResult extends OperationResult {
  private Flight flight;

  public FlightResult(boolean status, String msg, Flight flight) {
    super(status, null, msg);
    this.flight = flight;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }
}
