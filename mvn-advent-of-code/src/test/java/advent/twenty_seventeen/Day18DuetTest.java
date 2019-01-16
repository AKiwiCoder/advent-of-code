package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day18DuetTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day18Duet("/twenty_seventeen/Day18-Duet-input.txt", false);

        assertEquals(3423, dp.getPart1Answer().intValue());
        assertEquals(7493, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day18Duet("/twenty_seventeen/Day18-Duet-example#1.txt", true);

        assertEquals(4, dp.getPart1Answer().intValue());
        assertEquals(-1, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day18Duet("/twenty_seventeen/Day18-Duet-example#2.txt", false);

        assertEquals(-1, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }
}
