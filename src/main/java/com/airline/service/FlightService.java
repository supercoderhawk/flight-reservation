package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.Result.FlightResult;
import com.airline.dao.FlightDao;
import com.airline.utils.Operation;
import com.airline.utils.Constant.FlightStatus;
import static com.airline.utils.Constant.flightStatusMap;

/**
 * Created by airline on 2017/5/10.
 * 航班业务处理类
 */
public class FlightService extends FlightDao {

  public FlightService(DataSource dataSource) {
    super(dataSource);
  }

  public FlightResult createFlight(Flight flight){
    if(getFlightByID(flight.getFlightID())!=null){
     return Operation.failAtFlight("航班ID已存在");
    }
    addFlight(flight);
    return Operation.success(flight);
  }

  public FlightResult updateFlight(Flight flight){

    return Operation.success(flight);
  }

  public FlightResult deleteFlight(String flightID){
    Flight flight = getFlightByID(flightID);
    if(flight == null){
      return Operation.failAtFlight("航班不存在");
    }
    FlightStatus status = flight.getFlightStatus();
    if(status != FlightStatus.UNPUBLISHED && status != FlightStatus.TERMINATE){
      return Operation.failAtFlight("航班状态为:"+flightStatusMap.get(status)+"，无法删除");
    }
    removeFlightByID(flight.getFlightID());
    return Operation.success(flight);
  }
}
