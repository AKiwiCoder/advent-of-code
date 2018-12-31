package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day06ChronalCoordinatesTest {
    @Test
    public void checkReal() {
        Day06ChronalCoordinates cc = new Day06ChronalCoordinates("/twenty_eighteen/Day06-ChronalCoordinates-input.txt", 10000);

        assertEquals(5975, cc.getPart1Answer().intValue());
        assertEquals(38670, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day06ChronalCoordinates cc = new Day06ChronalCoordinates("/twenty_eighteen/Day06-ChronalCoordinates-example#1.txt", 32);

        assertEquals(17, cc.getPart1Answer().intValue());
        assertEquals(16, cc.getPart2Answer().intValue());
    }
}
