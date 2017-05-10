package com.airline.bean;

import com.airline.utils.Constant.FlightStatus;

import java.util.ArrayList;

/**
 * Created by airline on 2017/5/10.
 * 航班信息对象
 */
public class Flight {
  private String flightID;
  private String startTime;
  private String arrivalTime;
  private String startCity;
  private String arrivalCity;
  private String departureDate;
  private int price;
  private int currentPassengers;
  private int seatCapacity;
  private FlightStatus flightStatus;
  private ArrayList<Integer> passengerIDs;

  public Flight(String flightID, String startTime, String arrivalTime, String startCity, String arrivalCity, String departureDate, int price, int seatCapacity) {
    this.flightID = flightID;
    this.startTime = startTime;
    this.arrivalTime = arrivalTime;
    this.startCity = startCity;
    this.arrivalCity = arrivalCity;
    this.departureDate = departureDate;
    this.price = price;
    this.seatCapacity = seatCapacity;
  }

  public Flight() {
  }

  public String getFlightID() {
    return flightID;
  }

  public void setFlightID(String flightID) {
    this.flightID = flightID;
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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getCurrentPassengers() {
    return currentPassengers;
  }

  public void setCurrentPassengers(int currentPassengers) {
    this.currentPassengers = currentPassengers;
  }

  public int getSeatCapacity() {
    return seatCapacity;
  }

  public void setSeatCapacity(int seatCapacity) {
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
