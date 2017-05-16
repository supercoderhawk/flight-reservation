package com.airline.bean;

import com.airline.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
  private String arrivalDate;
  private Integer price;
  private Integer currentPassengers = 0;
  private Integer seatCapacity;
  private Constant.FlightStatus flightStatus = Constant.FlightStatus.UNPUBLISHED;
  private ArrayList<Integer> passengerIDs = new ArrayList<>();
  private Map<String, Integer> seatArrange = new HashMap<>();
  private ArrayList<String> freeSeats = new ArrayList<>();


  public Flight(String flightID, String flightSerial, String startTime, String arrivalTime, String startCity, String arrivalCity, String departureDate, Integer price, Integer seatCapacity, ArrayList<String> freeSeats) {
    this.flightID = flightID;
    this.flightSerial = flightSerial;
    this.startTime = startTime;
    this.arrivalTime = arrivalTime;
    this.startCity = startCity;
    this.arrivalCity = arrivalCity;
    this.departureDate = departureDate;
    this.price = price;
    this.seatCapacity = seatCapacity;
    this.freeSeats = freeSeats;
  }

  public Flight() {
  }

  public Flight(String flightID, String flightSerial, String startTime, String arrivalTime, String startCity, String
      arrivalCity, String departureDate, Integer price, Integer seatCapacity) {
    this.flightID = flightID;
    this.flightSerial = flightSerial;
    this.startTime = startTime;
    this.arrivalTime = arrivalTime;
    this.startCity = startCity;
    this.arrivalCity = arrivalCity;
    this.departureDate = departureDate;
    this.arrivalDate = departureDate;
    this.price = price;
    this.seatCapacity = seatCapacity;
  }

  public Flight(Flight flight) {
    this.flightID = flight.getFlightID();
    this.flightSerial = flight.getFlightSerial();
    this.startTime = flight.getStartTime();
    this.arrivalTime = flight.getArrivalTime();
    this.startCity = flight.getStartCity();
    this.arrivalCity = flight.getArrivalCity();
    this.departureDate = flight.getDepartureDate();
    this.arrivalDate = flight.getArrivalDate();
    this.price = flight.getPrice();
    this.currentPassengers = flight.getCurrentPassengers();
    this.seatCapacity = flight.getSeatCapacity();
    this.flightStatus = flight.getFlightStatus();
    this.passengerIDs = flight.getPassengerIDs();
    this.seatArrange = flight.getSeatArrange();
    this.freeSeats = flight.getFreeSeats();
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

  public Constant.FlightStatus getFlightStatus() {
    return flightStatus;
  }

  public void setFlightStatus(Constant.FlightStatus flightStatus) {
    this.flightStatus = flightStatus;
  }

  public ArrayList<Integer> getPassengerIDs() {
    return passengerIDs;
  }

  public void setPassengerIDs(ArrayList<Integer> passengerIDs) {
    this.passengerIDs = passengerIDs;
  }

  public Map<String, Integer> getSeatArrange() {
    return seatArrange;
  }

  public void setSeatArrange(Map<String, Integer> seatArrange) {
    this.seatArrange = seatArrange;
  }

  public ArrayList<String> getFreeSeats() {
    return freeSeats;
  }

  public void setFreeSeats(ArrayList<String> freeSeats) {
    this.freeSeats = freeSeats;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }
}
