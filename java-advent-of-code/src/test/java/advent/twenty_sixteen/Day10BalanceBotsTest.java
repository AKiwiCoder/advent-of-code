package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day10BalanceBotsTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day10BalanceBots("/twenty_sixteen/Day10-BalanceBots-input.txt", 17, 61);

        assertEquals(47, dp.getPart1Answer().intValue());
        assertEquals(2666, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day10BalanceBots("/twenty_sixteen/Day10-BalanceBots-example#1.txt", 2,5);

        assertEquals(2, dp.getPart1Answer().intValue());
        assertEquals(30, dp.getPart2Answer().intValue());
    }
}