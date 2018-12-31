package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day23ExperimentalEmergencyTeleportationTest {
    @Test
    public void checkReal() {
        Day23ExperimentalEmergencyTeleportation cc = new Day23ExperimentalEmergencyTeleportation("/twenty_eighteen/Day23-ExperimentalEmergencyTeleportation-input.txt");

        assertEquals(674, cc.getPart1Answer().intValue());
        assertEquals(129444177, cc.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        Day23ExperimentalEmergencyTeleportation cc = new Day23ExperimentalEmergencyTeleportation("/twenty_eighteen/Day23-ExperimentalEmergencyTeleportation-example#1.txt");

        assertEquals(7, cc.getPart1Answer().intValue());
        assertEquals(4, cc.getPart2Answer().longValue());
    }

    @Test
    public void checkExample2() {
        Day23ExperimentalEmergencyTeleportation cc = new Day23ExperimentalEmergencyTeleportation("/twenty_eighteen/Day23-ExperimentalEmergencyTeleportation-example#2.txt");

        assertEquals(6, cc.getPart1Answer().intValue());
        assertEquals(36, cc.getPart2Answer().longValue());
    }
}
