package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.StringUtilities;

import java.util.LinkedList;
import java.util.List;

public class Day16PermutationPromenade implements DailyProblem<String, String> {
    private final String part1Answer;
    private final String part2Answer;

    public Day16PermutationPromenade(String filename, int dancerCount) {
        String line = FileUtilities.readLines(filename, String::trim).get(0);

        LinkedList<Character> dancers = new LinkedList<>(StringUtilities.toCharacterList("abcdefghijklmnopqrstuvwxyz".substring(0, dancerCount)));

        dance(line, dancers);

        this.part1Answer = StringUtilities.toString(dancers);


        String result = "";

        List<String> cycles = new LinkedList<>();
        cycles.add(this.part1Answer);

        while (true) {
            dance(line, dancers);

            String val = StringUtilities.toString(dancers);

            if (cycles.contains(val)) {
                int startIndex = cycles.indexOf(val);

                int offset = startIndex +  1000000000 % cycles.size() - 1;
                if (offset < 0) {
                    offset += cycles.size();
                }

                result = cycles.get(offset);
                break;
            }
            cycles.add(val);
        }
        this.part2Answer = result;
    }

    private void dance(String line, LinkedList<Character> dancers) {
        for (String step : line.split(",")) {
            switch (step.charAt(0)) {
                case 's': {
                    int cnt = Integer.parseInt(step.substring(1));
                    for (int c = 0; c != cnt; c++) {
                        dancers.addFirst(dancers.removeLast());
                    }
                }
                break;
                case 'x': {
                    String temp = step.substring(1);
                    String[] pos = temp.split("/");
                    int idxA = Integer.parseInt(pos[0]);
                    int idxB = Integer.parseInt(pos[1]);
                    Character charA = dancers.get(idxA);
                    Character charB = dancers.get(idxB);
                    dancers.set(idxA, charB);
                    dancers.set(idxB, charA);
                }
                break;
                case 'p': {
                    String temp = step.substring(1);
                    String[] pos = temp.split("/");
                    Character charA = Character.valueOf(pos[0].charAt(0));
                    Character charB = Character.valueOf(pos[1].charAt(0));
                    int idxA = dancers.indexOf(charA);
                    int idxB = dancers.indexOf(charB);
                    dancers.set(idxA, charB);
                    dancers.set(idxB, charA);
                }
                break;
            }
        }
    }

    @Override
    public String getPart1Answer() {
        return part1Answer;
    }

    @Override
    public String getPart2Answer() {
        return part2Answer;
    }
}
