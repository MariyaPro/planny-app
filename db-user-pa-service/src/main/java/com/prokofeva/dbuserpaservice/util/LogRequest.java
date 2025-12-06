package com.prokofeva.dbuserpaservice.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogRequest {
    boolean logParameters() default true;
    boolean logResult() default true;
    LogLevel level() default LogLevel .INFO;

    enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }
}
