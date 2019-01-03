package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19MedicineForRudolph implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day19MedicineForRudolph(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        List<Pair<String,String>> replacements = new ArrayList<>();

        String input = "";
        for (String line : lines) {
            if (line.contains("=>")) {
                String[] bits = line.split("=>");
                replacements.add(new Pair<>(bits[0].trim(), bits[1].trim()));
            } else if (line.isEmpty()) {
                // Ignore
            } else {
                input = line.trim();
            }
        }

        this.part1Answer = calculatePartOne(replacements, input);
        this.part2Answer = calculatePartTwo(replacements, input);
    }

    private int calculatePartOne(List<Pair<String, String>> replacements, String input) {
        Set<String> molecules = new HashSet<>();
        for (Pair<String,String> replacement : replacements) {
            String search = replacement.getFirst();
            String text = replacement.getSecond();

            int index = input.indexOf(search, 0);
            while (index >= 0) {
                String molecule = input.substring(0, index) + text + input.substring(index+search.length());
                molecules.add(molecule);
                index = input.indexOf(search, index+1);
            }
        }
        return molecules.size();
    }

    private int calculatePartTwo(List<Pair<String, String>> replacements, String input) {
        int steps = 0;

        String current = input;
        boolean skipElectronReplacements = true;
        while (!current.equals("e")) {
            boolean replacementMade = false;

            for (Pair<String,String> replacement : replacements) {
                String text = replacement.getFirst();
                String search = replacement.getSecond();

                if (skipElectronReplacements && text.equals("e")) {
                    continue;
                }

                int index = current.lastIndexOf(search);
                if (index >= 0) {
                    current = current.substring(0, index) + text + current.substring(index+search.length());
                    steps++;
                    replacementMade = true;
                }
            }

            if (skipElectronReplacements && !replacementMade) {
                skipElectronReplacements = false;
            }
        }

        return steps;
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Integer getPart2Answer() {
        return part2Answer;
    }
}
