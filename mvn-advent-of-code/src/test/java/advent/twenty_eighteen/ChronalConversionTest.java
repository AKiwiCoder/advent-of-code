package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChronalConversionTest {
    @Test
    public void checkReal() {
        ChronalConversion cc = new ChronalConversion("/twenty_eighteen/ChronalConversion-input.txt");

        assertEquals(13970209, cc.getPart1Answer().intValue());
        assertEquals(6267260, cc.getPart2Answer().intValue());
    }
}
