package com.jobsity.bowling;

public class SourceNotFoundException extends Exception {
    public SourceNotFoundException(Throwable t) {
        super("Game source not found", t);
    }
}
