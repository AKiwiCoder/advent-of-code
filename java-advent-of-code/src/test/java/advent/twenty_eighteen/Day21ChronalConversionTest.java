package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day21ChronalConversionTest {
    @Test
    public void checkReal() {
        Day21ChronalConversion cc = new Day21ChronalConversion("/twenty_eighteen/Day21-ChronalConversion-input.txt");

        assertEquals(13970209, cc.getPart1Answer().intValue());
        assertEquals(6267260, cc.getPart2Answer().intValue());
    }
}
