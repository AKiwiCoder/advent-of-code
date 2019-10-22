package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day11ChronalChargeTest {
    @Test
    public void checkReal() {
        Day11ChronalCharge im = new Day11ChronalCharge("/twenty_eighteen/Day11-ChronalCharge-input.txt");

        assertEquals("33,54", im.getPart1Answer());
        assertEquals("232,289,8", im.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        Day11ChronalCharge im = new Day11ChronalCharge("/twenty_eighteen/Day11-ChronalCharge-example#1.txt");

        assertEquals("33,45", im.getPart1Answer());
        assertEquals("90,269,16", im.getPart2Answer());
    }

    @Test
    public void checkExample2() {
        Day11ChronalCharge im = new Day11ChronalCharge("/twenty_eighteen/Day11-ChronalCharge-example#2.txt");

        assertEquals("21,61", im.getPart1Answer());
        assertEquals("232,251,12", im.getPart2Answer());
    }
}
