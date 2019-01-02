package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day16AuntSueTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day16AuntSue("/twenty_fifteen/Day16-AuntSue-input.txt");

        assertEquals(373, dp.getPart1Answer().intValue());
        assertEquals(260, dp.getPart2Answer().intValue());
    }
}