package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.FinalMatchFrame;
import com.jobsity.bowling.models.MatchFrame;
import com.jobsity.bowling.models.PinCount;
import com.jobsity.bowling.validators.IFrameValidator;

import java.util.List;
import java.util.stream.Collectors;

public class TenPinConsoleEncoder implements IFrameEncoder {
    private IFrameValidator validator;

    public TenPinConsoleEncoder(IFrameValidator validator) {
        this.validator = validator;
    }

    @Override
    public<T extends MatchFrame> String encode(List<T> frames, String separator) {
        return frames
                .stream()
                .map(frame -> {
                            if (frame instanceof FinalMatchFrame)
                                return encodeFinalFrame(new StringBuilder(), frame.getShots(), separator);
                            else
                                return encodeFrame(frame, separator);
                        }
                ).collect(Collectors.joining());
    }

    private String encodeFrame(MatchFrame frame, String separator) {
        return validator.isStrike(frame) ? String.format("%1$sX%1$s", separator) : splitValidator(frame.getShots(), separator);
    }

    private String encodeFinalFrame(StringBuilder builder, List<PinCount> pinCounts, String separator) {
        if (pinCounts.isEmpty())
            return builder.toString().trim();
        else  {
            if (validator.isStrike(pinCounts.get(0)))
                builder.append("X").append(separator);
            else if (pinCounts.size() > 1){
                builder.append(splitValidator(pinCounts, separator));
                pinCounts.remove(0);
            } else
                builder.append(pinCounts.get(0).getValue());
            pinCounts.remove(0);
            return encodeFinalFrame(builder, pinCounts, separator);
        }
    }

    private String splitValidator(List<PinCount> pinCounts, String separator) {
        if (validator.areSplit(pinCounts.get(0), pinCounts.get(1)))
            return String.format("%1$s%2$s/%2$s", pinCounts.get(0).getValue(), separator);
        else
            return String.format("%1$s%2$s%3$s%2$s", pinCounts.get(0).getValue(), separator, pinCounts.get(1).getValue());
    }
}
