package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day11RadioisotopeThermoelectricGeneratorsTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day11RadioisotopeThermoelectricGenerators("/twenty_sixteen/Day11-RadioisotopeThermoelectricGenerators-input.txt");

        assertEquals(31, dp.getPart1Answer().intValue());
        assertEquals(55, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day11RadioisotopeThermoelectricGenerators("/twenty_sixteen/Day11-RadioisotopeThermoelectricGenerators-example#1.txt");

        assertEquals(11, dp.getPart1Answer().intValue());
        // assertEquals(33, dp.getPart2Answer().intValue());
    }
}