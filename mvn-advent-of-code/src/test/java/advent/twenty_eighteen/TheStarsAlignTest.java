package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TheStarsAlignTest {

    private static final String REAL_TXT = "" + //
            "#####,,,#,,,,#,,#####,,,,,,###,,,####,,,#,,,,,,,#####,,,######\n" + //
            "#,,,,#,,#,,,,#,,#,,,,#,,,,,,#,,,#,,,,#,,#,,,,,,,#,,,,#,,#,,,,,\n" + //
            "#,,,,#,,#,,,,#,,#,,,,#,,,,,,#,,,#,,,,,,,#,,,,,,,#,,,,#,,#,,,,,\n" + //
            "#,,,,#,,#,,,,#,,#,,,,#,,,,,,#,,,#,,,,,,,#,,,,,,,#,,,,#,,#,,,,,\n" + //
            "#####,,,######,,#####,,,,,,,#,,,#,,,,,,,#,,,,,,,#####,,,#####,\n" + //
            "#,,,,#,,#,,,,#,,#,,,,,,,,,,,#,,,#,,###,,#,,,,,,,#,,,,,,,#,,,,,\n" + //
            "#,,,,#,,#,,,,#,,#,,,,,,,,,,,#,,,#,,,,#,,#,,,,,,,#,,,,,,,#,,,,,\n" + //
            "#,,,,#,,#,,,,#,,#,,,,,,,#,,,#,,,#,,,,#,,#,,,,,,,#,,,,,,,#,,,,,\n" + //
            "#,,,,#,,#,,,,#,,#,,,,,,,#,,,#,,,#,,,##,,#,,,,,,,#,,,,,,,#,,,,,\n" + //
            "#####,,,#,,,,#,,#,,,,,,,,###,,,,,###,#,,######,,#,,,,,,,######\n";

    @Test
    public void checkReal() {
        TheStarsAlign im = new TheStarsAlign("/twenty_eighteen/TheStarsAlign-input.txt");

        assertEquals(REAL_TXT, im.getPart1Answer());
        assertEquals(10831, im.getPart2Answer().intValue());
    }

    private static final String EXAMPLE_TXT = "" + //
            "#,,,#,,###\n" + //
            "#,,,#,,,#,\n" + //
            "#,,,#,,,#,\n" + //
            "#####,,,#,\n" + //
            "#,,,#,,,#,\n" + //
            "#,,,#,,,#,\n" + //
            "#,,,#,,,#,\n" + //
            "#,,,#,,###\n";

    @Test
    public void checkExample1() {
        TheStarsAlign im = new TheStarsAlign("/twenty_eighteen/TheStarsAlign-example#1.txt");

        assertEquals(EXAMPLE_TXT, im.getPart1Answer());
        assertEquals(3, im.getPart2Answer().intValue());
    }
}
