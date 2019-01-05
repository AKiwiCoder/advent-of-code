package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day03SquaresWithThreeSidesTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day03SquaresWithThreeSides("/twenty_sixteen/Day03-SquaresWithThreeSides-input.txt");

        assertEquals(869, dp.getPart1Answer().intValue());
        assertEquals(1544, dp.getPart2Answer().intValue());
    }
}