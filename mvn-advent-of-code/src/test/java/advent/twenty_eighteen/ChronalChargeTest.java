package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChronalChargeTest {
    @Test
    public void checkReal() {
        ChronalCharge im = new ChronalCharge("/twenty_eighteen/ChronalCharge-input.txt");

        assertEquals("33,54", im.getPart1Answer());
        assertEquals("232,289,8", im.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        ChronalCharge im = new ChronalCharge("/twenty_eighteen/ChronalCharge-example#1.txt");

        assertEquals("33,45", im.getPart1Answer());
        assertEquals("90,269,16", im.getPart2Answer());
    }

    @Test
    public void checkExample2() {
        ChronalCharge im = new ChronalCharge("/twenty_eighteen/ChronalCharge-example#2.txt");

        assertEquals("21,61", im.getPart1Answer());
        assertEquals("232,251,12", im.getPart2Answer());
    }
}
