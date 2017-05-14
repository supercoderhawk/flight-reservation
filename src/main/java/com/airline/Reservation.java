package com.airline;

import com.airline.bean.OperationResult;
import com.airline.service.FlightService;
import com.airline.service.OrderService;
import com.airline.service.PassengerService;
import com.airline.utils.Operation;
import com.airline.utils.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/10.
 * 程序入口文件
 */

public class Reservation {
  private FlightService flightService;
  private PassengerService passengerService;
  private OrderService orderService;

  public Reservation(DataSource dataSource) {
    flightService = new FlightService(dataSource);
    passengerService = new PassengerService(dataSource);
    orderService = new OrderService(dataSource, flightService, passengerService);
  }

  private static OperationResult<Reservation> init() {
    DataSource dataSource = util.loadFileToObject("init.json", DataSource.class);
    Reservation reservation = new Reservation(dataSource);
    return Operation.success(reservation);
  }

  private static OperationResult<Map<String, String>> getArgs(String[] args) {
    Map<String, String> map = new HashMap<>();
    for (String arg : args) {
      String[] pair = arg.split("=");
      if (pair.length != 2) {
        return Operation.fail("参数错误");
      }
      map.put(pair[0], pair[1]);
    }
    return Operation.success(map);
  }

  private static void process() {
    Scanner scanner = new Scanner(System.in);
    //System.out.print("Enter your name: ");
    String cmd = null;
    String data = null;
    do {
      cmd = scanner.nextLine();
      switch (cmd){
        case "1":
          System.out.println("请输入用户和密码");
          data = scanner.nextLine();
      }
      data = scanner.nextLine();
    } while (cmd.equals("q"));
  }

  public static void main(String[] args) {
    /*
    Map<String,String> argsMap = null;
    OperationResult<Map<String,String>> res = getArgs(args);
    if(!res.isStatus()){
      System.out.println(res.getMsg());
    }else {
      argsMap = res.getData();
    }
    */
    OperationResult<Reservation> res = init();
    if (!res.isStatus()) {
      System.out.println(reply.getAppInitFail());
      return;
    }
    process();
  }
}
