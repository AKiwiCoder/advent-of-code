package advent.twenty_seventeen.support;

import java.util.Map;

public abstract class DuetOperation {

    public abstract int apply(int ip, Map<String, Long> registers);

    public static DuetOperation PARSE(String line) {
        // snd X plays a sound with a frequency equal to the value of X.
        // set X Y sets register X to the value of Y.
        // add X Y increases register X by the value of Y.
        // mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
        // mod X Y sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
        // rcv X recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
        // jgz X Y jumps with an offset of the value of Y, but only if the value of X is greater than zero. (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous instruction, and so on.)
        String[] bits = line.split("\\s+");
        if (bits[0].equals("snd")) {
            return new DuetSoundOperation(bits[1]);
        }
        if (bits[0].equals("set")) {
            return new DuetSetOperation(bits[1], bits[2]);
        }
        if (bits[0].equals("add")) {
            return new DuetAddOperation(bits[1], bits[2]);
        }
        if (bits[0].equals("sub")) {
            return new DuetSubOperation(bits[1], bits[2]);
        }
        if (bits[0].equals("mul")) {
            return new DuetMultiplyOperation(bits[1], bits[2]);
        }
        if (bits[0].equals("mod")) {
            return new DuetModuloOperation(bits[1], bits[2]);
        }
        if (bits[0].equals("rcv")) {
            return new DuetRecoverOperation(bits[1]);
        }
        if (bits[0].equals("jgz")) {
            return new DuetJumpGreaterThanZeroOperation(bits[1], bits[2]);
        }
        if (bits[0].equals("jnz")) {
            return new DuetJumpNotZeroOperation(bits[1], bits[2]);
        }
        throw new IllegalArgumentException("Cannot parse '" + line + "'");
    }

    public Integer getRequiredGotoLabel(int lineNo) {
        return null;
    }

    public abstract String getGeneratedCode(int lineNo);
}
