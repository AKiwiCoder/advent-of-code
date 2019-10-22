package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day25TheHaltingProblemTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day25TheHaltingProblem("/twenty_seventeen/Day25-TheHaltingProblem-input.txt");

        assertEquals(2474, dp.getPart1Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day25TheHaltingProblem("/twenty_seventeen/Day25-TheHaltingProblem-example#1.txt");

        assertEquals(3, dp.getPart1Answer().intValue());
    }
}

