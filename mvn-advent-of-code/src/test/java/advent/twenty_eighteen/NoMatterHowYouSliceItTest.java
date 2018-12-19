package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoMatterHowYouSliceItTest {
    @Test
    public void checkReal() {
        NoMatterHowYouSliceIt im = new  NoMatterHowYouSliceIt("/twenty_eighteen/NoMatterHowYouSliceIt-input.txt");

        assertEquals(110389, im.getPart1Answer().intValue());
        assertEquals(552, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        NoMatterHowYouSliceIt im = new  NoMatterHowYouSliceIt("/twenty_eighteen/NoMatterHowYouSliceIt-example#1.txt");

        assertEquals(4, im.getPart1Answer().intValue());
        assertEquals(3, im.getPart2Answer().intValue());
    }
}
