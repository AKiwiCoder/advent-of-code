package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day22SporificaVirusTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day22SporificaVirus("/twenty_seventeen/Day22-SporificaVirus-input.txt");

        assertEquals(5259, dp.getPart1Answer().intValue());
        assertEquals(2511722, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day22SporificaVirus("/twenty_seventeen/Day22-SporificaVirus-example#1.txt");

        assertEquals(5587, dp.getPart1Answer().intValue());
        assertEquals(2511944, dp.getPart2Answer().intValue());
    }
}

