package com.airline;

import com.airline.bean.*;
import com.airline.service.AdminService;
import com.airline.service.FlightService;
import com.airline.service.OrderService;
import com.airline.service.PassengerService;
import com.airline.utils.Operation;
import com.airline.utils.Util;

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
    DataSource dataSource = Util.loadFileToObject("init.json", DataSource.class);
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
    String cmd;
    OperationResult<Flight> resFlight;
    OperationResult<Passenger> passFlight;
    Passenger passenger;
    Order order;

    System.out.println("欢迎使用机票预订系统！");
    do {
      System.out.println("请输入指令进行操作，1：管理员登录，2：用户登录，3，用户注册，4：航班查询，q：退出系统");
      cmd = scanner.nextLine();
      switch (cmd){
        case "1":
          System.out.println("请输入管理员账号和密码");
          System.out.print("用户名：");
          Admin admin = new Admin();
          admin.setUserName(scanner.nextLine());
          System.out.print("密码：");
          admin.setPassword(Util.encrypt(scanner.nextLine()));
          OperationResult<Admin> resAdmin = reservation.adminService.login(admin);
          if(resAdmin.isStatus()){
            System.out.println("欢迎进入管理员系统");
            do{
              System.out.println("请输入指令进行操作：0：返回上一级，1：添加航班，2：修改航班，3：删除航班，4：查询航班，5：添加管理员");
              cmd=scanner.nextLine();
              switch (cmd){
                case "1":
                  System.out.println("请输入航班信息");
                  OperationResult<Flight> inputRes = Util.input2Object(scanner.nextLine(),Flight.class);
                  if(!inputRes.isStatus()){
                    System.out.println(inputRes.getMsg());
                    break;
                  }
                  resFlight = reservation.flightService.createFlight(inputRes.getData());
                  if(!resFlight.isStatus()){
                    System.out.println(resFlight.getMsg());
                  }else {
                    System.out.println("添加航班成功");
                  }
                  break;
                case "2":
                  System.out.println("请输入需要修改的航班序列号及需修改的信息");
                  break;
                case "3":
                  System.out.print("请输入需要删除的航班序列号：");
                  resFlight = reservation.flightService.deleteFlight(scanner.nextLine());
                  if(!resFlight.isStatus()){
                    System.out.println(resFlight.getMsg());
                    break;
                  }
                  break;
                case "4":
                  break;
                case "5":
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
          passenger = new Passenger();
          System.out.print("请输入身份证号，8位");
          passenger.setIdentityID(scanner.nextLine());
          System.out.print("请输入密码");
          passenger.setPassword(Util.encrypt(scanner.nextLine()));
          passFlight = reservation.passengerService.login(passenger);
          if(!passFlight.isStatus()){
            System.out.println(passFlight.getMsg());
            break;
          }else {
            System.out.println(passFlight.getData().getRealName()+"您好，欢迎进入用户系统，请选择指令进行操作");
            do{
              System.out.println("请输入指令进行操作：0：返回上一级，1：预定航班，2：退订航班，3：查询航班列表");
              cmd = scanner.nextLine();
              switch (cmd){
                case "1":
                  order = new Order();

                  break;
              }
            }while (!cmd.equals("0"));
          }
          break;
        case "3":
          System.out.println("请按照提示输入数据进行用户注册");
          passenger = new Passenger();
          System.out.print("请输入真实姓名：");
          passenger.setRealName(scanner.nextLine());
          System.out.print("请输入身份证号，8位");
          passenger.setIdentityID(scanner.nextLine());
          System.out.print("请输入密码");
          passenger.setPassword(Util.encrypt(scanner.nextLine()));
          passFlight = reservation.passengerService.registerPassenger(passenger);
          if(!passFlight.isStatus()){
            System.out.println(passFlight.getMsg());
            break;
          }else {
            System.out.println("用户注册成功！");
          }
          break;
        default:
          System.out.println("输入的命令不存在");
      }
    } while (!cmd.equals("q"));
    System.out.println("您已退出航班预订系统，再见！");
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
