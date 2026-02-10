package com.albert.exception;


import com.albert.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("handleException: ", e);
        return Result.error("Error! Please connect the administrator");

    }

    //first handle the exact exception, then handle the parent exception
    @ExceptionHandler
    public Result DuplicateKeyException(DuplicateKeyException e){
        log.error("DuplicateKeyException: ", e);
        String msg = e.getMessage();
        String errorMsg = msg.substring(msg.indexOf("Duplicate entry"));
        String[] arr = errorMsg.split(" ");
        return Result.error("Value " +arr[2] + " is exist");

    }

    @ExceptionHandler
    public Result handleBusinessException(BusinessException businessException) {
        log.error("服务器异常", businessException);
        return Result.error(businessException.getMessage());
    }

}
