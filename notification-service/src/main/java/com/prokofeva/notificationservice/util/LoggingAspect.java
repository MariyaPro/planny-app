package com.prokofeva.notificationservice.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.prokofeva.notificationservice.util.Util.toJson;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(logRequest)")
    public void logRequestPointcut(LogRequest logRequest) {
    }

    @Around(value = "logRequestPointcut(logRequest)", argNames = "joinPoint,logRequest")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint, LogRequest logRequest) throws Throwable {
        var methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        logMessage(logRequest.level(), "Starting method: {} with args: {}",
                methodName, logRequest.logParameters() ? toJson(args) : "[hidden]");

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;

        logMessage(logRequest.level(), "Method {} executed successfully in {} ms. Result: {}",
                methodName, executionTime, logRequest.logResult() ? toJson(result) : "[hidden]");

        return result;
    }

    private void logMessage(LogRequest.LogLevel level, String format, Object... args) {
        switch (level) {
            case DEBUG -> logger.debug(format, args);
            case INFO -> logger.info(format, args);
            case WARN -> logger.warn(format, args);
            case ERROR -> logger.error(format, args);
        }
    }
}