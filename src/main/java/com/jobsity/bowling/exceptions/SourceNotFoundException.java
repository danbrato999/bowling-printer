package com.jobsity.bowling.exceptions;

public class SourceNotFoundException extends RuntimeException {
    public SourceNotFoundException(String message) {
        super(message);
    }

    public SourceNotFoundException(Throwable t) {
        super("Game source not found", t);
    }
}
