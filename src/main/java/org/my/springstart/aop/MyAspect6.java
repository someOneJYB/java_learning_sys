package org.my.springstart.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 演示连接点对象: 可以获取目标方法相关的所有信息，包括方法名、签名、参数、目标类名、返回值（仅限环绕通知）
 */
@Slf4j
@Aspect     //声明切面类
@Component
public class MyAspect6 {

    @Pointcut("execution(* org.my.springstart.service.DeptService.*(..))")
    public void pt(){}

    @Before("pt()")
    public void before(JoinPoint joinPoint) {
        System.out.println("-------------------------------------------");
        //1.获取目标类名
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("name = " + name);

        //2.获取目标方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("signature = " + signature);

        //3.获取目标方法名
        String methodName = signature.getName();
        System.out.println("methodName = " + methodName);

        //4.获取方法参数
        Object[] args = joinPoint.getArgs();
        System.out.println("args = " + Arrays.toString(args));

        System.out.println("-------------------------------------------");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.获取目标类名
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("name = " + name);

        //2.获取目标方法签名
        Signature signature = joinPoint.getSignature();
        System.out.println("signature = " + signature);

        //3.获取目标方法名
        String methodName = signature.getName();
        System.out.println("methodName = " + methodName);

        //4.获取方法参数
        Object[] args = joinPoint.getArgs();
        System.out.println("args = " + Arrays.toString(args));

        //5.获取方法返回值
        Object result = joinPoint.proceed();
        System.out.println("result = " + result);
        return result;

    }


}
