package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day19GoWithTheFlowTest {
    @Test
    public void checkReal() {
        Day19GoWithTheFlow cc = new Day19GoWithTheFlow("/twenty_eighteen/Day19-GoWithTheFlow-input.txt");

        assertEquals(1694, cc.getPart1Answer().intValue());
        assertEquals(18964204, cc.getPart2Answer().intValue());
    }
}
