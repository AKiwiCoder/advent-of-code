package advent.twenty_seventeen.support;

import java.util.Map;

public class DuetSoundOperation extends DuetOperation {
    public static final String KEY = "FREQUENCY PLAYED";

    private final Long constantX;
    private final String registerX;

    public DuetSoundOperation(String x) {
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
            registers.put(KEY, registers.getOrDefault(registerX,0L));
        } else {
            registers.put(KEY, registers.get(constantX));
        }
        return ip + 1;
    }

    @Override
    public String toString() {
        return "snd " + ((constantX == null)?registerX:constantX);
    }

    public long getValue(Map<String, Long> registers) {
        if (constantX == null) {
            return registers.getOrDefault(registerX,0L);
        }
        return constantX;
    }


    @Override
    public String getGeneratedCode(int lineNo) {
        throw new UnsupportedOperationException("No C equivalent to send");
    }
}
