package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.IScoredFrame;

import java.util.List;

public class AccumulatedScoreEncoder implements IScoreEncoder {
    @Override
    public <T extends IScoredFrame> String encode(List<T> scoredFrames, String separator) {
        StringBuilder builder = new StringBuilder();
        int currentScore = 0;
        for (IScoredFrame frame: scoredFrames) {
            currentScore += frame.getFrameScore();
            builder
                    .append(currentScore)
                    .append(separator)
                    .append(separator);
        }

        return builder.toString();
    }
}
