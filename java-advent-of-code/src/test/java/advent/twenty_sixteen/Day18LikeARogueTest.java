package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day18LikeARogueTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day18LikeARogue("/twenty_sixteen/Day18-LikeARogue-input.txt", 40, 400000);

        assertEquals(1963, dp.getPart1Answer().intValue());
        assertEquals(20009568, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day18LikeARogue("/twenty_sixteen/Day18-LikeARogue-example#1.txt", 3, 30);

        assertEquals(6, dp.getPart1Answer().intValue());
        assertEquals(46, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day18LikeARogue("/twenty_sixteen/Day18-LikeARogue-example#2.txt", 10, 100);

        assertEquals(38, dp.getPart1Answer().intValue());
        assertEquals(478, dp.getPart2Answer().intValue());
    }
}