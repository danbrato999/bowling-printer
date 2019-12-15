package com.jobsity.bowling.mappers;

import com.jobsity.bowling.models.PinCount;

public interface IPinCountMapper {
    PinCount fromString(String value);
}
