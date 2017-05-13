package com.airline.bean;

import com.airline.utils.Constant.FlightStatus;

import java.util.ArrayList;

/**
 * Created by airline on 2017/5/10.
 * 航班信息对象
 */
public class Flight {
  private String flightID;
  private String flightSerial;
  private String startTime;
  private String arrivalTime;
  private String startCity;
  private String arrivalCity;
  private String departureDate;
  private Integer price;
  private Integer currentPassengers;
  private Integer seatCapacity;
  private FlightStatus flightStatus;
  private ArrayList<Integer> passengerIDs;

  public Flight(String flightID, String flightSerial,String startTime, String arrivalTime, String startCity, String arrivalCity, String departureDate, Integer price, Integer seatCapacity) {
    this.flightID = flightID;
    this.flightSerial = flightSerial;
    this.startTime = startTime;
    this.arrivalTime = arrivalTime;
    this.startCity = startCity;
    this.arrivalCity = arrivalCity;
    this.departureDate = departureDate;
    this.price = price;
    this.seatCapacity = seatCapacity;
    this.flightStatus = FlightStatus.UNPUBLISHED;
    currentPassengers = 0;
    passengerIDs = new ArrayList<>();
  }

  public Flight() {
    this.flightStatus = FlightStatus.UNPUBLISHED;
    passengerIDs = new ArrayList<>();
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

  public ArrayList<Integer> getPassengerIDs() {
    return passengerIDs;
  }

  public void setPassengerIDs(ArrayList<Integer> passengerIDs) {
    this.passengerIDs = passengerIDs;
  }
}
