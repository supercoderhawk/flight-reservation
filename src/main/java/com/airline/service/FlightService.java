package com.airline.service;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.dao.BaseDao;
import com.airline.dao.FlightDao;
import com.airline.utils.Operation;

/**
 * Created by airline on 2017/5/10.
 * 航班业务处理类
 */
public class FlightService extends FlightDao {

  public FlightService(DataSource dataSource) {
    super(dataSource);
  }

  public OperationResult createFlight(Flight flight){
    if(getFlightByID(flight.getFlightID())!=null){
     return Operation.fail("航班ID已存在");
    }
    return Operation.success(flight);
  }
}
