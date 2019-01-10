package advent.twenty_sixteen.support;

import java.util.List;

//     swap letter X with letter Y means that the letters X and Y should be swapped (regardless of where they appear in the string).
public class ScrambleSwapLetter implements IScrambleCommand {
    private final Character x;
    private final Character y;

    public ScrambleSwapLetter(Character x, Character y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void encode(List<Character> working) {
        int ix = working.indexOf(x);
        int iy = working.indexOf(y);
        Character tx =working.get(ix);
        Character ty = working.get(iy);
        working.set(ix, ty);
        working.set(iy, tx);
    }

    @Override
    public void decode(List<Character> working) {
        encode(working);
    }

    @Override
    public String toString() {
        return "swap letter " + x + " " + y;
    }
}
