package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.MatchFrame;

public interface IFrameEncoder {
    <T extends MatchFrame> String encode(T frame);
}
