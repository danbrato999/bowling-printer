package com.jobsity.bowling.services;

import com.jobsity.bowling.exceptions.SourceNotFoundException;
import com.jobsity.bowling.models.BowlingGame;

public interface IGameReader {
    BowlingGame readGame() throws SourceNotFoundException;
}
