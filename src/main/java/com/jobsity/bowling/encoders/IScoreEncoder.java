package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.IScoredFrame;

import java.util.List;

public interface IScoreEncoder {
    <T extends IScoredFrame> String encode(List<T> scoredFrames, String separator);
}
