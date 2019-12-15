package com.jobsity.bowling.validators;

public class TenPinScoreValidator extends BaseScoreValidator {
    private static final int MIN_FRAME_SCORE = 0;
    private static final int MAX_SCORE_PER_FRAME = 10;

    public TenPinScoreValidator () {
        super(MIN_FRAME_SCORE, MAX_SCORE_PER_FRAME, 30, 1, 10);
    }
}
