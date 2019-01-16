package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetJumpGreaterThanZeroOperation extends DuetDualArgumentOperation {
    public DuetJumpGreaterThanZeroOperation(String x, String y) {
        super("jgz", x,y);
    }


    @Override
    public int apply(int ip, Map<String, Long> registers) {
        if (getX(registers) > 0) {
            return (int)(ip + getY(registers));
        }
        return ip + 1;
    }
}
