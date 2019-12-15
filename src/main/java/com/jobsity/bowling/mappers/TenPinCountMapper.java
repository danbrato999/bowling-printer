package com.jobsity.bowling.mappers;

import com.jobsity.bowling.models.PinCount;

public class TenPinCountMapper implements IPinCountMapper {
    @Override
    public PinCount fromString(String value) {
        int score = "F".equals(value) ? 0 : Integer.parseInt(value);
        return new PinCount(value, score);
    }
}
