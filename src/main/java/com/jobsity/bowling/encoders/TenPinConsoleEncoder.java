package com.jobsity.bowling.encoders;

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
        if (frame.getFirstShot().toInt() == 10)
            return "\tX\t";
        else if (frame.getFirstShot().toInt() + frame.getSecondShot().toInt() == 10)
            return String.format("%s\t/\t", frame.getFirstShot());
        else
            return String.format("%s\t%s\t", frame.getFirstShot(), frame.getSecondShot());
    }

    public String encodeFinal(FinalMatchFrame frame) {
        StringBuilder builder = new StringBuilder();
        if (frame.getFirstShot().toInt() == 10) {
            builder.append("X\t");

            if (frame.getSecondShot().toInt() == 10)
                builder.append("X\t");
            else
                builder.append(String.format("%s\t", frame.getSecondShot()));

            if (frame.getThirdShot().toInt() == 10)
                builder.append("X");
            else if (frame.getSecondShot().toInt() + frame.getThirdShot().toInt() == 10)
                builder.append("/");
            else
                builder.append(frame.getThirdShot());
        } else {
            builder.append(String.format("%s\t", frame.getFirstShot()));

            if (frame.getFirstShot().toInt() + frame.getSecondShot().toInt() == 10)
                builder.append("/");
            else
                builder.append(frame.getSecondShot());
        }
        return builder.toString();
    }
}
