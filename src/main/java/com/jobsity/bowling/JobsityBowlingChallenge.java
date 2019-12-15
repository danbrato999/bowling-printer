package com.jobsity.bowling;

import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.encoders.TenPinConsoleEncoder;
import com.jobsity.bowling.exceptions.SourceNotFoundException;
import com.jobsity.bowling.mappers.TenPinPlayerScoreMapper;
import com.jobsity.bowling.models.BowlingGame;
import com.jobsity.bowling.services.FileGameReader;
import com.jobsity.bowling.services.GamePrinter;
import com.jobsity.bowling.mappers.IPlayerScoreMapper;
import com.jobsity.bowling.validators.IScoreValidator;
import com.jobsity.bowling.validators.TenPinScoreValidator;

public class JobsityBowlingChallenge {
    public static void main(String[] args) {
        try {
            IScoreValidator validator = new TenPinScoreValidator();
            IPlayerScoreMapper scoreMapper = new TenPinPlayerScoreMapper(validator);
            FileGameReader gameReader = new FileGameReader("src/main/resources/test-game.txt", scoreMapper);
            IFrameEncoder encoder = new TenPinConsoleEncoder();
            GamePrinter printer = new GamePrinter(encoder);

            BowlingGame game = gameReader.readGame();
            printer.printGame(game);
        } catch (SourceNotFoundException e) {
            e.printStackTrace();
        }
    }
}
