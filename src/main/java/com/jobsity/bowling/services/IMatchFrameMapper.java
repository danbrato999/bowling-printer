package com.jobsity.bowling.services;

import com.jobsity.bowling.models.MatchFrame;
import com.jobsity.bowling.models.PinCount;

import java.util.Deque;
import java.util.List;

public interface IMatchFrameMapper {
    List<MatchFrame> toMatchFrameList(Deque<PinCount> pinCounts);
}
