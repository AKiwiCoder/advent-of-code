package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day20ParticleSwarmTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day20ParticleSwarm("/twenty_seventeen/Day20-ParticleSwarm-input.txt");

        assertEquals(344, dp.getPart1Answer().intValue());
        assertEquals(404, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day20ParticleSwarm("/twenty_seventeen/Day20-ParticleSwarm-example#1.txt");

        assertEquals(0, dp.getPart1Answer().intValue());
        assertEquals(2, dp.getPart2Answer().intValue());
    }


    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day20ParticleSwarm("/twenty_seventeen/Day20-ParticleSwarm-example#2.txt");

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(1, dp.getPart2Answer().intValue());
    }
}

