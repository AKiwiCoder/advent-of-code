package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10TheStarsAlignTest {

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
        Day10TheStarsAlign im = new Day10TheStarsAlign("/twenty_eighteen/Day10-TheStarsAlign-input.txt");

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
        Day10TheStarsAlign im = new Day10TheStarsAlign("/twenty_eighteen/Day10-TheStarsAlign-example#1.txt");

        assertEquals(EXAMPLE_TXT, im.getPart1Answer());
        assertEquals(3, im.getPart2Answer().intValue());
    }
}
