package advent.twenty_sixteen.support;

import java.util.Map;

public class LeonardoCopyOperation implements ILeonardoOperation {
    private final Integer constant;
    private final String x;
    private final String y;

    private final String originalX;
    private final String originalY;

    public LeonardoCopyOperation(String x, String y) {
        this.originalX = x;
        this.originalY = y;

        Integer c = null;
        try {
            c = Integer.parseInt(x);
        } catch (NumberFormatException e) {
        }

        this.constant = c;
        this.x = (c == null) ? x : null;
        this.y = y;
    }

    @Override
    public int execute(int ip, Map<String, Integer> registers) {
        if (registers.containsKey(y)) {
            if (constant != null) {
                registers.put(y, constant);
            } else {
                registers.put(y, registers.get(x));
            }
        }
        return ip + 1;
    }

    @Override
    public ILeonardoOperation toggle() {
        return new LeonardoJumpNotZeroOperation(originalX, originalY);
    }

    @Override
    public String toString() {
        return "cpy " + (constant == null ? x : constant) + " " + y;
    }
}
