package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day25LetItSnowTest {
    @Test
    public void checkReal() {
        DailyProblem<Long, Long> dp = new Day25LetItSnow("/twenty_fifteen/Day25-LetItSnow-input.txt");

        assertEquals(8997277, dp.getPart1Answer().longValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Long, Long> dp = new Day25LetItSnow("/twenty_fifteen/Day25-LetItSnow-example#1.txt");

        assertEquals(33511524, dp.getPart1Answer().longValue());
    }
}