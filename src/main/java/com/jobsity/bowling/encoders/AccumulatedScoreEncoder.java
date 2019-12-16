package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.IScoredFrame;
import com.jobsity.bowling.models.PlayerScore;

public class AccumulatedScoreEncoder implements IScoreEncoder {
    private String separator;

    public AccumulatedScoreEncoder(String separator) {
        this.separator = separator;
    }

    @Override
    public String encode(PlayerScore score) {
        StringBuilder builder = new StringBuilder();
        int currentScore = 0;
        for (IScoredFrame frame: score.getMatchFrames()) {
            currentScore += frame.getFrameScore();
            builder
                    .append(currentScore)
                    .append(separator);
        }


        if (builder.length() > 0) builder.delete(builder.lastIndexOf(separator), builder.length());
        return builder.toString();
    }
}
