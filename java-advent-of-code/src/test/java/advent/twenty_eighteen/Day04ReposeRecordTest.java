package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day04ReposeRecordTest {
    @Test
    public void checkReal() {
        Day04ReposeRecord im = new Day04ReposeRecord("/twenty_eighteen/Day04-ReposeRecord-input.txt");

        assertEquals(19830, im.getPart1Answer().intValue());
        assertEquals(43695, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day04ReposeRecord im = new Day04ReposeRecord("/twenty_eighteen/Day04-ReposeRecord-example#1.txt");

        assertEquals(240, im.getPart1Answer().intValue());
        assertEquals(4455, im.getPart2Answer().intValue());
    }
}
