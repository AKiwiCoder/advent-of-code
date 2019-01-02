package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day13KnightsOfTheDinnerTableTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day13KnightsOfTheDinnerTable("/twenty_fifteen/Day13-KnightsOfTheDinnerTable-input.txt");

        assertEquals(664, dp.getPart1Answer().intValue());
        assertEquals(640, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day13KnightsOfTheDinnerTable("/twenty_fifteen/Day13-KnightsOfTheDinnerTable-example#1.txt");

        assertEquals(330, dp.getPart1Answer().intValue());
        assertEquals(286, dp.getPart2Answer().intValue());
    }
}