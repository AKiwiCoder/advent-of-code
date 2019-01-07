package advent.twenty_sixteen.support;

import java.util.Map;

public class LeonardoJumpNotZeroOperation implements ILeonardoOperation {
    private final Integer constant;
    private final String x;
    private final Integer y;

    public LeonardoJumpNotZeroOperation(String x, String y) {
        Integer c = null;
        try {
            c = Integer.parseInt(x);
        } catch (NumberFormatException e) {
        }

        try {
            this.y = Integer.parseInt(y);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Cannot parse", e);
        }

        this.constant = c;
        this.x = (c == null) ? x : null;
    }

    @Override
    public int execute(int ip, Map<String, Integer> registers) {
        Integer value = (this.constant == null)?registers.get(x):constant;
        if (value != 0) {
            return ip + y;
        }
        return ip + 1;
    }

    @Override
    public String toString() {
        return "jnz " + x + " " + y;
    }
}
