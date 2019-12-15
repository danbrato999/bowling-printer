package com.jobsity.bowling.validators;

import com.jobsity.bowling.models.FinalMatchFrame;
import com.jobsity.bowling.models.MatchFrame;
import com.jobsity.bowling.models.PinCount;
import com.jobsity.bowling.models.PlayerScore;

public abstract class BaseScoreValidator implements IGameValidator {
    private int minFrameScore;
    private int maxRegularFrameScore;
    private int maxLastFrameScore;
    private int maxLastFrameExtraAttempts;
    private int maxFramesPerPlayer;

    public BaseScoreValidator(int minFrameScore, int maxRegularFrameScore, int maxLastFrameScore, int maxLastFrameExtraAttempts, int maxFramesPerPlayer) {
        this.minFrameScore = minFrameScore;
        this.maxRegularFrameScore = maxRegularFrameScore;
        this.maxLastFrameScore = maxLastFrameScore;
        this.maxLastFrameExtraAttempts = maxLastFrameExtraAttempts;
        this.maxFramesPerPlayer = maxFramesPerPlayer;
    }

    @Override
    public void validateMatchFrame(MatchFrame matchFrame) {
        int score = matchFrame.getScore();

        assert score > minFrameScore;

        if (matchFrame instanceof FinalMatchFrame) {
            assert ((FinalMatchFrame) matchFrame).getExtraShotsCount() <= maxLastFrameExtraAttempts;
            assert matchFrame.getScore() <= maxLastFrameScore;
        } else
            assert matchFrame.getFirstShot().toInt() + matchFrame.getSecondShot().toInt() <= maxRegularFrameScore;
    }

    @Override
    public void validatePlayerScore(PlayerScore score) {
        assert score.getMatchFrames().size() < maxFramesPerPlayer;
    }

    @Override
    public boolean isStrike(MatchFrame frame) {
        return frame.getFirstShot().toInt() == maxRegularFrameScore;
    }

    @Override
    public boolean isSplit(MatchFrame frame) {
        return frame.getFirstShot().toInt() + frame.getSecondShot().toInt() == maxRegularFrameScore;
    }

    @Override
    public boolean isStrike(PinCount pinCount) {
        return pinCount.toInt() == maxRegularFrameScore;
    }

    @Override
    public boolean areSplit(PinCount count1, PinCount count2) {
        return count1.toInt() + count2.toInt() == maxRegularFrameScore;
    }
}
