package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day13PacketScannersTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day13PacketScanners("/twenty_seventeen/Day13-PacketScanners-input.txt");

        assertEquals(2604, dp.getPart1Answer().intValue());
        assertEquals(3941460, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day13PacketScanners("/twenty_seventeen/Day13-PacketScanners-example#1.txt");

        assertEquals(24, dp.getPart1Answer().intValue());
        assertEquals(10, dp.getPart2Answer().intValue());
    }
}

