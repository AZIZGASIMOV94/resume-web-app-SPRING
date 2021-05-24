package com.mycompany.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

  /*  @Around("execution(*com.mycompany.UserRestController.getUsers(...))")
    public void logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("logAround() running...");
        System.out.println("hijacked method: "+ proceedingJoinPoint.getSignature().getName());
        System.out.println("around before is running ");
        proceedingJoinPoint.proceed();
        System.out.println("around after is running ");

        System.out.println("****");
    }*/
}
