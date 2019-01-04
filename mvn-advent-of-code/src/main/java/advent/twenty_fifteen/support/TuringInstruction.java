package advent.twenty_fifteen.support;

public abstract class TuringInstruction {
    public abstract int execute(int ip, int[] registers);

    public static TuringInstruction PARSE(String line) {
        if (line.startsWith("hlf"))
            return new TuringInstructionSetRegisterHalf(convertToRegisterIndex(line.substring(4)));
        if (line.startsWith("tpl"))
            return new TuringInstructionSetRegisterTriple(convertToRegisterIndex(line.substring(4)));
        if (line.startsWith("inc"))
            return new TuringInstructionIncrementRegister(convertToRegisterIndex(line.substring(4)));
        if (line.startsWith("jmp"))
            return new TuringInstructionJump(convertToOffset(line.substring(4)));
        if (line.startsWith("jie")) {
            String[] bits = line.substring(4).split(",");
            return new TuringInstructionJumpEven(convertToRegisterIndex(bits[0]), convertToOffset(bits[1]));
        }
        if (line.startsWith("jio")) {
            String[] bits = line.substring(4).split(",");
            return new TuringInstructionJumpIfOne(convertToRegisterIndex(bits[0]), convertToOffset(bits[1]));
        }
        throw new IllegalArgumentException("Cannot parse '" + line + "'");
    }

    private static int convertToOffset(String string) {
        return Integer.parseInt(string.trim());
    }

    private static int convertToRegisterIndex(String string) {
        if (string.trim().equals("a"))
            return 0;
        if (string.trim().equals("b"))
            return 1;
        throw new IllegalArgumentException("Cannot parse register '" + string + "'");
    }
}
