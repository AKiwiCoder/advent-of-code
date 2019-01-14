package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day05AMazeOfTwistyTrampolinesAllAlikeTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day05AMazeOfTwistyTrampolinesAllAlike("/twenty_seventeen/Day05-AMazeOfTwistyTrampolinesAllAlike-input.txt");

        assertEquals(381680, dp.getPart1Answer().intValue());
        assertEquals(29717847, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day05AMazeOfTwistyTrampolinesAllAlike("/twenty_seventeen/Day05-AMazeOfTwistyTrampolinesAllAlike-example#1.txt");

        assertEquals(5, dp.getPart1Answer().intValue());
        assertEquals(10, dp.getPart2Answer().intValue());
    }
}