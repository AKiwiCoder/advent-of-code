package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.*;

public class Day09AllInASingleNight implements DailyProblem<Integer, Integer>  {
    private final int part1Answer;
    private final int part2Answer;

    public Day09AllInASingleNight(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        Map<Pair<String, String>, Integer> distances = new HashMap<>();
        Set<String> startingPoints = new HashSet<>();

        for (String line : lines) {
            String[] bits = line.split(" ");
            String srce = bits[0];
            String dest = bits[2];
            int dist = Integer.parseInt(bits[4]);

            startingPoints.add(srce);
            startingPoints.add(dest);

            distances.put(new Pair<>(srce, dest), dist);
            distances.put(new Pair<>(dest, srce), dist);
        }

        List<List<String>> paths = new LinkedList<>();
        for (String start : startingPoints) {
            List<String> startPath = new LinkedList<>();
            startPath.add(start);
            paths.addAll(generatePath(start, distances, new LinkedList<>()));
        }

        Map<List<String>, Integer> results = new HashMap<>();
        for (List<String> path : paths) {
            results.put(path, calculateLength(path, distances));
        }

        this.part1Answer = results.values().stream().min(Comparator.naturalOrder()).get();
        this.part2Answer = results.values().stream().max(Comparator.naturalOrder()).get();
    }

    private int calculateLength(List<String> path, Map<Pair<String, String>, Integer> distances) {
        int total = 0;
        for (int i = 1 ; i != path.size(); i++) {
            total += distances.get(new Pair<>(path.get(i-1), path.get(i)));
        }
        return total;
    }

    private List<List<String>> generatePath(String current, Map<Pair<String, String>, Integer> distances, List<String> path) {
        List<List<String>> result = new LinkedList<>();
        for (Pair<String, String> key : distances.keySet()){
            if (key.getFirst().equals(current) && !path.contains(key.getSecond())) {
                List<String> working = new LinkedList<>(path);
                working.add(key.getSecond());
                List<List<String>> subPaths = generatePath(key.getSecond(), distances, working);
                result.addAll(subPaths);
            }
        }
        if (result.size() == 0) {
            result.add(path);
        }
        return result;
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
