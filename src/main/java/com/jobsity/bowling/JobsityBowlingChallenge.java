package com.jobsity.bowling;

import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.encoders.TenPinConsoleEncoder;
import com.jobsity.bowling.exceptions.SourceNotFoundException;
import com.jobsity.bowling.mappers.TenPinPlayerScoreMapper;
import com.jobsity.bowling.models.BowlingGame;
import com.jobsity.bowling.services.FileGameReader;
import com.jobsity.bowling.services.GameConsolePrinter;
import com.jobsity.bowling.mappers.IPlayerScoreMapper;
import com.jobsity.bowling.services.IGamePrinter;
import com.jobsity.bowling.services.IGameReader;
import com.jobsity.bowling.validators.IGameValidator;
import com.jobsity.bowling.validators.TenPinScoreValidator;

public class JobsityBowlingChallenge {
    private IGameReader gameReader;
    private IGamePrinter gamePrinter;

    public JobsityBowlingChallenge(IGameReader gameReader, IGamePrinter gamePrinter) {
        this.gameReader = gameReader;
        this.gamePrinter = gamePrinter;
    }

    public void fetchAndPrint() {
        try {
            BowlingGame game = gameReader.readGame();
            gamePrinter.printGame(game);
        } catch (SourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TODO Improve exception handling
    public static void main(String[] args) {
        IGameValidator validator = new TenPinScoreValidator();
        IPlayerScoreMapper scoreMapper = new TenPinPlayerScoreMapper(validator);
        IGameReader reader = new FileGameReader("src/main/resources/test-game.txt", scoreMapper);

        IFrameEncoder encoder = new TenPinConsoleEncoder(validator);
        IGamePrinter printer = new GameConsolePrinter(encoder);

        new JobsityBowlingChallenge(reader, printer)
                .fetchAndPrint();
    }
}
