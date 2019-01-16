package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetRecoverOperation extends DuetOperation {
    public static final String KEY = "FREQUENCY RECOVERED";

    private final Long constantX;
    private final String registerX;

    public DuetRecoverOperation(String x) {
        Long tempInt;

        try {
            tempInt = Long.parseLong(x);
        } catch (NumberFormatException e) {
            tempInt = null;
        }
        this.constantX = (tempInt == null) ? null : tempInt;
        this.registerX = (tempInt == null) ? x : null;
    }

    @Override
    public int apply(int ip, Map<String, Long> registers) {
        if (constantX == null) {
            Long value = registers.getOrDefault(registerX, 0l);
            if (value != 0) {
                registers.put(KEY, registers.get(DuetSoundOperation.KEY));
            }
        } else {
            Long value = constantX;
            if (value != 0) {
                registers.put(KEY, registers.get(DuetSoundOperation.KEY));
            }
        }
        return ip + 1;
    }


    @Override
    public String toString() {
        return "rcv " + ((constantX == null)?registerX:constantX);
    }

    public String getRegister() {
        return registerX;
    }
}
