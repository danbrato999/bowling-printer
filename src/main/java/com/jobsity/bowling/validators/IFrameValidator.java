package com.jobsity.bowling.validators;

import com.jobsity.bowling.models.IScoredFrame;
import com.jobsity.bowling.models.PinCount;

public interface IFrameValidator {
    void validateMatchFrame(IScoredFrame frame);
    boolean isStrike(IScoredFrame frame);
    boolean isSplit(IScoredFrame frame);
    boolean isStrike(PinCount pinCount);
    boolean areSplit(PinCount count1, PinCount count2);
}
