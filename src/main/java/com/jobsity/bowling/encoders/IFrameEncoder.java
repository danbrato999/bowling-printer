package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.MatchFrame;

import java.util.List;

public interface IFrameEncoder {
    <T extends MatchFrame> String encode(List<T> frame, String separator);
}
