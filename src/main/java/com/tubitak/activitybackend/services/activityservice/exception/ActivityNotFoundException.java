package com.tubitak.activitybackend.services.activityservice.exception;

public class ActivityNotFoundException extends Exception {
    public ActivityNotFoundException() {
        super();
    }

    public ActivityNotFoundException(String message) {
        super(message);
    }

    public ActivityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}