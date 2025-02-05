package com.itheima.reggie.exception;

/**
 * @ClassName CustomException
 * @Description TODO  自定义业务异常
 * @Author aql
 * @Date 2025/2/5 16:38
 * @Version 1.0
 **/
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
