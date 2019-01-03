package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day19MedicineForRudolphTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day19MedicineForRudolph("/twenty_fifteen/Day19-MedicineForRudolph-input.txt");

        assertEquals(518, dp.getPart1Answer().intValue());
        assertEquals(200, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day19MedicineForRudolph("/twenty_fifteen/Day19-MedicineForRudolph-example#1.txt");

        assertEquals(4, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day19MedicineForRudolph("/twenty_fifteen/Day19-MedicineForRudolph-example#2.txt");

        assertEquals(7, dp.getPart1Answer().intValue());
        assertEquals(6, dp.getPart2Answer().intValue());
    }
}