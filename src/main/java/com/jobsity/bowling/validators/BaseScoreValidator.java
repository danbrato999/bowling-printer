package com.jobsity.bowling.validators;

import com.jobsity.bowling.models.*;

public abstract class BaseScoreValidator implements IGameValidator {
    private int minFrameScore;
    private int maxRegularFrameScore;
    private int maxLastFrameScore;
    private int maxLastFrameAttempts;
    private int maxFramesPerPlayer;

    public BaseScoreValidator(int minFrameScore, int maxRegularFrameScore, int maxLastFrameScore, int maxLastFrameAttempts, int maxFramesPerPlayer) {
        this.minFrameScore = minFrameScore;
        this.maxRegularFrameScore = maxRegularFrameScore;
        this.maxLastFrameScore = maxLastFrameScore;
        this.maxLastFrameAttempts = maxLastFrameAttempts;
        this.maxFramesPerPlayer = maxFramesPerPlayer;
    }

    @Override
    public void validateMatchFrame(IScoredFrame frame) {
        assert frame.getFrameScore() > minFrameScore;

        if (frame instanceof FinalMatchFrame) {
            assert frame.shotsCount() <= maxLastFrameAttempts;
            assert frame.getFrameScore() <= maxLastFrameScore;
        } else
            assert frame.getShotsScore() <= maxRegularFrameScore;
    }

    @Override
    public void validatePlayerScore(PlayerScore score) {
        assert score.getMatchFrames().size() < maxFramesPerPlayer;
    }

    @Override
    public boolean isStrike(IScoredFrame frame) {
        return frame.getShotsScore() == maxRegularFrameScore && frame.shotsCount() == 1;
    }

    @Override
    public boolean isSplit(IScoredFrame frame) {
        return frame.getShotsScore() == maxRegularFrameScore && frame.shotsCount() == 2;
    }

    @Override
    public boolean isStrike(PinCount pinCount) {
        return pinCount.getScore() == maxRegularFrameScore;
    }

    @Override
    public boolean areSplit(PinCount count1, PinCount count2) {
        return count1.getScore() + count2.getScore() == maxRegularFrameScore;
    }
}
