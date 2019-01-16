package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetSubOperation extends DuetDualArgumentOperation {
   public DuetSubOperation(String x, String y) {
       super("sub", x,y);
    }

    @Override
    public int apply(int ip, Map<String, Long> registers) {
        setX(registers, getX(registers) - getY(registers));
        return ip + 1;
    }
}
