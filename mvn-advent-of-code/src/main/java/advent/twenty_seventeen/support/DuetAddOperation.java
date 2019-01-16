package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetAddOperation extends DuetDualArgumentOperation {
   public DuetAddOperation(String x, String y) {
       super("add", x,y);
    }

    @Override
    public int apply(int ip, Map<String, Long> registers) {
        setX(registers, getX(registers) + getY(registers));
        return ip + 1;
    }
}
