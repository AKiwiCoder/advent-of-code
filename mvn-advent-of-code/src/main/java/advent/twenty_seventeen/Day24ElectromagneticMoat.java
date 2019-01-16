package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.FileUtilities;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day24ElectromagneticMoat implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day24ElectromagneticMoat(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);

        List<Pair<Integer, Integer>> connectors = new LinkedList<>();
        for (String line : lines) {
            String[] bits = line.split("/");
            connectors.add(new Pair<>(Integer.parseInt(bits[0]), Integer.parseInt(bits[1])));
        }

        List<Pair<Integer, Integer>> current = new LinkedList<>();

        List<List<Pair<Integer, Integer>>> paths = generatePaths(current, 0, connectors);

        this.part1Answer = paths.stream().mapToInt(path -> score(path)).max().getAsInt();

        int longestLength = paths.stream().mapToInt(List::size).max().getAsInt();

        this.part2Answer = paths.stream().filter(path -> path.size() == longestLength).mapToInt(path -> score(path)).max().getAsInt();
    }

    private int score(List<Pair<Integer, Integer>> path) {
        return path.stream().mapToInt(pair -> pair.getFirst() + pair.getSecond()).sum();
    }

    private List<List<Pair<Integer, Integer>>> generatePaths(List<Pair<Integer, Integer>> used, int freePort, List<Pair<Integer, Integer>> available) {
        List<List<Pair<Integer, Integer>>> results = new LinkedList<>();

        if (available.size() == 0) {
            results.add(used);
            return results;
        }

        boolean listAddedTo = false;
        for (Pair<Integer, Integer> connector : available) {
            if (connector.getFirst() == freePort) {
                List<Pair<Integer, Integer>> newUsed = new LinkedList<>(used);
                newUsed.add(connector);
                List<Pair<Integer, Integer>> newAvailable = new LinkedList<>(available);
                newAvailable.remove(connector);
                results.addAll(generatePaths(newUsed, connector.getSecond(), newAvailable));
                listAddedTo = true;
            } else if (connector.getSecond() == freePort) {
                List<Pair<Integer, Integer>> newUsed = new LinkedList<>(used);
                newUsed.add(connector);
                List<Pair<Integer, Integer>> newAvailable = new LinkedList<>(available);
                newAvailable.remove(connector);
                results.addAll(generatePaths(newUsed, connector.getFirst(), newAvailable));
                listAddedTo = true;
            }
        }

        if (!listAddedTo) {
            results.add(used);
        }

        return results;
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
