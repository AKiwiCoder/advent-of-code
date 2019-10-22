package advent.twenty_sixteen.support;

import java.util.List;

public interface IScrambleCommand {
    /*

    swap position X with position Y means that the letters at indexes X and Y (counting from 0) should be swapped.
    swap letter X with letter Y means that the letters X and Y should be swapped (regardless of where they appear in the string).
    rotate based on position of letter X means that the whole string should be rotated to the right based on the index of letter X (counting from 0) as determined before this instruction does any rotations. Once the index is determined, rotate the string to the right one time, plus a number of times equal to that index, plus one additional time if the index was at least 4.
    rotate left/right X steps means that the whole string should be rotated; for example, one right rotation would turn abcd into dabc.
    reverse positions X through Y means that the span of letters at indexes X through Y (including the letters at X and Y) should be reversed in order.
    move position X to position Y means that the letter which is at index X should be removed from the string, then inserted such that it ends up at index Y.

     */
    static IScrambleCommand PARSE(String line) {
        String[] bits = line.split("\\s+");
        if (line.startsWith("swap position")) {
            return new ScrambleSwapPosition(Integer.parseInt(bits[2]), Integer.parseInt(bits[5]));
        }
        if (line.startsWith("swap letter")) {
            return new ScrambleSwapLetter(bits[2].charAt(0), bits[5].charAt(0));
        }
        if (line.startsWith("rotate based")) {
            return new ScrambleRotatePosition(bits[6].charAt(0));
        }
        if (line.startsWith("rotate")) {
            if (line.startsWith("rotate left")) {
                return new ScrambleRotateString(true, Integer.parseInt(bits[2]));
            }
            if (line.startsWith("rotate right")) {
                return new ScrambleRotateString(false, Integer.parseInt(bits[2]));
            }
        }
        if (line.startsWith("reverse position")) {
            return new ScrambleReversePosition(Integer.parseInt(bits[2]), Integer.parseInt(bits[4]));
        }
        if (line.startsWith("move position")) {
            return new ScrambleMovePosition(Integer.parseInt(bits[2]), Integer.parseInt(bits[5]));
        }
        throw new IllegalArgumentException("Cannot parse '" + line + "'");
    }

    void encode(List<Character> working);
    void decode(List<Character> working);
}
