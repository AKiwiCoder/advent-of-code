package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day16ChronalClassificationTest {
    @Test
    public void checkReal() {
        Day16ChronalClassification cc = new Day16ChronalClassification("/twenty_eighteen/Day16-ChronalClassification-input.txt");

        assertEquals(570, cc.getPart1Answer().intValue());
        assertEquals(503, cc.getPart2Answer().intValue());
    }
}
