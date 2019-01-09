package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15TimingIsEverythingTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day15TimingIsEverything("/twenty_sixteen/Day15-TimingIsEverything-input.txt");

        assertEquals(148737, dp.getPart1Answer().intValue());
        assertEquals(2353212, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day15TimingIsEverything("/twenty_sixteen/Day15-TimingIsEverything-example#1.txt");

        assertEquals(5, dp.getPart1Answer().intValue());
        assertEquals(85, dp.getPart2Answer().intValue());
    }
}