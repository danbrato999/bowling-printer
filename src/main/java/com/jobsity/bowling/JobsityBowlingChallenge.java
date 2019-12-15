package com.jobsity.bowling;

import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.encoders.TenPinConsoleEncoder;
import com.jobsity.bowling.models.BowlingGame;
import com.jobsity.bowling.services.FileGameReader;
import com.jobsity.bowling.services.GamePrinter;
import com.jobsity.bowling.services.IMatchFrameMapper;
import com.jobsity.bowling.services.RecursiveMatchFrameMapper;

public class JobsityBowlingChallenge {
    public static void main(String[] args) {
        try {
            IMatchFrameMapper matchFrameMapper = new RecursiveMatchFrameMapper();
            FileGameReader gameReader = new FileGameReader("src/main/resources/test-game.txt", matchFrameMapper);
            IFrameEncoder encoder = new TenPinConsoleEncoder();
            GamePrinter printer = new GamePrinter(encoder);

            BowlingGame game = gameReader.readGame();
            printer.printGame(game);
        } catch (SourceNotFoundException e) {
            e.printStackTrace();
        }
    }
}
