package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetSetOperation extends DuetDualArgumentOperation {
    public DuetSetOperation(String x, String y) {
        super("set", x,y);
    }


    @Override
    public int apply(int ip, Map<String, Long> registers) {
        setX(registers, getY(registers));
        return ip + 1;
    }
}
