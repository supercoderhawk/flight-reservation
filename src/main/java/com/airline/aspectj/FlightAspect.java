package com.airline.aspectj;

import com.airline.DataSource;
import com.airline.bean.Flight;
import com.airline.service.FlightService;
import com.airline.utils.Constant;
import com.airline.utils.Operation;
import com.airline.utils.util;
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

  @Pointcut("execution(* com.airline.service.FlightService.createFlight(com.airline.bean.Flight)) && args(flight) || execution(* com.airline.service.FlightService.updateFlight(com.airline.bean.Flight)) && args(flight)")
  public void validatePoint(Flight flight) {
  }

  /**
   * <p>在创建和更新航班前进行参数检查。</p>
   * <p>要求：
   * <ol>
   * <li>航班号和航班序列号不能为空</li>
   * <li>创建航班时，起飞、降落的时间和城市均不能为空</li>
   * <li>更新日期时间格式必须正确</li>
   * </ol>
   * </p>
   * <p>更新航班时，值为null的参数会被忽略</p>
   * <p>检查结果存储于dataSource的modifyFlight字段中</p>
   *
   * @param joinPoint:
   * @param flight:
   */
  @Before("validatePoint(com.airline.bean.Flight) && args(flight)")
  public void validateFlight(JoinPoint joinPoint, Flight flight) {
    ValidateFunction func = null;
    DataSource dataSource = ((FlightService)joinPoint.getTarget()).getDataSource();
    if (StringUtils.isEmpty(flight.getFlightID())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightFlightIDEmpty()));
      return;
    } else if (StringUtils.isEmpty(flight.getFlightSerial())) {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightFlightSerialEmpty()));
      return;
    }
    String funcShortString = joinPoint.toShortString();
    if (funcShortString.contains("createFlight")) {
      func = ValidateFunction.CREATE;
    } else if (funcShortString.contains("updateFlight")) {
      func = ValidateFunction.UPDATE;
    } else {
      dataSource.setModifyFlight(Operation.fail(reply.getFlightFunctionInvokeError()));
      return;
    }

    Optional<String> startTime = Optional.ofNullable(flight.getStartTime());
    Optional<String> arrivalTime = Optional.ofNullable(flight.getArrivalTime());
    Optional<String> departureDate = Optional.ofNullable(flight.getDepartureDate());
    boolean isNotEmpty = startTime.isPresent() && arrivalTime.isPresent() && departureDate.isPresent();
    isNotEmpty = isNotEmpty && StringUtils.isNotEmpty(flight.getStartCity())
                      && StringUtils.isNotEmpty(flight.getArrivalCity());

    if (func == ValidateFunction.CREATE) {
      if (!isNotEmpty) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightParameterEmpty()));
        return;
      } else if (!util.isTimeValidate(startTime.get())) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightStartTimeError()));
        return;
      } else if (!util.isTimeValidate(arrivalTime.get())) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightArrivalTimeError()));
        return;
      } else if (!util.isDateValidate(departureDate.get())) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightDepartureDateError()));
        return;
      } else if (StringUtils.isEmpty(flight.getStartCity())) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightStartCityEmpty()));
        return;
      } else if (StringUtils.isEmpty(flight.getArrivalCity())) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightArrivalCityEmpty()));
        return;
      }
      Integer seats = flight.getSeatCapacity();
      if (seats != null) {
        for (int i = 0; i < seats; i++) {
          flight.getFreeSeats().add(String.valueOf(i + 1));
        }
      }
    }

    if (func == ValidateFunction.UPDATE) {
      if (startTime.isPresent() && util.isTimeValidate(startTime.get())) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightParameterEmpty()));
        return;
      } else if (arrivalTime.isPresent() && !util.isTimeValidate(arrivalTime.get())) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightStartTimeError()));
        return;
      } else if (departureDate.isPresent() && !util.isDateValidate(departureDate.get())) {
        dataSource.setModifyFlight(Operation.fail(reply.getFlightDepartureDateError()));
        return;
      }
    }
    dataSource.setModifyFlight(Operation.success());
    //System.out.println(Operation.success());
    //System.out.println("as"+dataSource.getModifyFlight());
  }

  @Pointcut("execution(* com.airline.service.FlightService.*(..))")
  public void updateStatusPoint() {
  }

  @Before("updateStatusPoint()")
  public void updateFlightStatus(JoinPoint joinPoint) {
    DataSource dataSource = ((FlightService)joinPoint.getTarget()).getDataSource();
    for (Flight flight : dataSource.getFlights()) {
      if (flight.getFlightStatus() == Constant.FlightStatus.AVAILABLE && util.isTimeToTerminate(flight.getStartTime())) {
        flight.setFlightStatus(Constant.FlightStatus.TERMINATE);
      }
      if (flight.getFlightStatus() == Constant.FlightStatus.FULL && util.isTimeToTerminate(flight.getStartTime())) {
        flight.setFlightStatus(Constant.FlightStatus.TERMINATE);
      }
    }
  }

}
