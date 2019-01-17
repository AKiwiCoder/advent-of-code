package advent.twenty_seventeen.support;

import java.util.Map;

public abstract class DuetDualArgumentOperation extends DuetOperation {
    private final Long constantX;
    private final String registerX;
    private final Long constantY;
    private final String registerY;
    private final String name;

    public DuetDualArgumentOperation(String name, String x, String y) {
        this.name = name;
        Long tempInt;

        try {
            tempInt = Long.parseLong(x);
        } catch (NumberFormatException e) {
            tempInt = null;
        }
        this.constantX = (tempInt == null) ? null : tempInt;
        this.registerX = (tempInt == null) ? x : null;

        try {
            tempInt = Long.parseLong(y);
        } catch (NumberFormatException e) {
            tempInt = null;
        }

        this.constantY = (tempInt == null) ? null : tempInt;
        this.registerY = (tempInt == null) ? y : null;
    }

    protected void setX(Map<String, Long> registers, Long value) {
        registers.put(registerX, value);
    }

    protected Long getX(Map<String, Long> registers) {
        return getValue(registers, constantX, registerX);
    }

    protected Long getY(Map<String, Long> registers) {
        return getValue(registers, constantY, registerY);
    }

    protected Long getValue(Map<String, Long> registers, Long constant, String name) {
        if (constant == null) {
            return registers.getOrDefault(name, 0l);
        }
        return constant;
    }

    @Override
    public String toString() {
        return name + " " + ((constantX == null) ? registerX : constantX) + " " + ((constantY == null) ? registerY : constantY);
    }

    protected Long getConstantY(){
        return constantY;
    }
}
