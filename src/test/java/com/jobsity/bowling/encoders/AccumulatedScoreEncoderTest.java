package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.BasicMatchFrame;
import com.jobsity.bowling.models.IScoredFrame;
import com.jobsity.bowling.models.PinCount;
import com.jobsity.bowling.models.PlayerScore;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jobsity.bowling.encoders.EncoderTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class AccumulatedScoreEncoderTest {

    private final AccumulatedScoreEncoder encoder = new AccumulatedScoreEncoder();

    @Test
    void encodeEmptyList() {
        PlayerScore emptyScore = new PlayerScore("", Collections.emptyList());
        String encodedScore = encoder.encode(emptyScore, DEFAULT_SEPARATOR);
        assertEquals("", encodedScore, "Empty score list not encoded as empty string");
    }

    @Test
    void encodeOk() {
        String encodedScore = encoder.encode(INCOMPLETE_SCORE, DEFAULT_SEPARATOR);
        assertEquals("10|15", encodedScore);
    }

    List<IScoredFrame> fakeScoreList() {
        PinCount firstCount = new PinCount("8", 8);
        PinCount secondCount = new PinCount("2", 2);
        PinCount thirdCount = new PinCount("0", 0);
        PinCount fourthCount = new PinCount("5", 5);
        IScoredFrame firstScore = new BasicMatchFrame(firstCount, secondCount);
        IScoredFrame secondScore = new BasicMatchFrame(thirdCount, fourthCount);

        return Arrays.asList(firstScore, secondScore);
    }
}