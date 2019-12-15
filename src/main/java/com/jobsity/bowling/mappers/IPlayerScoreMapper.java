package com.jobsity.bowling.mappers;

import com.jobsity.bowling.models.PinCount;
import com.jobsity.bowling.models.PlayerScore;

import java.util.Deque;

public interface IPlayerScoreMapper {
    PlayerScore toPlayerScore(String playerName, Deque<PinCount> pinCounts);
}
