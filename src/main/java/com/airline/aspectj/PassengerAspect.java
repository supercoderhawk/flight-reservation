package com.airline.aspectj;

import com.airline.DataSource;
import com.airline.bean.Passenger;
import com.airline.service.PassengerService;
import com.airline.utils.Operation;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static com.airline.utils.Constant.reply;

/**
 * Created by airline on 2017/5/14.
 * 乘客业务横切类
 */

@Aspect
public class PassengerAspect {
  @Pointcut("execution(* com.airline.service.PassengerService.registerPassenger(com.airline.bean.Passenger)) && args" +
      "(passenger)")
  public void createPassengerPointcut(Passenger passenger) {
  }

  @Before("createPassengerPointcut(com.airline.bean.Passenger) && args(passenger)")
  public void validateCreatePassenger(JoinPoint joinPoint, Passenger passenger) {
    DataSource dataSource = ((PassengerService)joinPoint.getTarget()).getDataSource();
    if (StringUtils.isEmpty(passenger.getRealName())) {
      dataSource.setPassengerCheck(Operation.fail(reply.getPassengerNameEmpty()));
    }
    dataSource.setPassengerCheck(Operation.success());
  }

  @Pointcut("execution(* com.airline.service.PassengerService.*(com.airline.bean.Passenger)) && args(passenger)")
  public void commonPointcut(Passenger passenger) {
  }

  @Before("commonPointcut(com.airline.bean.Passenger) && args(passenger)")
  public void validateAll(JoinPoint joinPoint, Passenger passenger) {
    DataSource dataSource = ((PassengerService)joinPoint.getTarget()).getDataSource();
    if (StringUtils.isEmpty(passenger.getIdentityID()) || passenger.getIdentityID().length() != 8) {
      dataSource.setPassengerCheck(Operation.fail(reply.getPassengerIdentityLengthError()));
      return;
    } else if (StringUtils.isEmpty(passenger.getPassword())) {
      dataSource.setPassengerCheck(Operation.fail(reply.getPassengerPasswordEmpty()));
      return;
    }
    dataSource.setPassengerCheck(Operation.success());
  }
}
