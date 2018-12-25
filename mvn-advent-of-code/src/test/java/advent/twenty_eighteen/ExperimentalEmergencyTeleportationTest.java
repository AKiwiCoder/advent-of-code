package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExperimentalEmergencyTeleportationTest {
    @Test
    public void checkReal() {
        ExperimentalEmergencyTeleportation cc = new ExperimentalEmergencyTeleportation("/twenty_eighteen/ExperimentalEmergencyTeleportation-input.txt");

        assertEquals(674, cc.getPart1Answer().intValue());
        assertEquals(129444177, cc.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        ExperimentalEmergencyTeleportation cc = new ExperimentalEmergencyTeleportation("/twenty_eighteen/ExperimentalEmergencyTeleportation-example#1.txt");

        assertEquals(7, cc.getPart1Answer().intValue());
        assertEquals(4, cc.getPart2Answer().longValue());
    }

    @Test
    public void checkExample2() {
        ExperimentalEmergencyTeleportation cc = new ExperimentalEmergencyTeleportation("/twenty_eighteen/ExperimentalEmergencyTeleportation-example#2.txt");

        assertEquals(6, cc.getPart1Answer().intValue());
        assertEquals(36, cc.getPart2Answer().longValue());
    }
}
