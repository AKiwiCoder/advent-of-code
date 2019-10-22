package advent.twenty_sixteen.support;

import java.util.Map;

public class LeonardoJumpNotZeroOperation implements ILeonardoOperation {
    private final Integer constantX;
    private final Integer constantY;
    private final String x;
    private final String y;

    private final String originalX;
    private final String originalY;

    public LeonardoJumpNotZeroOperation(String x, String y) {
        this.originalX = x;
        this.originalY = y;

        Integer nx = null;
        try {
            nx = Integer.parseInt(x);
        } catch (NumberFormatException e) {
        }
        this.constantX = nx;
        this.x = (nx == null) ? originalX : null;

        Integer ny = null;
        try {
            ny = Integer.parseInt(y);
        } catch (NumberFormatException e) {
        }
        this.constantY = ny;
        this.y = (ny == null) ? originalY : null;
    }

    @Override
    public int execute(int ip, Map<String, Integer> registers) {
        if (constantX != null) {
            Integer valueY = (this.constantY == null) ? registers.get(y) : constantY;
            if (constantX != 0) {
                return ip + valueY;
            }
        }

        if (registers.containsKey(x)) {
            Integer valueX = registers.get(x);
            Integer valueY = (this.constantY == null) ? registers.get(y) : constantY;
            if (valueX != 0) {
                return ip + valueY;
            }
        }
        return ip + 1;
    }

    @Override
    public ILeonardoOperation toggle() {
        return new LeonardoCopyOperation(originalX, originalY);
    }

    @Override
    public String toString() {
        return "jnz " + (constantX == null ? x : constantX) + " " + (constantY == null ? y : constantY);
    }
}
