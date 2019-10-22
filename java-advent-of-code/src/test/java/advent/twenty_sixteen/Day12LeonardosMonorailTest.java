package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day12LeonardosMonorailTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day12LeonardosMonorail("/twenty_sixteen/Day12-LeonardosMonorail-input.txt");

        assertEquals(318083, dp.getPart1Answer().intValue());
        assertEquals(9227737, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day12LeonardosMonorail("/twenty_sixteen/Day12-LeonardosMonorail-example#1.txt");

        assertEquals(42, dp.getPart1Answer().intValue());
        assertEquals(42, dp.getPart2Answer().intValue());
    }
}