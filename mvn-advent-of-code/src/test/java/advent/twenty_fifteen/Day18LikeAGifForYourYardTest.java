package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day18LikeAGifForYourYardTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day18LikeAGifForYourYard("/twenty_fifteen/Day18-LikeAGifForYourYard-input.txt", 100, 100);

        assertEquals(814, dp.getPart1Answer().intValue());
        assertEquals(924, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day18LikeAGifForYourYard("/twenty_fifteen/Day18-LikeAGifForYourYard-example#1.txt", 4, 5);

        assertEquals(4, dp.getPart1Answer().intValue());
        assertEquals(17, dp.getPart2Answer().intValue());
    }
}