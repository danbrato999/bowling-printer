package com.jobsity.bowling.services;

import com.jobsity.bowling.exceptions.InvalidFormatException;
import com.jobsity.bowling.exceptions.SourceNotFoundException;
import com.jobsity.bowling.mappers.IPinCountMapper;
import com.jobsity.bowling.mappers.IPlayerScoreMapper;
import com.jobsity.bowling.models.BowlingGame;
import com.jobsity.bowling.models.PinCount;
import com.jobsity.bowling.models.PlayerScore;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileGameReaderTest {

    @Test
    void mapThreePlayersGame() throws SourceNotFoundException {
        IPinCountMapper countMapper = mock(IPinCountMapper.class);
        when(countMapper.fromString(any())).thenReturn(new PinCount("X", 0));

        IPlayerScoreMapper scoreMapper = mock(IPlayerScoreMapper.class);
        when(scoreMapper.toPlayerScore(any(), any())).thenReturn(new PlayerScore("FakePlayer", Collections.emptyList()));

        FileGameReader reader = new FileGameReader("src/test/resources/3-players-incomplete-game.txt", countMapper, scoreMapper);
        BowlingGame game = reader.readGame();

        assertEquals(3, game.getPlayerScores().size());
    }

    @Test
    void mapInvalidFile() {
        IPinCountMapper countMapper = mock(IPinCountMapper.class);
        IPlayerScoreMapper scoreMapper = mock(IPlayerScoreMapper.class);

        FileGameReader reader = new FileGameReader("src/test/resources/invalid-format-game.txt", countMapper, scoreMapper);

        assertThrows(InvalidFormatException.class, reader::readGame);
    }

    @Test
    void missingFileException() {
        IPinCountMapper countMapper = mock(IPinCountMapper.class);
        IPlayerScoreMapper scoreMapper = mock(IPlayerScoreMapper.class);

        FileGameReader reader = new FileGameReader("src/test/resources/non-existent-game.txt", countMapper, scoreMapper);
        assertThrows(SourceNotFoundException.class, reader::readGame);
    }
}