package com.jobsity.bowling.validators;

import com.jobsity.bowling.models.FinalMatchFrame;

public class TenPinScoreValidator extends BaseScoreValidator {
    private static final int MIN_FRAME_SCORE = 0;
    private static final int MAX_SCORE_PER_FRAME = 10;
    private static final int MAX_LAST_FRAME_SCORE = 30;
    private static final int MAX_LAST_FRAME_ATTEMPTS = 3;
    private static final int MAX_FRAMES_PER_PLAYER = 10;

    public TenPinScoreValidator () {
        super(MIN_FRAME_SCORE, MAX_SCORE_PER_FRAME, MAX_LAST_FRAME_SCORE, MAX_LAST_FRAME_ATTEMPTS, MAX_FRAMES_PER_PLAYER);
    }

    @Override
    protected void validateFinalFrame(FinalMatchFrame frame) {
        super.validateFinalFrame(frame);
        assert frame.getShots().get(0).getScore() != MAX_SCORE_PER_FRAME || frame.shotsCount() == 3;
    }
}
