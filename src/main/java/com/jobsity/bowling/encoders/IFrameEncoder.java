package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.PlayerScore;

public interface IFrameEncoder {
    String encode(PlayerScore score, String separator);
}
