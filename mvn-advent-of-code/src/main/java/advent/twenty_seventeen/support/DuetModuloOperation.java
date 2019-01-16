package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetModuloOperation extends DuetDualArgumentOperation {
    public DuetModuloOperation(String x, String y) {
        super("mod", x,y);
    }


    @Override
    public int apply(int ip, Map<String, Long> registers) {
        setX(registers, getX(registers) % getY(registers));
        return ip + 1;
    }
}
