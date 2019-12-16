package com.jobsity.bowling;

import com.jobsity.bowling.exceptions.InvalidFormatException;
import com.jobsity.bowling.exceptions.InvalidGameException;
import com.jobsity.bowling.exceptions.SourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JobsityBowlingChallengeTest {
    private static final String FRAME = "Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void printNothing() {
        assertThrows(SourceNotFoundException.class, () ->
                JobsityBowlingChallenge.main(new String[0]));
    }

    @Test
    void printMissingFile() {
        assertThrows(SourceNotFoundException.class, () ->
                JobsityBowlingChallenge.main(new String[]{"src/test/resources/missing-file.txt"}));
    }

    @Test
    void printFileWithWrongFormat() {
        assertThrows(InvalidFormatException.class, () ->
                JobsityBowlingChallenge.main(new String[]{"src/test/resources/invalid-format-game.txt"}));
    }

    @Test
    void printFileWithIncorrectData() {
        assertThrows(InvalidGameException.class, () ->
                JobsityBowlingChallenge.main(new String[]{"src/test/resources/3-players-incomplete-game.txt"}));
    }

    @Test
    void printPerfectGame() {
        JobsityBowlingChallenge.main(new String[]{"src/test/resources/perfect-game.txt"});
        String expectedPrint = FRAME + "Carl\nPinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\n" +
                "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n";

        String printed = outContent.toString();
        assertEquals(expectedPrint, printed);
    }

    @Test
    void printRegularGame() {
        JobsityBowlingChallenge.main(new String[]{"src/test/resources/test-game.txt"});
        String expectedPrint = FRAME + "Jeff\nPinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n" +
                "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n" +
                "John\nPinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n" +
                "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n";
        String printed = outContent.toString();

        assertEquals(expectedPrint, printed);
    }

    @Test
    void printZeroGame() {
        JobsityBowlingChallenge.main(new String[]{"src/test/resources/fouls-game.txt"});
        String expectedPrint = FRAME + "Paul\nPinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
                "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n";
        String printed = outContent.toString();

        assertEquals(expectedPrint, printed);
    }
}