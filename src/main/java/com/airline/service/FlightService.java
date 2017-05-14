package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.bean.Order;
import com.airline.dao.FlightDao;
import com.airline.utils.Constant.FlightStatus;
import com.airline.utils.Constant.QueryFlightStrategy;
import com.airline.utils.Operation;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.airline.utils.Constant.flightStatusMap;
import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/10.
 * 航班业务处理类
 */
public class FlightService extends FlightDao {

  public FlightService(DataSource dataSource) {
    super(dataSource);
  }

  public OperationResult<?> createFlight(Flight flight){
    OperationResult<Object> checkRes = dataSource.getModifyFlight();
    if(!checkRes.isStatus()){
      return Operation.fail(checkRes.getMsg());
    }
    if(getFlightBySerial(flight.getFlightSerial())!=null){
     return Operation.fail(reply.getFlightFlightExisted());
    }
    addFlight(flight);
    return Operation.success(flight);
  }

  public OperationResult<?> updateFlight(Flight flight){
    OperationResult<Object> checkRes = dataSource.getModifyFlight();
    if(!checkRes.isStatus()){
      return Operation.fail(checkRes.getMsg());
    }
    Flight oldFlight = getFlightBySerial(flight.getFlightSerial());
    if(oldFlight == null){
      return Operation.fail(reply.getFlightNoFlight());
    }

    switch (oldFlight.getFlightStatus()){
      case UNPUBLISHED:
        if(flight.getStartTime() != null){
          oldFlight.setStartTime(flight.getStartTime());
        }
        if(flight.getArrivalTime() != null){
          oldFlight.setArrivalTime(flight.getArrivalTime());
        }
        if(flight.getStartCity() != null){
          oldFlight.setStartCity(flight.getStartCity());
        }
        if(flight.getArrivalCity() != null){
          oldFlight.setArrivalCity(flight.getArrivalCity());
        }
      case AVAILABLE:
        if(flight.getPrice() != null){
          oldFlight.setPrice(flight.getPrice());
        }
        if(flight.getCurrentPassengers() != null){
          oldFlight.setCurrentPassengers(flight.getCurrentPassengers());
        }
        if(flight.getSeatCapacity() != null){
          oldFlight.setSeatCapacity(flight.getSeatCapacity());
        }
        if(flight.getPassengerIDs() != null){
          oldFlight.setPassengerIDs(flight.getPassengerIDs());
        }
        break;
      default:
        break;
    }

    return Operation.success(flight);
  }

  void addPassengerToFlight(Order order){
    Flight flight = getFlightBySerial(order.getFlightSerial());
    flight.getPassengerIDs().add(order.getPassengerID());
    flight.setCurrentPassengers(flight.getCurrentPassengers()+1);
    flight.getFreeSeats().remove(order.getSeat());
  }

  void removePassengerFromFlight(Order order){
    Flight flight = getFlightBySerial(order.getFlightSerial());
    flight.getPassengerIDs().remove(order.getPassengerID());
    flight.setCurrentPassengers(flight.getCurrentPassengers()-1);
    flight.getFreeSeats().add(order.getSeat());
  }

  public void publishAllFlights(){
    ArrayList<Flight> flights = dataSource.getFlights();
    if(flights == null){
      return;
    }
    for(Flight flight:flights){
      if(flight.getFlightStatus() == FlightStatus.UNPUBLISHED){
        flight.setFlightStatus(FlightStatus.AVAILABLE);
      }
    }
  }

  public OperationResult<Flight> publishFlight(String flightSeries){
    Flight flight = getFlightBySerial(flightSeries);
    if(flight == null){
      return Operation.fail(reply.getFlightNoFlight());
    }
    if(flight.getFlightStatus() != FlightStatus.UNPUBLISHED){
      return Operation.fail(reply.getFlightFlightPublished());
    }
    flight.setFlightStatus(FlightStatus.AVAILABLE);
    return Operation.success(flight);
  }

  public OperationResult<Flight> deleteFlight(String flightSerial){
    Flight flight = getFlightBySerial(flightSerial);
    if(flight == null){
      return Operation.fail(reply.getFlightNoFlight());
    }
    FlightStatus status = flight.getFlightStatus();
    if(status != FlightStatus.UNPUBLISHED && status != FlightStatus.TERMINATE){
      return Operation.fail(String.format(reply.getFlightCantDeleteFlight(),flightStatusMap.get(status)));
    }
    removeFlightBySerial(flight.getFlightID());
    return Operation.success(flight);
  }

  public OperationResult<ArrayList<Flight>> queryFlight(Flight flight, QueryFlightStrategy strategy){
    ArrayList<Flight> flights;
    Optional<String> startCity = Optional.ofNullable(flight.getStartCity());
    Optional<String> arrivalCity = Optional.ofNullable(flight.getArrivalCity());
    Optional<String> departureDate = Optional.ofNullable(flight.getDepartureDate());
    if(strategy == QueryFlightStrategy.OTHER){
      boolean isNotEmpty = startCity.isPresent() || arrivalCity.isPresent() || departureDate.isPresent();
      if(!isNotEmpty){
        return Operation.fail(reply.getFlightNotSetupAllTime());
      }
    }

    switch (strategy){
      case ID:
        flights = dataSource.getFlights().stream().filter(f->f.getFlightID().contains(flight.getFlightID()))
                                                  .collect(Collectors.toCollection(ArrayList::new));
        break;
      case OTHER:
        flights = dataSource.getFlights().stream()
            .filter(f->!startCity.isPresent()|| f.getStartCity().equals(startCity.get()))
            .filter(f->!arrivalCity.isPresent()||f.getStartCity().equals(arrivalCity.get()) )
            .filter(f->!departureDate.isPresent()||f.getStartCity().equals(departureDate.get()))
            .collect(Collectors.toCollection(ArrayList::new));
        break;
      default:
        return Operation.fail(reply.getFlightQueryStrategyError());
    }
    return Operation.success(flights);
  }
}
