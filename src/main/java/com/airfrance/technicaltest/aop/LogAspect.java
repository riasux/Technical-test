package com.airfrance.technicaltest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.airfrance.technicaltest.controller.UserController.*(..))")
    public void methodCall() {
    }

    @Before("methodCall()")
    public void log(JoinPoint joinPoint) {
        log.info("Before - start of execution");
        log.info("method " + joinPoint.toShortString() + " call with " + joinPoint.getArgs().length
                + " parameters :\n"
                + Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(pointcut = "methodCall()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        log.info("After - end of execution"
                + joinPoint.getSignature().getName()
                + "\n Result is " + result);
    }

    @AfterThrowing(pointcut = "methodCall()", throwing = "e")
    public void log(JoinPoint joinPoint, Throwable e) {
        log.info("method " + joinPoint.toShortString() + " return exception " + e.getClass().getSimpleName());
    }

    @Around("@annotation(com.airfrance.technicaltest.aop.TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = point.proceed();
        long endtime = System.currentTimeMillis();
        log.info("processing time : \n" +
                " Class Name: " + point.getSignature().getDeclaringTypeName() + ". Method Name: " + point.getSignature().getName() + ". Time taken for Execution is : " + (endtime - startTime) + "ms");

        return object;
    }
}
