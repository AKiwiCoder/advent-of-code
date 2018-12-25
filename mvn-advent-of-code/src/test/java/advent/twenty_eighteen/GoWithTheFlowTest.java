package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoWithTheFlowTest {
    @Test
    public void checkReal() {
        GoWithTheFlow cc = new GoWithTheFlow("/twenty_eighteen/GoWithTheFlow-input.txt");

        assertEquals(1694, cc.getPart1Answer().intValue());
        assertEquals(18964204, cc.getPart2Answer().intValue());
    }
}
