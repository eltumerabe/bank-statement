package com.nagaro.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NagaroLogger {
    private final Logger logger = LoggerFactory.getLogger(NagaroLogger.class);

    @Around("execution(* com.nagaro..*.*(..)))")
    public Object logTracePerformanceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        logger.info(" ===== enter " + className + "." + methodName + " =====");
        Object result = joinPoint.proceed();
        //Log method execution time
        logger.info(" ===== exit " + className + "." + methodName + " =====");
        return result;
    }

}
