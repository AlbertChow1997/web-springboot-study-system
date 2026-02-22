package com.itheima.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RecordTimeAspect {
    @Around("execution(* com.itheima.service.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable{
        //1. record the start time
        long begin = System.currentTimeMillis();
        //2. implement the method
        Object result = pjp.proceed();

        //3. record the end time
        long end = System.currentTimeMillis();
        log.info("Method {} implement time: {} ms", pjp.getSignature(),end - begin);
        return result;
    }
}
