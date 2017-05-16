package com.airline;

import com.airline.bean.*;
import com.airline.service.AdminService;
import com.airline.service.FlightService;
import com.airline.service.OrderService;
import com.airline.service.PassengerService;
import com.airline.utils.Constant;
import com.airline.utils.LocalDateTimeAdapter;
import com.airline.utils.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/10.
 * 程序入口文件
 */

public class Reservation {
  private DataSource dataSource;
  private FlightService flightService;
  private PassengerService passengerService;
  private OrderService orderService;
  private AdminService adminService;

  public Reservation(DataSource dataSource) {
    this.dataSource = dataSource;
    flightService = new FlightService(dataSource);
    passengerService = new PassengerService(dataSource);
    orderService = new OrderService(dataSource, flightService, passengerService);
    adminService = new AdminService(dataSource);
    if (dataSource == null) {
      System.out.println(reply.getAppInitFail());
      System.exit(1);
    }

    OperationResult<Flight> fligtRes;
    for (Flight flight : dataSource.getFlights()) {
      Util.addSeats(flight.getFreeSeats(),flight.getSeatCapacity());
      if(StringUtils.isEmpty(flight.getArrivalDate())){
        flight.setArrivalDate(flight.getDepartureDate());
      }
    }
    flightService.publishAllFlights();
  }

  private static Reservation init() {
    DataSource dataSource = Util.loadFileToObject("init.json", DataSource.class);
    return new Reservation(dataSource);
  }

  private static Map<String, String> getArgs(String[] args) {
    Map<String, String> map = new HashMap<>();
    for (String arg : args) {
      String[] pair = arg.split("=");
      if (pair.length != 2) {
        System.out.println("命令行参数错误，程序将退出");
        System.exit(1);
      }
      map.put(pair[0], pair[1]);
    }
    return map;
  }

  private static String prettyOutput(Object obj) {
    Gson gson = new GsonBuilder().setPrettyPrinting()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    return gson.toJson(obj);
  }

  private static void process(Reservation reservation) {
    Scanner scanner = new Scanner(System.in);
    String cmd;
    OperationResult<Flight> resFlight;
    OperationResult<Passenger> passFlight;
    OperationResult<Order> resOrder;
    Passenger passenger;
    Order order;
    Admin admin;
    Admin curAdmin;

    System.out.println("欢迎使用机票预订系统！");
    do {
      System.out.println("请输入指令进行操作，1：管理员登录，2：用户登录，3，用户注册，4：航班查询，q：退出系统");
      cmd = scanner.nextLine();
      switch (cmd) {
        case "1":
          System.out.println("请输入管理员账号和密码");
          System.out.print("用户名：");
          admin = new Admin();
          admin.setUserName(scanner.nextLine());
          System.out.print("密码：");
          admin.setPassword(Util.encrypt(scanner.nextLine()));
          OperationResult<Admin> resAdmin = reservation.adminService.login(admin);
          if (resAdmin.isStatus()) {
            System.out.println("欢迎进入管理员系统");
            curAdmin = resAdmin.getData();
            do {
              System.out.println("请输入指令进行操作：0：返回上一级，1：添加航班，2,发布航班，3：修改航班，4：删除航班，5：查询航班，6：添加管理员，7，修改密码");
              cmd = scanner.nextLine();
              switch (cmd) {
                case "1":
                  System.out.println("请输入航班信息");
                  OperationResult<Flight> inputRes = Util.input2Object(scanner.nextLine(), Flight.class);
                  if (!inputRes.isStatus()) {
                    System.out.println(inputRes.getMsg());
                    break;
                  }
                  resFlight = reservation.flightService.createFlight(inputRes.getData());
                  if (!resFlight.isStatus()) {
                    System.out.println(resFlight.getMsg());
                  } else {
                    System.out.println("添加航班成功");
                  }
                  break;
                case "2":
                  System.out.print("请输入需要发布的航班序列号：");
                  resFlight  = reservation.flightService.publishFlight(scanner.nextLine());
                  if(!resFlight.isStatus()){
                    System.out.println(resFlight.getMsg());
                  }else {
                    System.out.println("发布成功");
                  }
                  break;
                case "3":
                  System.out.println("请输入需要修改的航班序列号及需修改的信息");
                  resFlight = Util.input2Object(scanner.nextLine(),Flight.class);
                  if(!resFlight.isStatus()){
                    System.out.println(resFlight.getMsg());
                  }else {
                    resFlight = reservation.flightService.updateFlight(resFlight.getData());
                    if(!resFlight.isStatus()){
                      System.out.println(resFlight.getMsg());
                    }else {
                      System.out.println("修改信息成功");
                    }
                  }
                  break;
                case "4":
                  System.out.print("请输入需要删除的航班序列号：");
                  resFlight = reservation.flightService.deleteFlight(scanner.nextLine());
                  if (!resFlight.isStatus()) {
                    System.out.println(resFlight.getMsg());
                    break;
                  }
                  break;
                case "5":
                  System.out.println("选择查找方式：Y：按照ID查找，N：起飞城市、到达城市和起飞日期");
                  String search = scanner.nextLine();
                  Flight searchFlight = new Flight();
                  OperationResult<ArrayList<Flight>> flights;
                  if(search.equals("Y")){
                    System.out.println("请输入航班号");
                    searchFlight.setFlightID(scanner.nextLine());
                    flights = reservation.flightService.queryFlight(searchFlight, Constant.QueryFlightStrategy.ID);
                    System.out.println(prettyOutput(flights));
                  }else if(search.equals("N")){
                    System.out.println("请输入相关信息：");
                    resFlight = Util.input2Object(scanner.nextLine(),Flight.class);
                    if(!resFlight.isStatus()){
                      System.out.println(resFlight.getMsg());
                      break;
                    }else {
                      flights = reservation.flightService.queryFlight(resFlight.getData(), Constant
                          .QueryFlightStrategy.OTHER);
                      System.out.println(prettyOutput(flights));
                    }
                  }
                  break;
                case "6":
                  System.out.println("请输入要添加的管理员账号和密码");
                  System.out.print("用户名：");
                  admin = new Admin();
                  admin.setUserName(scanner.nextLine());
                  System.out.print("密码：");
                  admin.setPassword(Util.encrypt(scanner.nextLine()));
                  resAdmin = reservation.adminService.addAdmin(admin);
                  if (!resAdmin.isStatus()) {
                    System.out.println(resAdmin.getMsg());
                  } else {
                    System.out.println("添加管理员成功");
                  }
                  break;
                case "7":
                  System.out.print("请输入新的密码：");
                  curAdmin.setPassword(Util.encrypt(scanner.nextLine()));
                  resAdmin = reservation.adminService.updateAdmin(curAdmin);
                  if(!resAdmin.isStatus()){
                    System.out.println(resAdmin.getMsg());
                  }
                  break;
                case "0":
                  break;
                default:
                  System.out.println("输入的命令不存在");
              }
            } while (!cmd.equals("0"));
            System.out.println("您已退出管理员系统");
          } else {
            System.out.println(resAdmin.getMsg());
          }
          break;
        case "2":
          System.out.println("请输入身份证号和密码进行登录");
          passenger = new Passenger();
          System.out.print("请输入身份证号，8位：");
          passenger.setIdentityID(scanner.nextLine());
          System.out.print("请输入密码：");
          passenger.setPassword(Util.encrypt(scanner.nextLine()));
          //char[] passString = System.console().readPassword("%s", "请输入密码:");
          //passenger.setPassword(Util.encrypt(new String(passString)));
          passFlight = reservation.passengerService.login(passenger);
          if (!passFlight.isStatus()) {
            System.out.println(passFlight.getMsg());
            break;
          } else {
            System.out.println(passFlight.getData().getRealName() + "您好，欢迎进入用户系统，请选择指令进行操作");
            Passenger curPassenger = passFlight.getData();
            do {
              System.out.println("请输入指令进行操作：0：返回上一级，1：预定航班，2：退订航班，3：查询订单列表");
              cmd = scanner.nextLine();
              switch (cmd) {
                case "1":
                  order = new Order();
                  System.out.print("请输入航班序列号");
                  order.setPassengerID(curPassenger.getPassengerID());
                  order.setFlightSerial(scanner.nextLine());
                  resOrder = reservation.orderService.reserveTicket(order);
                  order = resOrder.getData();
                  if (!resOrder.isStatus()) {
                    System.out.println(resOrder.getMsg());
                    break;
                  } else {
                    System.out.println("预定航班成功，座位号为" + order.getSeat() + "，是否支付，Y：支付，N：取消订单");
                    String pay = scanner.nextLine();
                    if (pay.equals("Y")) {
                      order.setOrderStatus(Constant.OrderStatus.PAID);
                      resOrder = reservation.orderService.payOrder(order);
                      if (!resOrder.isStatus()) {
                        System.out.println(resOrder.getMsg());
                      } else {
                        System.out.println("支付成功");
                      }
                    } else if (pay.equals("N")) {
                      order.setOrderStatus(Constant.OrderStatus.CANCEL);
                      resOrder = reservation.orderService.payOrder(order);
                      if (!resOrder.isStatus()) {
                        System.out.println(resOrder.getMsg());
                      } else {
                        System.out.println("取消支付成功，机票已被退订");
                      }
                    }
                  }
                  break;
                case "2":
                  order = new Order();
                  System.out.print("请输入航班序列号");
                  order.setPassengerID(curPassenger.getPassengerID());
                  order.setFlightSerial(scanner.nextLine());
                  resOrder = reservation.orderService.unsubscribeFlight(order);
                  if (!resOrder.isStatus()) {
                    System.out.println(resOrder.getMsg());
                    break;
                  } else {
                    System.out.println("退订航班成功");
                  }
                  break;
                case "3":
                  System.out.println(prettyOutput(curPassenger.getOrderList()));
                  break;
                case "0":
                  break;
                default:
                  System.out.println("输入的命令不存在");
              }
            } while (!cmd.equals("0"));
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
          if (!passFlight.isStatus()) {
            System.out.println(passFlight.getMsg());
            break;
          } else {
            System.out.println("用户注册成功！");
          }
          break;
        case "4":
          System.out.println("选择查找方式：Y：按照ID查找，N：起飞城市、到达城市和起飞日期");
          String search = scanner.nextLine();
          Flight searchFlight = new Flight();
          OperationResult<ArrayList<FlightPublic>> flights;
          Constant.QueryFlightStrategy strategy;
          if(search.equals("Y")){
            System.out.println("请输入航班号：");
            searchFlight.setFlightID(scanner.nextLine());
            flights = reservation.flightService.queryFlightPublic(searchFlight, Constant.QueryFlightStrategy.ID);
            System.out.println(prettyOutput(flights.getData()));
          }else if(search.equals("N")){
            System.out.println("请输入相关信息：");
            resFlight = Util.input2Object(scanner.nextLine(),Flight.class);
            if(!resFlight.isStatus()){
              System.out.println(resFlight.getMsg());
              break;
            }else {
              flights = reservation.flightService.queryFlightPublic(resFlight.getData(), Constant.QueryFlightStrategy.OTHER);
              System.out.println(prettyOutput(flights.getData()));
            }
          }
          break;
        case "0":
          break;
        default:
          System.out.println("输入的命令不存在");
      }
    } while (!cmd.equals("q"));
    System.out.println("您已退出航班预订系统，再见！");
  }

  public static void main(String[] args) {
    Map<String,String> argsMap = getArgs(args);
    Reservation reservation = init();
    String path = System.getProperty("user.dir");
    System.out.println(path);
    process(reservation);
    path += "/data.json";
    try {
      Files.write(Paths.get(path), prettyOutput(reservation.dataSource).getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
