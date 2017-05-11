package com.airline.utils;

import com.airline.bean.Flight;
import com.airline.bean.Result.FlightResult;
import com.airline.bean.Result.OperationResult;

/**
 * Created by airline on 2017/5/10.
 * 业务操作返回结果的封装类
 */
public class Operation {
  public static OperationResult success(){
    return new OperationResult(true,null,null);
  }
  public static OperationResult success(Object obj){
    return new OperationResult(true,obj,null);
  }
  public static FlightResult success(Flight flight){
    return new FlightResult(true,null,flight);
  }
  public static OperationResult fail(String msg){
    return new OperationResult(false,null,msg);
  }
  public static FlightResult failAtFlight(String msg){
    return new FlightResult(false,msg,null);
  }
}
