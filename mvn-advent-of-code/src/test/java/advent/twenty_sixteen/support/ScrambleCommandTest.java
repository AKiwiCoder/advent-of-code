package advent.twenty_sixteen.support;

import advent.utilities.StringUtilities;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScrambleCommandTest {
    private static void performTest(IScrambleCommand command, String expected) {
        List<Character> working = StringUtilities.toCharacterList("abcdefg");
        assertEquals("abcdefg", StringUtilities.toString(working));
        command.encode(working);
        assertEquals(expected, StringUtilities.toString(working));
        command.decode(working);
        assertEquals("abcdefg", StringUtilities.toString(working));
    }

    @Test
    public void testSwapPosition() {
        IScrambleCommand command = new ScrambleSwapPosition(1, 3);
        performTest(command, "adcbefg");
    }

    @Test
    public void testSwapLetter() {
        IScrambleCommand command = new ScrambleSwapLetter('b', 'd');
        performTest(command, "adcbefg");
    }

    @Test
    public void testRotateLeft() {
        IScrambleCommand command = new ScrambleRotateString(true, 2);
        performTest(command, "cdefgab");
    }

    @Test
    public void testRotateRight() {
        IScrambleCommand command = new ScrambleRotateString(false, 2);
        performTest(command, "fgabcde");
    }

    @Test
    public void testReversePosition() {
        IScrambleCommand command = new ScrambleReversePosition(1, 4);
        performTest(command, "aedcbfg");
    }

    @Test
    public void testMovePosition() {
        IScrambleCommand command = new ScrambleMovePosition(1, 4);
        performTest(command, "acdebfg");
    }

    @Test
    public void testRotatePosition() {
        String TEMP = "...................................";
        ScrambleRotatePosition p = new ScrambleRotatePosition('x');

        for (int i = 0; i != 8; i++) {
            String test = TEMP.substring(0, 8);
            List<Character> b = StringUtilities.toCharacterList(test);
            b.set(i, 'x');
            String before = StringUtilities.toString(b);
            p.encode(b);
            String during = StringUtilities.toString(b);
            p.decode(b);
            String after = StringUtilities.toString(b);
            if (!before.equals(after)) {
                System.out.println(i + " " + before + " " + during + " " + after);
            }
        }
    }
}