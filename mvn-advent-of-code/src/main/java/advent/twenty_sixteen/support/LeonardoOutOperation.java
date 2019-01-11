package advent.twenty_sixteen.support;

import java.util.Map;

public class LeonardoOutOperation implements ILeonardoOperation {
    private final String x;
    private final Integer constant;

    public LeonardoOutOperation(String x) {
        Integer temp = null;
        try {
            temp = Integer.parseInt(x);
        } catch (NumberFormatException e) {
        }
        this.constant = temp;
        this.x = (temp == null) ? x : null;
    }

    @Override
    public int execute(int ip, Map<String, Integer> registers) {
        return ip + 1;
    }

    @Override
    public ILeonardoOperation toggle() {
        return new LeonardoOutOperation(x);
    }

    @Override
    public String toString() {
        return "out " + x;
    }

    public String getOutput(Map<String, Integer> registers) {
        return Integer.toString((constant == null) ? registers.get(x) : constant);
    }
}
