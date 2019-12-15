package com.jobsity.bowling.exceptions;

public class SourceNotFoundException extends Exception {
    public SourceNotFoundException(Throwable t) {
        super("Game source not found", t);
    }
}
