package advent.twenty_sixteen.support;

import java.util.List;

//    move position X to position Y means that the letter which is at index X should be removed from the string, then inserted such that it ends up at index Y.
public class ScrambleMovePosition implements IScrambleCommand {
    private final int x;
    private final int y;

    public ScrambleMovePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void encode(List<Character> working) {
        perform(x, y, working);
    }

    @Override
    public void decode(List<Character> working) {
        perform(y, x, working);
    }

    private static void perform(int x, int y, List<Character> working) {
        Character c = working.get(x);
        working.remove(x);
        working.add(y, c);
    }

    @Override
    public String toString() {
        return "move position " + x + " to " + y;
    }
}
