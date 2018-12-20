package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChronalCoordinatesTest {
    @Test
    public void checkReal() {
        ChronalCoordinates cc = new  ChronalCoordinates("/twenty_eighteen/ChronalCoordinates-input.txt", 10000);

        assertEquals(5975, cc.getPart1Answer().intValue());
        assertEquals(38670, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        ChronalCoordinates cc = new  ChronalCoordinates("/twenty_eighteen/ChronalCoordinates-example#1.txt", 32);

        assertEquals(17, cc.getPart1Answer().intValue());
        assertEquals(16, cc.getPart2Answer().intValue());
    }
}
