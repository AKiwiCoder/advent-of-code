package advent.twenty_fifteen.support;

public class TuringInstructionJump extends TuringInstruction {
    private int offset;

    public TuringInstructionJump(int offset) {
        this.offset = offset;
    }

    @Override
    public int execute(int ip, int[] registers) {
        return ip + offset;
    }
}
