package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day23OpeningTheTuringLockTest {
    @Test
    public void checkReal() {
        DailyProblem<Pair<Integer, Integer>, Pair<Integer, Integer>> dp = new Day23OpeningTheTuringLock("/twenty_fifteen/Day23-OpeningTheTuringLock-input.txt");

        assertEquals(184, dp.getPart1Answer().getSecond().intValue());
        assertEquals(231, dp.getPart2Answer().getSecond().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Pair<Integer, Integer>, Pair<Integer, Integer>> dp = new Day23OpeningTheTuringLock("/twenty_fifteen/Day23-OpeningTheTuringLock-example#1.txt");

        assertEquals(2, dp.getPart1Answer().getFirst().intValue());
        assertEquals(7, dp.getPart2Answer().getFirst().intValue());
    }
}