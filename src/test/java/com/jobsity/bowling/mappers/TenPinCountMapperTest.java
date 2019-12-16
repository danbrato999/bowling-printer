package com.jobsity.bowling.mappers;

import com.jobsity.bowling.models.PinCount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TenPinCountMapperTest {
    private final TenPinCountMapper mapper = new TenPinCountMapper();

    @Test
    void fromString() {
        PinCount strike = mapper.fromString("10");
        assertEquals(10, strike.getScore());

        PinCount fail = mapper.fromString("F");
        assertEquals("F", fail.getValue());
        assertEquals(0, fail.getScore());

        PinCount number = mapper.fromString("7");
        assertEquals(7, number.getScore());
    }
}