package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChronalClassificationTest {
    @Test
    public void checkReal() {
        ChronalClassification cc = new ChronalClassification("/twenty_eighteen/ChronalClassification-input.txt");

        assertEquals(570, cc.getPart1Answer().intValue());
        assertEquals(503, cc.getPart2Answer().intValue());
    }
}
