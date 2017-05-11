package com.airline;

import com.airline.bean.Result.OperationResult;
import com.airline.service.FlightService;
import com.airline.service.OrderService;
import com.airline.utils.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by airline on 2017/5/10.
 * 程序入口文件
 */

@SuppressWarnings("unchecked")
public class Reservation {
  private FlightService flightService;
  private OrderService orderService;

  public Reservation(DataSource dataSource){
    flightService = new FlightService(dataSource);
    orderService = new OrderService(dataSource);
  }

  public static void main(String[] args) {
    //Reservation.class.getClassLoader().get
    System.out.println(System.getProperty("java.class.path"));
    System.out.println(System.getProperty("user.path"));
    Map<String,String> argsMap = null;
    OperationResult res = getArgs(args);
    if(!res.isStatus()){
      System.out.println(res.getMsg());
    }else {
      argsMap = (Map<String,String>)res.getData();
    }
    //argsMap =
  }

  private static OperationResult getArgs(String[] args){
    Map<String,String> map = new HashMap<>();
    for(String arg:args){
      String[] pair = arg.split("=");
      if(pair.length != 2){
        return Operation.fail("参数错误");
      }
      map.put(pair[0],pair[1]);
    }
    return Operation.success(map);
  }

  private static OperationResult init(){
    return Operation.success();
  }
}
