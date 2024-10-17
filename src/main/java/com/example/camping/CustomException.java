package com.example.camping;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

public class CustomException extends Exception {
    private final LocalDateTime timestamp;
    private final String stackTrace;
    private final String errorDescription;

    public CustomException(String message, String errorDescription, Throwable cause) {
        super(message, cause);
        this.timestamp = LocalDateTime.now();
        StringWriter sw = new StringWriter();
        cause.printStackTrace(new PrintWriter(sw));
        this.stackTrace = sw.toString();
        this.errorDescription = errorDescription;
    }

    public CustomException(String message, String errorDescription) {
        super(message);
        this.timestamp = LocalDateTime.now();
        this.stackTrace = "";
        this.errorDescription = errorDescription;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getStackTraceString() {
        return stackTrace;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}