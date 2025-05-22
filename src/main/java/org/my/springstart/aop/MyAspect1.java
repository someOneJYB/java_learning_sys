package org.my.springstart.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
 @Aspect     //声明切面类
 @Component //
public class MyAspect1 {
    //抽取公共切入点表达式，这个是设置的是 public 类型，可供公共使用，@Pointcut 方法下的 pt 方法无法直接触发逻辑。若需要在切入点匹配时执行代码，应将其写入通知方法（如 @Before）。
    @Pointcut("execution(* org.my.springstart.service.iml..*.*(..))")
    public void pt(){}

    //环绕通知，必须有返回值且类型是 Object
    // @Around("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕前通知....");
        Object result = joinPoint.proceed();
        log.info("环绕后通知....");
        return result;
    }

    //目标方法前触发该通知
    // @Before("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    @Before("pt()")
    public void before(){
        log.info("前置通知.......");
    }

    //无论目标方法是否有异常，都会触发该通知
    // @After("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    @After("pt()")
    public void after(){
        log.info("后置通知.......");
    }

    //只有目标方法正常返回，才会触发该通知
    // @AfterReturning("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("返回后通知.......");
    }

    //只有目标方法抛异常了，才会触发该通知
    // @AfterThrowing("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    @AfterThrowing("pt()")
    public void afterThrowing(){
        log.info("异常后通知.......");
    }
}
