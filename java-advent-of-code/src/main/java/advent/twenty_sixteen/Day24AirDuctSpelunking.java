package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.common.Point;
import advent.twenty_sixteen.support.Step;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;
import advent.utilities.PointUtilities;

import java.util.*;
import java.util.stream.Collectors;

public class Day24AirDuctSpelunking implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    public Day24AirDuctSpelunking(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING_NO_TRIM);

        // Generate Map & Working Area
        char[][] map = new char[lines.size()][];
        int[][] distances = new int[lines.size()][];
        for (int i = 0; i != lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
            distances[i] = new int[map[i].length];
        }

        Map<Integer, Point> pointsToVisit = new HashMap<>();
        for (int row = 0; row != map.length; row++) {
            for (int col = 0; col != map[row].length; col++) {
                if (Character.isDigit(map[row][col])) {
                    pointsToVisit.put(Integer.parseInt(Character.toString(map[row][col])), new Point(col, row));
                }
            }
        }

        Map<Integer, Map<Integer, Integer>> quickestPaths = new HashMap<>();
        for (Map.Entry<Integer, Point> entry : pointsToVisit.entrySet()) {
            resetDistanceMap(distances);
            quickestPaths.put(entry.getKey(), fillDistanceMap(map, distances, entry.getValue().getRow(), entry.getValue().getCol(), pointsToVisit.size()));
        }

        List<Integer> leftToVisit = new ArrayList<>(pointsToVisit.keySet());
        leftToVisit.remove(Integer.valueOf(0));
        Set<Pair<List<Integer>, Integer>> solutions1 = new HashSet<>();
        List<Integer> current = new LinkedList<>();
        current.add(Integer.valueOf(0));
        findShortestPath(current, Integer.valueOf(0), 0, quickestPaths, leftToVisit, solutions1);

        // Part 2 asks to get home again
        Set<Pair<List<Integer>, Integer>> solutions2 = solutions1.stream().map(entry -> new Pair<>(entry.getFirst(), entry.getSecond() + quickestPaths.get(entry.getFirst().get(entry.getFirst().size()-1)).get(Integer.valueOf(0)))).collect(Collectors.toSet());

        this.part1Answer = solutions1.stream().mapToInt(p -> p.getSecond().intValue()).min().getAsInt();
        this.part2Answer = solutions2.stream().mapToInt(p -> p.getSecond().intValue()).min().getAsInt();
    }

    private void findShortestPath(List<Integer> current, Integer previous, int distanceSoFar, Map<Integer, Map<Integer, Integer>> quickestPaths, List<Integer> leftToVisit, Set<Pair<List<Integer>, Integer>> solutions) {
        if (leftToVisit.size() == 0) {
            solutions.add(new Pair<>(current, distanceSoFar));
        } else {
            for (Integer next : leftToVisit) {
                List<Integer> newCurrent = new LinkedList<>(current);
                newCurrent.add(next);
                List<Integer> newLeft = new LinkedList<>(leftToVisit);
                newLeft.remove(next);
                int newDistanceSoFar = distanceSoFar + quickestPaths.get(previous).get(next);
                findShortestPath(newCurrent, next, newDistanceSoFar, quickestPaths, newLeft, solutions);
            }
        }
    }

    private void resetDistanceMap(int[][] distances) {
        for (int row = 0; row != distances.length; row++) {
            for (int col = 0; col != distances[row].length; col++) {
                distances[row][col] = Integer.MAX_VALUE;
            }
        }
    }

    private Map<Integer,Integer> fillDistanceMap(char[][] map, int[][] distances, int startRow, int startCol, int points) {
        Point origin = new Point(startCol, startRow);

        LinkedList<Step> stack = new LinkedList<>();
        stack.push(new Step(origin, 0));

        Map<Integer, Integer> results = new HashMap<>();

        while (!stack.isEmpty()) {
            Step current = stack.pop();

            Point currentPoint = current.getPoint();

            int row = currentPoint.getRow();
            int col = currentPoint.getCol();

            distances[row][col] = current.getCost();

            if (distances[row][col] > current.getCost()) {
                continue;
            }

            if (map[row][col] != '.') {
                results.put(Integer.parseInt(Character.toString(map[row][col])), distances[row][col]);
                if (results.size() == points)
                    return results;
            }

            considerMoving(map, distances, stack, PointUtilities.north(currentPoint), current.getCost() + 1);
            considerMoving(map, distances, stack, PointUtilities.south(currentPoint), current.getCost() + 1);
            considerMoving(map, distances, stack, PointUtilities.east(currentPoint), current.getCost() + 1);
            considerMoving(map, distances, stack, PointUtilities.west(currentPoint), current.getCost() + 1);
        }
        return results;
    }

    private void considerMoving(char[][] map, int[][] distances, LinkedList<Step> stack, Point point, int cost) {
        int row = point.getRow();
        int col = point.getCol();

        if (distances[row][col] <= cost || map[row][col] == '#') {
            return;
        }

        distances[row][col] = cost;
        stack.addLast(new Step(point, cost));
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
