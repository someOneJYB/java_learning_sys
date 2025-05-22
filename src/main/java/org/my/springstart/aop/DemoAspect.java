package org.my.springstart.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect //声明当前类是一个AOP类（切面类）
@Component // 必须写不然的话就无法被管理注入就不会执行
public class DemoAspect {

    /**
     * 统计耗时
     * @return
     */
    @Around("execution(* org.my.springstart.service.iml..*.*(..))")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.获取方法运行时的开始时间
        long begin = System.currentTimeMillis();

        //2.运行原始方法
        Object result = joinPoint.proceed();

        //3.获取方法运行结束时间，计算耗时
        long end = System.currentTimeMillis();
        log.info("方法{}", joinPoint.getSignature(), "执行耗时：{}ms", end - begin);
        log.info("执行耗时：{}ms", end - begin);
        //4.返回响应结果
        return result;
    }
}
