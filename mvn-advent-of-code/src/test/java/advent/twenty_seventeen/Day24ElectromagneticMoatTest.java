package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day24ElectromagneticMoatTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day24ElectromagneticMoat("/twenty_seventeen/Day24-ElectromagneticMoat-input.txt");

        assertEquals(1511, dp.getPart1Answer().intValue());
        assertEquals(1471, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day24ElectromagneticMoat("/twenty_seventeen/Day24-ElectromagneticMoat-example#1.txt");

        assertEquals(31, dp.getPart1Answer().intValue());
        assertEquals(19, dp.getPart2Answer().intValue());
    }
}

