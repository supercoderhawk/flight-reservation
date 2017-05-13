package com.airline.utils;

import com.airline.bean.OperationResult;

/**
 * Created by airline on 2017/5/10.
 * 业务操作返回结果的封装类
 */
public class Operation {
  public static OperationResult<Object> success(){
    return new OperationResult<>(true,null,null);
  }

  public static <T> OperationResult<T> success(T t){
    return new OperationResult<>(true,t,null);
  }

  public static <T> OperationResult<T> fail(String msg){
    return new OperationResult<>(false,null,msg);
  }

}
