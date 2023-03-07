package com.wisecoach.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/6 下午4:35
 * {@code @version:} 1.0.0
 */

@Aspect
@Component
public class TestAspect {
    @Pointcut("execution(* com.wisecoach.service.*.*(..))")
    public void service() {}

    @Around("service()")
    public Object testAdvice(ProceedingJoinPoint point) throws Throwable {
        System.out.println("test aspect");
        Object proceed = point.proceed();
        System.out.println("test aspect after");
        return proceed;
    }
}
