package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01NotQuiteLispTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-input.txt");

        assertEquals(74, dp.getPart1Answer().intValue());
        assertEquals(1795, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#1.txt");

        assertEquals(0, dp.getPart1Answer().intValue());
        assertEquals(0, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#2.txt");

        assertEquals(0, dp.getPart1Answer().intValue());
        assertEquals(0, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#3.txt");

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(0, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample4() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#4.txt");

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(0, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample5() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#5.txt");

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(1, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample6() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#6.txt");

        assertEquals(-1, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample7() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#7.txt");

        assertEquals(-1, dp.getPart1Answer().intValue());
        assertEquals(1, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample8() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#8.txt");

        assertEquals(-3, dp.getPart1Answer().intValue());
        assertEquals(1, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample9() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#9.txt");

        assertEquals(-3, dp.getPart1Answer().intValue());
        assertEquals(1, dp.getPart2Answer().intValue());
    }
}