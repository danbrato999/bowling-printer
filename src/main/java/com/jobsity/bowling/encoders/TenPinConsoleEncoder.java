package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.FinalMatchFrame;
import com.jobsity.bowling.models.MatchFrame;
import com.jobsity.bowling.validators.IFrameValidator;

public class TenPinConsoleEncoder implements IFrameEncoder {
    private IFrameValidator validator;

    public TenPinConsoleEncoder(IFrameValidator validator) {
        this.validator = validator;
    }

    @Override
    public<T extends MatchFrame> String encode(T frame) {
        if (frame instanceof FinalMatchFrame)
            return encodeFinal((FinalMatchFrame) frame);
        else
            return encodeFrame(frame);
    }

    public String encodeFrame(MatchFrame frame) {
        if (validator.isStrike(frame))
            return "\tX\t";
        else if (validator.isSplit(frame))
            return String.format("%s\t/\t", frame.getFirstShot());
        else
            return String.format("%s\t%s\t", frame.getFirstShot(), frame.getSecondShot());
    }

    public String encodeFinal(FinalMatchFrame frame) {
        StringBuilder builder = new StringBuilder();
        if (validator.isStrike(frame.getFirstShot())) {
            builder.append("X\t");

            if (validator.isStrike(frame.getSecondShot()))
                builder.append("X\t");
            else
                builder.append(String.format("%s\t", frame.getSecondShot()));

            if (validator.isStrike(frame.getThirdShot()))
                builder.append("X");
            else if (validator.areSplit(frame.getSecondShot(), frame.getThirdShot()))
                builder.append("/");
            else
                builder.append(frame.getThirdShot());
        } else {
            builder.append(String.format("%s\t", frame.getFirstShot()));

            if (validator.isSplit(frame))
                builder.append("/");
            else
                builder.append(frame.getSecondShot());
        }
        return builder.toString();
    }
}
