package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TheSumOfItsPartsTest {
    @Test
    public void checkReal() {
        TheSumOfItsParts im = new  TheSumOfItsParts("/twenty_eighteen/TheSumOfItsParts-input.txt", 5,60);

        assertEquals("ABLCFNSXZPRHVEGUYKDIMQTWJO", im.getPart1Answer());
        assertEquals(1157, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        TheSumOfItsParts im = new  TheSumOfItsParts("/twenty_eighteen/TheSumOfItsParts-example#1.txt", 2,0);

        assertEquals("CABDFE", im.getPart1Answer());
        assertEquals(15, im.getPart2Answer().intValue());
    }
}
