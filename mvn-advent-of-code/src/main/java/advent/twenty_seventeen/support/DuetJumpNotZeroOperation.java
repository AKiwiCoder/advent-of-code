package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetJumpNotZeroOperation extends DuetDualArgumentOperation {
    public DuetJumpNotZeroOperation(String x, String y) {
        super("jnz", x,y);
    }

    @Override
    public int apply(int ip, Map<String, Long> registers) {
        if (getX(registers) != 0) {
            return (int)(ip + getY(registers));
        }
        return ip + 1;
    }
}
