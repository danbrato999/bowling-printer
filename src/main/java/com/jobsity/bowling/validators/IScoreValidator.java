package com.jobsity.bowling.validators;

import com.jobsity.bowling.models.MatchFrame;
import com.jobsity.bowling.models.PlayerScore;

public interface IScoreValidator {
    void validateMatchFrame(MatchFrame matchFrame);
    void validatePlayerScore(PlayerScore score);
}
