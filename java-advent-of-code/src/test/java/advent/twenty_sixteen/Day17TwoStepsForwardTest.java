package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class Day17TwoStepsForwardTest {
    @Test
    public void checkReal() throws NoSuchAlgorithmException {
        DailyProblem<String, Integer> dp = new Day17TwoStepsForward("gdjjyniy");

        assertEquals("DUDDRLRRRD", dp.getPart1Answer());
        assertEquals(578, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() throws NoSuchAlgorithmException {
        DailyProblem<String, Integer> dp = new Day17TwoStepsForward("ihgpwlah");

        assertEquals("DDRRRD", dp.getPart1Answer());
        assertEquals(370, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() throws NoSuchAlgorithmException {
        DailyProblem<String, Integer> dp = new Day17TwoStepsForward("kglvqrro");

        assertEquals("DDUDRLRRUDRD", dp.getPart1Answer());
        assertEquals(492, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() throws NoSuchAlgorithmException {
        DailyProblem<String, Integer> dp = new Day17TwoStepsForward("ulqzkmiv");

        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", dp.getPart1Answer());
        assertEquals(830, dp.getPart2Answer().intValue());
    }
}