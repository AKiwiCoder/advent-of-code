package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.twenty_eighteen.support.Rule;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day12SubterraneanSustainability implements DailyProblem<Long, Long> {

    private long partOneAnswer;
    private long partTwoAnswer;

    public Day12SubterraneanSustainability(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::ToString);

        String input = lines.get(0).substring("initial state: ".length());

        List<Rule> rules = new ArrayList<>();

        for (int i = 2; i != lines.size(); i++) {
            String line = lines.get(i);

            Rule rule = new Rule(line.substring(0, 5), line.substring(9, 10).toCharArray()[0]);
            if (rule.getOutput() == '#') {
                rules.add(rule);
            }
        }

        input = "..." + input + "...";

        long modifier = -3;

        boolean stillRunning = true;
        int step = 0;
        long lastDiff = -1;
        long lastCalculation = 0;

        char[] output = new char[input.length()];
        while (stillRunning) {
            step++;

            if (output.length != input.length()) {
                output = new char[input.length()];
            }
            Arrays.fill(output, '.');

            for (Rule r : rules) {
                int index = input.indexOf(r.getPattern());
                while (index != -1) {
                    output[index + 2] = r.getOutput();
                    index = input.indexOf(r.getPattern(), index + 1);
                }
            }

            input = new String(output);
            if (!input.endsWith("...")) {
                input = input + "...";
            }
            if (!input.startsWith("...")) {
                input = "..." + input;
                modifier -= 3;
            }
            while (input.startsWith("....")) {
                modifier++;
                input = input.substring(1);
            }

            long current = calculate(input, modifier);

            if (step == 20) {
                this.partOneAnswer = current;
            }

            long diff = current - lastCalculation;

            if (diff == lastDiff) {
                break;
            }

            lastDiff = diff;
            lastCalculation = current;
        }

        this.partTwoAnswer = (((50000000000l - step) * lastDiff) + calculate(input, modifier));

    }

    private static long calculate(String input, long modifier) {
        long cnt = 0;
        for (int i = 0; i != input.length(); i++) {
            if (input.charAt(i) == '#') {
                cnt += (i + modifier);
            }
        }
        return cnt;
    }

    @Override
    public Long getPart1Answer() {
        return partOneAnswer;
    }

    @Override
    public Long getPart2Answer() {
        return partTwoAnswer;
    }
}
