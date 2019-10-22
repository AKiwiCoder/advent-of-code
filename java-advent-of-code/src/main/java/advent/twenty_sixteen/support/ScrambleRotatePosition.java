package advent.twenty_sixteen.support;

import advent.utilities.StringUtilities;

import java.util.List;

//  rotate based on position of letter X means that the whole string should be rotated to the right based on
//  the index of letter X (counting from 0) as determined before this instruction does any rotations. Once
//  the index is determined, rotate the string to the right one time, plus a number of times equal to that
//  index, plus one additional time if the index was at least 4.
public class ScrambleRotatePosition implements IScrambleCommand {
    private final Character x;

    public ScrambleRotatePosition(Character x) {
        this.x = x;
    }

    private int stepCalculate(int i) {
        return i + 1 + ((i >= 4) ? 1 : 0);
    }

    @Override
    public void encode(List<Character> working) {
        int steps = stepCalculate(working.indexOf(x));
        ScrambleRotateString worker = new ScrambleRotateString(false, steps);
        worker.encode(working);
    }

    @Override
    public void decode(List<Character> working) {
        int i = working.indexOf(x);
        int steps = calculateSteps(working.size(), i);
        if (steps < 0) {
            ScrambleRotateString worker = new ScrambleRotateString(false, -steps);
            worker.decode(working);
        } else if (steps > 0) {
            ScrambleRotateString worker = new ScrambleRotateString(true, steps);
            worker.decode(working);
        }
    }

    private static int[] SIZE_8_MAPPINGS = new int[]{-1, -1, 2, -2, 1, -3, 0, 4};

    private int calculateSteps(int size, int currentIndex) {
        if (size == 8) {
            return SIZE_8_MAPPINGS[currentIndex];
        }
        throw new IllegalArgumentException("Cannot figure out mappings");
    }

    @Override
    public String toString() {
        return "rotate position " + x;
    }
}
