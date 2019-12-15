package com.jobsity.bowling.encoders;

import com.jobsity.bowling.encoders.IFrameEncoder;
import com.jobsity.bowling.models.FinalMatchFrame;
import com.jobsity.bowling.models.MatchFrame;

public class TenPinConsoleEncoder implements IFrameEncoder {

    @Override
    public<T extends MatchFrame> String encode(T frame) {
        if (frame instanceof FinalMatchFrame)
            return encodeFinal((FinalMatchFrame) frame);
        else
            return encodeFrame(frame);
    }

    public String encodeFrame(MatchFrame frame) {
        if (frame.getFirstShot() == 10)
            return "\tX\t";
        else if (frame.getFirstShot() + frame.getSecondShot() == 10)
            return String.format("%d\t/\t", frame.getFirstShot());
        else
            return String.format("%d\t%d\t", frame.getFirstShot(), frame.getSecondShot());
    }

    public String encodeFinal(FinalMatchFrame frame) {
        StringBuilder builder = new StringBuilder();
        if (frame.getFirstShot() == 10) {
            builder.append("X\t");

            if (frame.getSecondShot() == 10)
                builder.append("X\t");
            else
                builder.append(String.format("%d\t", frame.getSecondShot()));

            if (frame.getThirdShot() == 10)
                builder.append("X");
            else if (frame.getSecondShot() + frame.getThirdShot() == 10)
                builder.append("/");
            else
                builder.append(frame.getThirdShot());
        } else {
            builder.append(String.format("%d\t", frame.getFirstShot()));

            if (frame.getFirstShot() + frame.getSecondShot() == 10)
                builder.append("/");
            else
                builder.append(frame.getSecondShot());
        }
        return builder.toString();
    }
}
