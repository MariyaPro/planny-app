package com.prokofeva.notificationservice.exceptions;

public class ProcessStopException extends RuntimeException {

    public ProcessStopException(String message) {
        super(message);
    }
}
