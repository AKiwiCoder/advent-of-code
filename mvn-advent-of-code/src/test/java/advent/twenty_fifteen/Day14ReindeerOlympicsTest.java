package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day14ReindeerOlympicsTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day14ReindeerOlympics("/twenty_fifteen/Day14-ReindeerOlympics-input.txt", 2503);

        assertEquals(2640, dp.getPart1Answer().intValue());
        assertEquals(1102, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day14ReindeerOlympics("/twenty_fifteen/Day14-ReindeerOlympics-example#1.txt", 1000);

        assertEquals(1120, dp.getPart1Answer().intValue());
        assertEquals(689, dp.getPart2Answer().intValue());
    }
}