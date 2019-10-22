package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day11HexEdTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day11HexEd("/twenty_seventeen/Day11-HexEd-input.txt");

        assertEquals(877, dp.getPart1Answer().intValue());
        assertEquals(1622, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day11HexEd("/twenty_seventeen/Day11-HexEd-example#1.txt");

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day11HexEd("/twenty_seventeen/Day11-HexEd-example#2.txt");

        assertEquals(0, dp.getPart1Answer().intValue());
        assertEquals(2, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        DailyProblem<Integer, Integer> dp = new Day11HexEd("/twenty_seventeen/Day11-HexEd-example#3.txt");

        assertEquals(2, dp.getPart1Answer().intValue());
        assertEquals(2, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample4() {
        DailyProblem<Integer, Integer> dp = new Day11HexEd("/twenty_seventeen/Day11-HexEd-example#4.txt");

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }
}

