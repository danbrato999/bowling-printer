package com.jobsity.bowling.encoders;

import com.jobsity.bowling.models.*;
import com.jobsity.bowling.validators.IFrameValidator;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static com.jobsity.bowling.encoders.EncoderTestConstants.*;

class TenPinConsoleEncoderTest {

    @Test
    void encodeBasic() {
        IFrameValidator validator = mock(IFrameValidator.class);
        when(validator.isStrike(any(IScoredFrame.class))).thenReturn(false);
        when(validator.areSplit(any(), any())).thenReturn(false);

        TenPinConsoleEncoder encoder = new TenPinConsoleEncoder(validator);
        String encoded = encoder.encode(INCOMPLETE_SCORE, DEFAULT_SEPARATOR);

        assertEquals("8|2|0|5", encoded);
    }

    @Test
    void encodeStrikes() {
        IFrameValidator validator = mock(IFrameValidator.class);
        when(validator.isStrike(any(IScoredFrame.class))).thenReturn(true);

        TenPinConsoleEncoder encoder = new TenPinConsoleEncoder(validator);
        String encoded = encoder.encode(INCOMPLETE_SCORE, DEFAULT_SEPARATOR);

        assertEquals("|X||X", encoded);
    }

    @Test
    void encodeSplits() {
        IFrameValidator validator = mock(IFrameValidator.class);
        when(validator.isStrike(any(IScoredFrame.class))).thenReturn(false);
        when(validator.areSplit(any(), any())).thenReturn(true);

        TenPinConsoleEncoder encoder = new TenPinConsoleEncoder(validator);
        String encoded = encoder.encode(INCOMPLETE_SCORE, DEFAULT_SEPARATOR);
        assertEquals("8|/|0|/", encoded);
    }

    @Test
    void encodeFinalFrame() {
        PinCount count1 = countMapper.fromString("10");
        PinCount count2 = countMapper.fromString("F");
        PinCount count3 = countMapper.fromString("6");
        List<MatchFrame> matchFrames = Collections.singletonList(new FinalMatchFrame(count1, count2)
                .addShot(count3));
        PlayerScore score = new PlayerScore("", matchFrames);

        IFrameValidator validator = mock(IFrameValidator.class);
        when(validator.isStrike(any(PinCount.class))).thenReturn(false);
        when(validator.isStrike(count1)).thenReturn(true);
        when(validator.areSplit(count2, count3)).thenReturn(false);


        TenPinConsoleEncoder encoder = new TenPinConsoleEncoder(validator);
        String encoded = encoder.encode(score, DEFAULT_SEPARATOR);

        assertEquals("X|F|6", encoded);

        IFrameValidator validator1 = mock(IFrameValidator.class);
        when(validator1.isStrike(any(PinCount.class))).thenReturn(false);
        when(validator1.areSplit(any(), any())).thenReturn(true);

        TenPinConsoleEncoder encoder1 = new TenPinConsoleEncoder(validator1);
        String encoded1 = encoder1.encode(score, DEFAULT_SEPARATOR);

        assertEquals("10|/6", encoded1);
    }
}