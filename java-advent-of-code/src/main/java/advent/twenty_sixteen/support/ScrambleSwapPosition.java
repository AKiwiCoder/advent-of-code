package advent.twenty_sixteen.support;

import java.util.List;

//     swap position X with position Y means that the letters at indexes X and Y (counting from 0) should be swapped.
public class ScrambleSwapPosition implements IScrambleCommand {
    private final int x;
    private final int y;

    public ScrambleSwapPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void encode(List<Character> working) {
        Character tx = working.get(x);
        Character ty = working.get(y);
        working.set(x, ty);
        working.set(y, tx);
    }

    @Override
    public void decode(List<Character> working) {
        encode(working);
    }

        @Override
    public String toString() {
        return "swap position " + x +" " + y;
    }
}
