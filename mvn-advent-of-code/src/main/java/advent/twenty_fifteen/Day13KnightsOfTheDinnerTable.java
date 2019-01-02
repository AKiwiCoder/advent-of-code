package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.FileUtilities;
import advent.utilities.ListUtilities;
import advent.utilities.Parsers;

import java.util.*;

public class Day13KnightsOfTheDinnerTable implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day13KnightsOfTheDinnerTable(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        Set<String> people = new HashSet<>();
        Map<Pair<String, String>, Integer> happynessChanges = new HashMap<>();

        for (String line : lines) {
            String[] bits = line.split(" ");
            String sourcePerson = bits[0];
            String destPerson = bits[10].substring(0, bits[10].length() - 1);

            people.add(sourcePerson);
            people.add(destPerson);

            int change = Integer.parseInt(bits[3]);
            if (bits[2].equals("lose")) {
                change = -change;
            }

            happynessChanges.put(new Pair<>(sourcePerson, destPerson), change);
        }

        List<List<String>> combinations = ListUtilities.generateCombinations(null, people, null);

        Map<List<String>, Integer> part1Happyness = new HashMap<>();
        combinations.forEach(seating -> part1Happyness.put(seating, calculate(seating, happynessChanges)));

        this.part1Answer = part1Happyness.values().stream().max(Comparator.naturalOrder()).get();

        for (String p : people) {
            happynessChanges.put(new Pair<>("me", p), 0);
            happynessChanges.put(new Pair<>(p, "me"), 0);
        }
        people.add("me");

        Map<List<String>, Integer> part2Happyness = new HashMap<>();
        combinations = ListUtilities.generateCombinations(null, people, null);
        combinations.forEach(seating -> part2Happyness.put(seating, calculate(seating, happynessChanges)));

        this.part2Answer = part2Happyness.values().stream().max(Comparator.naturalOrder()).get();
    }

    private int calculate(List<String> seating, Map<Pair<String, String>, Integer> happynessChanges) {
        int change = 0;
        for (int i = 0; i != seating.size(); i++) {
            int next = (i + 1) % seating.size();
            int prev = (i == 0) ? seating.size() - 1 : i - 1;

            Pair<String, String> clockwise = new Pair<>(seating.get(i), seating.get(next));
            Pair<String, String> counterclockwise = new Pair<>(seating.get(i), seating.get(prev));

            change += happynessChanges.get(clockwise) + happynessChanges.get(counterclockwise);
        }
        return change;
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
