package com.jobsity.bowling.models;

public class PinCount {
    public static final PinCount ZERO = PinCount.of("0");
    private String value;

    public static PinCount of(String value) {
        PinCount count = new PinCount();
        count.value = value;
        return count;
    }

    public int toInt() {
        return "F".equals(value) ? 0 : Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
