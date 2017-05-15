package com.airline.bean;

import com.airline.utils.Constant.FlightStatus;

/**
 * Created by airline on 2017/5/15.
 * 航班可见的公共信息
 */
public class FlightPublic {
  private String flightID;
  private String flightSerial;
  private String startTime;
  private String arrivalTime;
  private String startCity;
  private String arrivalCity;
  private String departureDate;
  private Integer price;
  private Integer currentPassengers = 0;
  private Integer seatCapacity;
  private FlightStatus flightStatus = FlightStatus.UNPUBLISHED;

  public FlightPublic() {
  }

  public FlightPublic(Flight flight) {
    flightID = flight.getFlightID();
    flightSerial = flight.getFlightSerial();
    startTime = flight.getStartTime();
    arrivalTime = flight.getArrivalTime();
    startCity = flight.getStartCity();
    arrivalCity = flight.getArrivalCity();
    departureDate = flight.getDepartureDate();
    price = flight.getPrice();
    currentPassengers = flight.getCurrentPassengers();
    seatCapacity = flight.getSeatCapacity();
    flightStatus = flight.getFlightStatus();
  }

  public String getFlightID() {
    return flightID;
  }

  public void setFlightID(String flightID) {
    this.flightID = flightID;
  }

  public String getFlightSerial() {
    return flightSerial;
  }

  public void setFlightSerial(String flightSerial) {
    this.flightSerial = flightSerial;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public String getStartCity() {
    return startCity;
  }

  public void setStartCity(String startCity) {
    this.startCity = startCity;
  }

  public String getArrivalCity() {
    return arrivalCity;
  }

  public void setArrivalCity(String arrivalCity) {
    this.arrivalCity = arrivalCity;
  }

  public String getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(String departureDate) {
    this.departureDate = departureDate;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getCurrentPassengers() {
    return currentPassengers;
  }

  public void setCurrentPassengers(Integer currentPassengers) {
    this.currentPassengers = currentPassengers;
  }

  public Integer getSeatCapacity() {
    return seatCapacity;
  }

  public void setSeatCapacity(Integer seatCapacity) {
    this.seatCapacity = seatCapacity;
  }

  public FlightStatus getFlightStatus() {
    return flightStatus;
  }

  public void setFlightStatus(FlightStatus flightStatus) {
    this.flightStatus = flightStatus;
  }
}
