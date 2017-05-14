package com.airline;

import com.airline.bean.Admin;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.service.AdminService;
import com.airline.service.FlightService;
import com.airline.service.OrderService;
import com.airline.service.PassengerService;
import com.airline.utils.InputProcess;
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
  private AdminService adminService;

  public Reservation(DataSource dataSource) {
    flightService = new FlightService(dataSource);
    passengerService = new PassengerService(dataSource);
    orderService = new OrderService(dataSource, flightService, passengerService);
    adminService = new AdminService(dataSource);
  }

  private static OperationResult<Reservation> init() {
    DataSource dataSource = util.loadFileToObject("init.json",DataSource.class);
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

  private static void process(Reservation reservation) {
    Scanner scanner = new Scanner(System.in);
    //System.out.print("Enter your name: ");
    String cmd = null;
    String data = null;
    System.out.println("欢迎使用机票预订系统！");
    do {
      System.out.println("请输入指令进行操作，1：管理员登录，2：用户登录，3，航班查询");
      cmd = scanner.nextLine();
      switch (cmd){
        case "1":
          System.out.println("请输入管理员账号和密码");
          System.out.print("用户名：");
          Admin admin = new Admin();
          admin.setUserName(scanner.nextLine());
          System.out.print("密码：");
          admin.setPassword(util.encrypt(scanner.nextLine()));
          OperationResult<Admin> resAdmin = reservation.adminService.login(admin);
          if(resAdmin.isStatus()){
            System.out.println("欢迎进入管理员系统");
            do{
              System.out.println("请输入指令进行操作：0：返回上一级，1：添加航班，2：修改航班，3：删除航班");
              cmd=scanner.nextLine();
              switch (cmd){
                case "1":
                  System.out.println("请输入航班信息");
                  OperationResult<Flight> inputRes = InputProcess.input2Object(scanner.nextLine(),Flight.class);
                  if(!inputRes.isStatus()){
                    System.out.println(inputRes.getMsg());
                    break;
                  }
                  OperationResult<Flight> resFlight = reservation.flightService.createFlight(inputRes.getData());
                  if(!resFlight.isStatus()){
                    System.out.println(resFlight.getMsg());
                  }else {
                    System.out.println("添加航班成功");
                  }
                  break;
                default:
                  System.out.println("输入的命令不存在");
              }
            }while (!cmd.equals("0"));
            System.out.println("您已退出管理员系统");
          }else {
            System.out.println(resAdmin.getMsg());
          }
          break;
        case "2":
          System.out.println("请输入身份证号和密码进行登录");
        default:
          System.out.println("输入的命令不存在");
      }
      //data = scanner.nextLine();
    } while (!cmd.equals("q"));
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
    process(res.getData());
  }
}
