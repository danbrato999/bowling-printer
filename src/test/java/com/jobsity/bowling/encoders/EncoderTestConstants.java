package com.jobsity.bowling.encoders;

import com.jobsity.bowling.mappers.IPinCountMapper;
import com.jobsity.bowling.mappers.TenPinCountMapper;
import com.jobsity.bowling.models.BasicMatchFrame;
import com.jobsity.bowling.models.MatchFrame;
import com.jobsity.bowling.models.PlayerScore;

import java.util.Arrays;

public class EncoderTestConstants {
    static final String DEFAULT_SEPARATOR = "|";
    static final IPinCountMapper countMapper = new TenPinCountMapper();
    static final PlayerScore INCOMPLETE_SCORE = getIncompleteScore();

    private static PlayerScore getIncompleteScore() {
        MatchFrame firstScore = new BasicMatchFrame(countMapper.fromString("8"), countMapper.fromString("2"));
        MatchFrame secondScore = new BasicMatchFrame(countMapper.fromString("0"), countMapper.fromString("5"));

        return new PlayerScore("", Arrays.asList(firstScore, secondScore));
    }

    private EncoderTestConstants() {}
}
