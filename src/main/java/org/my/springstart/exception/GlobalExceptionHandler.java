package org.my.springstart.exception;

import lombok.extern.slf4j.Slf4j;
import org.my.springstart.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
// 用来捕获 controller 层的异常，写完这个就可以捕获到所有的异常，在Spring框架中，使用 @RestControllerAdvice 可以统一处理全局异常
// 基本实现：创建一个类或者创建 exception 模块中创建类，添加 @RestControllerAdvice 注解，并通过 @ExceptionHandler 捕获特定异常。
//@RestControllerAdvice = @ControllerAdvice + @ResponseBody

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        log.error("{}", "发生全局异常:" + e.getMessage());
        return Result.error(e.getMessage());
    }
    // 捕获自定义业务异常
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException ex) {
        return Result.error(ex.getCode() + "-" + ex.getMessage());
    }

}
