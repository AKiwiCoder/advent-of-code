package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetJumpNotZeroOperation extends DuetDualArgumentOperation {
    private final String x;
    private final String y;

    public DuetJumpNotZeroOperation(String x, String y) {
        super("jnz", x,y);
        this.x = x;
        this.y = y;
    }

    @Override
    public int apply(int ip, Map<String, Long> registers) {
        if (getX(registers) != 0) {
            return (int)(ip + getY(registers));
        }
        return ip + 1;
    }

    @Override
    public Integer getRequiredGotoLabel(int lineNo) {
        return lineNo + getConstantY().intValue();
    }

    @Override
    public String getGeneratedCode(int lineNo) {
        return "if (" + x + " != 0) goto label" + getRequiredGotoLabel(lineNo) + ";";
    }
}
