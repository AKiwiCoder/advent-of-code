package advent.twenty_sixteen.support;

import java.util.List;

//  rotate left/right X steps means that the whole string should be rotated; for example, one right rotation would turn abcd into dabc.
public class ScrambleRotateString implements IScrambleCommand {
    private final boolean left;
    private final int steps;

    public ScrambleRotateString(boolean left, int steps) {
        this.left = left;
        this.steps = steps;
    }

    @Override
    public void encode(List<Character> working) {
        perform(left, steps, working);
    }

    @Override
    public void decode(List<Character> working) {
        perform(!left, steps, working);
    }

    private static void perform(boolean left, int steps, List<Character> working) {
        if (left) {
            for (int i = 0; i != steps; i++) {
                Character c = working.get(0);
                working.remove(0);
                working.add(c);
            }
        } else {
            for (int i = 0; i != steps; i++) {
                Character c = working.get(working.size() - 1);
                working.remove(working.size() - 1);
                working.add(0, c);
            }
        }
    }

    @Override
    public String toString() {
        return "rotate " + (left?"left":"right") + " " + steps;
    }
}
