package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day03PerfectlySphericalHousesInAVacuumTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day03PerfectlySphericalHousesInAVacuum("/twenty_fifteen/Day03-PerfectlySphericalHousesInAVacuum-input.txt");

        assertEquals(2572, dp.getPart1Answer().intValue());
        assertEquals(2631, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day03PerfectlySphericalHousesInAVacuum("/twenty_fifteen/Day03-PerfectlySphericalHousesInAVacuum-example#1.txt");

        assertEquals(2, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day03PerfectlySphericalHousesInAVacuum("/twenty_fifteen/Day03-PerfectlySphericalHousesInAVacuum-example#2.txt");

        assertEquals(4, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        DailyProblem<Integer, Integer> dp = new Day03PerfectlySphericalHousesInAVacuum("/twenty_fifteen/Day03-PerfectlySphericalHousesInAVacuum-example#3.txt");

        assertEquals(2, dp.getPart1Answer().intValue());
        assertEquals(11, dp.getPart2Answer().intValue());
    }
}