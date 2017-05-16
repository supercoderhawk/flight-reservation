package com.airline.aspectj;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.bean.OperationResult;
import com.airline.service.FlightService;
import com.airline.utils.Constant;
import com.airline.utils.Operation;
import com.airline.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Optional;

import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/12.
 * 航班业务横切类
 */
@Aspect
public class FlightAspect {
  private enum ValidateFunction {UPDATE, CREATE}

  @Pointcut("execution(* com.airline.service.FlightService.createFlight(com.airline.bean.Flight)) && args(flight)")
  public void validatePointAtCreate(Flight flight) {
  }

  @Pointcut("execution(* com.airline.service.FlightService.updateFlight(com.airline.bean.Flight)) && args(flight)")
  public void validatePointAtUpdate(Flight flight) {
  }

  /**
   * <p>在创建航班前进行参数检查。</p>
   * <p>要求：</p>
   * <ol>
   * <li>航班号和航班序列号不能为空</li>
   * <li>起飞、降落的时间和城市均不能为空</li>
   * <li>更新日期时间格式必须正确</li>
   * </ol>
   * <p>检查结果存储于dataSource的modifyFlight字段中</p>
   * @param joinPoint:
   * @param flight:
   */
  @Before("validatePointAtCreate(com.airline.bean.Flight) && args(flight)")
  public void validateFlightAtCreate(JoinPoint joinPoint, Flight flight) {
    DataSource dataSource = ((FlightService) joinPoint.getTarget()).getDataSource();
    if(flight == null){
      dataSource.setModifyFlight(Operation.fail(reply.getFlightParameterEmpty()));
      return;
    }
    if (StringUtils.isEmpty(flight.getFlightID())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightFlightIDEmpty()));
      return;
    } else if (StringUtils.isEmpty(flight.getFlightSerial())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightFlightSerialEmpty()));
      return;
    }

    Optional<String> startTime = Optional.ofNullable(flight.getStartTime());
    Optional<String> arrivalTime = Optional.ofNullable(flight.getArrivalTime());
    Optional<String> departureDate = Optional.ofNullable(flight.getDepartureDate());
    boolean isNotEmpty = startTime.isPresent() && arrivalTime.isPresent() && departureDate.isPresent();
    isNotEmpty = isNotEmpty && StringUtils.isNotEmpty(flight.getStartCity())
        && StringUtils.isNotEmpty(flight.getArrivalCity());

    // 对参数格式的检查
    if (!isNotEmpty) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightParameterEmpty()));
      return;
    } else if (!Util.isDateValidate(departureDate.get())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightDepartureDateInvalidate()));
      return;
    } else if (!Util.isTimeValidate(startTime.get())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightStartTimeInvalidate()));
      return;
    } else if (!Util.isTimeValidate(arrivalTime.get())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightArrivalTimeInvalidate()));
      return;
    } else if (StringUtils.isEmpty(flight.getStartCity())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightStartCityEmpty()));
      return;
    } else if (StringUtils.isEmpty(flight.getArrivalCity())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightArrivalCityEmpty()));
      return;
    }

    if (StringUtils.isNotEmpty(flight.getArrivalDate())) {
      if (!Util.isDateValidate(flight.getArrivalDate())) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightArrivalDateInvalidate()));
        return;
      }
    } else {
      flight.setArrivalDate(flight.getDepartureDate());
    }

    // 对起飞和降落时间的检查
    boolean isStartEnd = Util.isStartAndArrivalTimeValidate(startTime.get(), flight.getDepartureDate(),
                                                            arrivalTime.get(), flight.getArrivalDate());
    if (!Util.isStartTimeValidate(startTime.get(), departureDate.get())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightStartTimeError()));
      return;
    } else if (!isStartEnd) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightStartArrivalTimeError()));
      return;
    }

    // 设置座位
    Util.addSeats(flight.getFreeSeats(),flight.getSeatCapacity());

    dataSource.setModifyFlight(Operation.success());
  }

  /**
   * <p>更新航班前进行参数检查</p>
   * <p>要求</p>
   * <ol>
   * <li>必须传入航班序列号并且航班序列号对应的航班必须存在</li>
   * <li>如果参数为空，则该字段不更新，即只设置航班序列号的话，等于不更新</li>
   * <li>日期格式必须符合要求，起飞时间和降落时间必须符合要求</li>
   * </ol>
   *
   * @param joinPoint: 连接点参数
   * @param flight:    更新的航班对象
   */
  @Before("validatePointAtUpdate(com.airline.bean.Flight) && args(flight)")
  public void validateFlightAtUpdate(JoinPoint joinPoint, Flight flight) {
    DataSource dataSource = ((FlightService) joinPoint.getTarget()).getDataSource();
    if (StringUtils.isEmpty(flight.getFlightSerial())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightFlightSerialEmpty()));
      return;
    }
    OperationResult<Flight> flightRes = isFlightExist(dataSource, flight.getFlightSerial());
    if (!flightRes.isStatus()) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightNoFlight()));
      return;
    }

    Flight oldFlight = flightRes.getData();
    String startTime = flight.getStartTime();
    String arrivalTime = flight.getArrivalTime();
    String departureDate = flight.getDepartureDate();
    String arrivalDate = flight.getArrivalDate();
    if (!StringUtils.isEmpty(startTime)) {
      if (!Util.isTimeValidate(startTime)) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightParameterEmpty()));
        return;
      }
    } else {
      startTime = oldFlight.getStartTime();
    }
    if (!StringUtils.isEmpty(arrivalTime)) {
      if (!Util.isTimeValidate(arrivalTime)) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightStartTimeInvalidate()));
        return;
      }
    } else {
      arrivalTime = oldFlight.getArrivalTime();
    }
    if (!StringUtils.isEmpty(departureDate)) {
      if (!Util.isDateValidate(departureDate)) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightDepartureDateInvalidate()));
        return;
      }
    } else {
      departureDate = oldFlight.getDepartureDate();
    }
    if (!StringUtils.isEmpty(arrivalDate)) {
      if (!Util.isDateValidate(arrivalDate)) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightArrivalDateInvalidate()));
        return;
      }
    } else {
      arrivalDate = oldFlight.getArrivalDate();
    }

    // 对起飞和降落时间的检查
    boolean isStartEnd = Util.isStartAndArrivalTimeValidate(startTime, departureDate, arrivalTime, arrivalDate);
    if (!Util.isStartTimeValidate(startTime, departureDate)) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightStartTimeError()));
      return;
    } else if (!isStartEnd) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightStartArrivalTimeError()));
      return;
    }
    dataSource.setModifyFlight(Operation.success());
  }

  @Pointcut("execution(* *.*SWITCH_TABLE*(..)) || execution(* *.*lambda*(..))")
  public void excludeMethod(){}

  @Pointcut("execution(* com.airline.service.FlightService.*(..))  && !excludeMethod()")
  public void updateStatusPoint() {
  }

  @Before("updateStatusPoint()")
  public void updateFlightStatus(JoinPoint joinPoint) {
    DataSource dataSource = ((FlightService) joinPoint.getTarget()).getDataSource();
    for (Flight flight : dataSource.getFlights()) {
      boolean isTerminate = Util.isStartTimeValidate(flight.getStartTime(), flight.getDepartureDate());
      Constant.FlightStatus status = flight.getFlightStatus();
      if (!isTerminate) {
        if (status == Constant.FlightStatus.AVAILABLE || status == Constant.FlightStatus.FULL) {
          flight.setFlightStatus(Constant.FlightStatus.TERMINATE);
        }
      }
    }
  }

  private static OperationResult<Flight> isFlightExist(DataSource dataSource, String flightSerial) {
    for (Flight flight : dataSource.getFlights()) {
      if (flight.getFlightSerial().equals(flightSerial)) {
        return Operation.success(flight);
      }
    }
    return Operation.fail(reply.getFlightNoFlight());
  }
}
