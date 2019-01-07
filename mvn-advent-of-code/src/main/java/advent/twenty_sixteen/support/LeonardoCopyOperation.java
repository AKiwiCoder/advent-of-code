package advent.twenty_sixteen.support;

import java.util.Map;

public class LeonardoCopyOperation implements ILeonardoOperation {
    private final Integer constant;
    private final String x;
    private final String y;

    public LeonardoCopyOperation(String x, String y) {
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
        if (constant != null) {
            registers.put(y, constant);
        } else {
            registers.put(y, registers.get(x));
        }
        return ip + 1;
    }

    @Override
    public String toString() {
        return "cpy " + (constant == null ? x : constant) + " " + y;
    }
}
