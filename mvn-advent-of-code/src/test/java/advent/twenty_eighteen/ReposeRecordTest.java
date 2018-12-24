package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReposeRecordTest {
    @Test
    public void checkReal() {
        ReposeRecord im = new ReposeRecord("/twenty_eighteen/ReposeRecord-input.txt");

        assertEquals(19830, im.getPart1Answer().intValue());
        assertEquals(43695, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        ReposeRecord im = new ReposeRecord("/twenty_eighteen/ReposeRecord-example#1.txt");

        assertEquals(240, im.getPart1Answer().intValue());
        assertEquals(4455, im.getPart2Answer().intValue());
    }
}
