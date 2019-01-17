package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetMultiplyOperation extends DuetDualArgumentOperation {
    private final String x;
    private final String y;

    public DuetMultiplyOperation(String x, String y) {
        super("mul", x,y);
        this.x = x;
        this.y = y;
    }


    @Override
    public int apply(int ip, Map<String, Long> registers) {
        setX(registers, getX(registers) * getY(registers));
        return ip + 1;
    }


    @Override
    public String getGeneratedCode(int lineNo) {
        return x + " = " + x + " * " + y + ";";
    }
}
