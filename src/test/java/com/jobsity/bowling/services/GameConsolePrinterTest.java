package com.jobsity.bowling.services;

import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.encoders.IScoreEncoder;
import com.jobsity.bowling.models.BowlingGame;
import com.jobsity.bowling.models.PlayerScore;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameConsolePrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void printGame() {
        String player = "Player1";
        String separator = "*";
        PlayerScore score = new PlayerScore(player, Collections.emptyList());

        IFrameEncoder frameEncoder = mock(IFrameEncoder.class);
        IScoreEncoder scoreEncoder = mock(IScoreEncoder.class);
        when(frameEncoder.encode(score))
                .thenReturn("FakePins");
        when(scoreEncoder.encode(score))
                .thenReturn("FakeScore");

        BowlingGame game = new BowlingGame(Collections.singletonList(score));
        GameConsolePrinter printer = new GameConsolePrinter(frameEncoder, scoreEncoder, separator);
        printer.printGame(game);

        String expectedData = "Frame**1**2**3**4**5**6**7**8**9**10\n" + player + "\nPinfalls*FakePins\nScore**FakeScore\n";
        String printedData = outContent.toString();

        assertEquals(expectedData, printedData, "Wrong data printed to Std.out");
    }
}