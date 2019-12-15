package com.jobsity.bowling.models;

public class PinCount {
    private String value;
    private int score;

    public PinCount(String value, int score) {
        this.value = value;
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }
}
