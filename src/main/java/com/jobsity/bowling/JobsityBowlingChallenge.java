package com.jobsity.bowling;

import com.jobsity.bowling.encoders.AccumulatedScoreEncoder;
import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.encoders.IScoreEncoder;
import com.jobsity.bowling.encoders.TenPinConsoleEncoder;
import com.jobsity.bowling.exceptions.SourceNotFoundException;
import com.jobsity.bowling.mappers.IPinCountMapper;
import com.jobsity.bowling.mappers.TenPinCountMapper;
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
    private static final String DEFAULT_SEPARATOR = "\t";

    private IGameReader gameReader;
    private IGamePrinter gamePrinter;

    public JobsityBowlingChallenge(IGameReader gameReader, IGamePrinter gamePrinter) {
        this.gameReader = gameReader;
        this.gamePrinter = gamePrinter;
    }

    public void fetchAndPrint() {
        BowlingGame game = gameReader.readGame();
        gamePrinter.printGame(game);
    }

    public static void main(String[] args) {
        if (args.length == 0)
            throw new SourceNotFoundException("Game file not provided");

        IGameValidator validator = new TenPinScoreValidator();
        IPlayerScoreMapper scoreMapper = new TenPinPlayerScoreMapper(validator);
        IPinCountMapper pinCountMapper = new TenPinCountMapper();
        IGameReader reader = new FileGameReader(args[0], pinCountMapper, scoreMapper);

        IFrameEncoder frameEncoder = new TenPinConsoleEncoder(validator, DEFAULT_SEPARATOR);
        IScoreEncoder scoreEncoder = new AccumulatedScoreEncoder(DEFAULT_SEPARATOR + DEFAULT_SEPARATOR);
        IGamePrinter printer = new GameConsolePrinter(frameEncoder, scoreEncoder, DEFAULT_SEPARATOR);

        new JobsityBowlingChallenge(reader, printer)
                .fetchAndPrint();
    }
}
