package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day25ClockSignalTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day25ClockSignal("/twenty_sixteen/Day25-ClockSignal-input.txt");

        assertEquals(189, dp.getPart1Answer().intValue());
    }
}