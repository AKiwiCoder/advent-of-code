package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetMultiplyOperation extends DuetDualArgumentOperation {
    public DuetMultiplyOperation(String x, String y) {
        super("mul", x,y);
    }


    @Override
    public int apply(int ip, Map<String, Long> registers) {
        setX(registers, getX(registers) * getY(registers));
        return ip + 1;
    }
}
