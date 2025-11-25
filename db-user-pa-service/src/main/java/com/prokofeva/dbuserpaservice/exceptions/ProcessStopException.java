package com.prokofeva.dbuserpaservice.exceptions;

public class ProcessStopException extends RuntimeException {

    public ProcessStopException(String message) {
        super(message);
    }
}
