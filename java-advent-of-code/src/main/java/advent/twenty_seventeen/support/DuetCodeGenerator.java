package advent.twenty_seventeen.support;

import advent.utilities.FileUtilities;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuetCodeGenerator {
    private final DuetOperation[] program;

    public DuetCodeGenerator(String filename) {
        List<DuetOperation> working =  FileUtilities.readLines(filename, DuetOperation::PARSE);
        this.program = working.toArray(new DuetOperation[0]);
    }

    public void generate(PrintStream out) {
        Set<Integer> labels = new HashSet<>();
        for (int lineNo = 0; lineNo != program.length; lineNo++) {
            DuetOperation op = program[lineNo];
            Integer label = op.getRequiredGotoLabel(lineNo);
            if (label != null) {
                labels.add(label);
            }
        }
        String padding = "    ";

        out.println("#include <stdio.h>");
        out.println();
        out.println("int main(int argc, char **argv) {");
        out.println(padding + "int a = 0;");
        out.println(padding + "int b = 0;");
        out.println(padding + "int c = 0;");
        out.println(padding + "int d = 0;");
        out.println(padding + "int e = 0;");
        out.println(padding + "int f = 0;");
        out.println(padding + "int g = 0;");
        out.println(padding + "int h = 0;");
        out.println("");
        for (int lineNo = 0; lineNo != program.length; lineNo++) {
            if (labels.contains(Integer.valueOf(lineNo))) {
                out.println("label" + lineNo + ":");
            }

            DuetOperation op = program[lineNo];
            out.println(padding + op.getGeneratedCode(lineNo));
        }

        for (Integer lineNo : labels) {
            if (lineNo >= program.length) {
                out.println("label" + lineNo + ":");
            }
        }

        out.println(padding + "printf(\"%d\", h);");
        out.println("}");
    }
}
