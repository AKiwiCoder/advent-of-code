package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15ScienceForHungryPeopleTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day15ScienceForHungryPeople("/twenty_fifteen/Day15-ScienceForHungryPeople-input.txt");

        assertEquals(222870, dp.getPart1Answer().intValue());
        assertEquals(117936, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day15ScienceForHungryPeople("/twenty_fifteen/Day15-ScienceForHungryPeople-example#1.txt");

        assertEquals(62842880, dp.getPart1Answer().intValue());
        assertEquals(57600000, dp.getPart2Answer().intValue());
    }
}