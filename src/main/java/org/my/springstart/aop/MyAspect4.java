package org.my.springstart.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// @Order(Integer.MIN_VALUE)   //设置优先级，数字越小，优先级越高
@Slf4j
 @Aspect     //声明切面类
 @Component
public class MyAspect4 {
    //抽取公共切入点表达式
    @Pointcut("execution(* org.my.springstart.service.iml.DeptServiceImpl.*(..))")
    public void pt(){}

    //目标方法前触发该通知
    @Before("pt()")
    public void before(){
        log.info("MyAspect4.....前置通知.......");
    }

    //无论目标方法是否有异常，都会触发该通知
    @After("pt()")
    public void after(){
        log.info("MyAspect4.....后置通知.......");
    }


}
