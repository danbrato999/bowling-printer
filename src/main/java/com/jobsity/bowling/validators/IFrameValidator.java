package com.jobsity.bowling.validators;

import com.jobsity.bowling.models.MatchFrame;
import com.jobsity.bowling.models.PinCount;

public interface IFrameValidator {
    void validateMatchFrame(MatchFrame matchFrame);
    boolean isStrike(MatchFrame frame);
    boolean isSplit(MatchFrame frame);
    boolean isStrike(PinCount pinCount);
    boolean areSplit(PinCount count1, PinCount count2);
}
