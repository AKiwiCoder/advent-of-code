package advent.twenty_sixteen.support;

import java.util.List;

// reverse positions X through Y means that the span of letters at indexes X through Y (including the letters at X and Y) should be reversed in order.
public class ScrambleReversePosition implements IScrambleCommand {
    private final int x;
    private final int y;

    public ScrambleReversePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void encode(List<Character> working) {
        int tx = x;
        int ty = y;
        while (tx <= ty) {
            if (tx == ty) {
                break;
            }
            Character a = working.get(tx);
            Character b = working.get(ty);
            working.set(tx, b);
            working.set(ty, a);
            tx++;
            ty--;
        }
    }

    @Override
    public void decode(List<Character> working) {
        encode(working);
    }

    @Override
    public String toString() {
        return "reverse positions " + x + " through " + y;
    }
}