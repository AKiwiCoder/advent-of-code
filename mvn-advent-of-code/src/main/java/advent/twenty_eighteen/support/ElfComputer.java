package advent.twenty_eighteen.support;

import java.util.List;

public class ElfComputer {
    private Statement[] statements;

    public ElfComputer(List<Statement> statements) {
        this.statements = statements.toArray(new Statement[0]);
    }

    public void execute(int ipRegister, int[] registers) {
        while (registers[ipRegister] < statements.length) {
            int ip = registers[ipRegister];
            Statement statement = statements[ip];
            statement.getMnemonic().execute(statement.getA(), statement.getB(), statement.getC(), registers);
            ip++;
            registers[ipRegister] = ip;
        }
    }
}
