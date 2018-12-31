package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.*;

public class Day20ARegularMap implements DailyProblem<Integer, Long> {
    static class Point {
        int col;
        int row;

        Point(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return col == point.col &&
                    row == point.row;
        }

        @Override
        public int hashCode() {
            return Objects.hash(col, row);
        }

        @Override
        public String toString() {
            return "(" + col + "," + row + ")";
        }

        private static Point north(Point start) {
            return new Point(start.col, start.row - 1);
        }

        private static Point south(Point start) {
            return new Point(start.col, start.row + 1);
        }

        private static Point west(Point start) {
            return new Point(start.col - 1, start.row);
        }

        private static Point east(Point start) {
            return new Point(start.col + 1, start.row);
        }
    }

    static class State {
        Point current;
        Point previous;
        int pos;

        State(Point current, Point previous, int pos) {
            this.current = current;
            this.previous = previous;
            this.pos = pos;
        }

        @Override
        public String toString() {
            return "[" + current + " " + previous + " " + pos + "]";
        }
    }

    private final int part1Answer;
    private final long part2Answer;

    public Map<Point, Integer> process(String input) {
        Stack<Point> positions = new Stack<>();

        Map<Integer, List<State>> depths = new HashMap<>();

        Stack<State> todo = new Stack<>();

        Point current = new Point(0, 0);
        Point previous = current;

        Map<Point, Integer> distances = new HashMap<>();
        distances.put(current, 0);

        todo.push(new State(current, previous, 0));

        int pos;
        Integer depth = new Integer(0);
        while (!todo.isEmpty()) {
            State state = todo.pop();
            pos = state.pos;
            current = state.current;
            previous = state.previous;

            while (pos != input.length()) {
                char c = input.charAt(pos++);
                switch (c) {
                    case '^':
                    case '$':
                        break;
                    case 'N':
                    case 'S':
                    case 'E':
                    case 'W':
                        switch (c) {
                            case 'N':
                                current = Point.north(current);
                                break;
                            case 'S':
                                current = Point.south(current);
                                break;
                            case 'W':
                                current = Point.west(current);
                                break;
                            case 'E':
                                current = Point.east(current);
                                break;
                        }

                        if (!distances.containsKey(current)) {
                            distances.put(current, distances.get(previous) + 1);
                        } else {
                            distances.put(current, Math.min(distances.get(current), distances.get(previous) + 1));
                        }
                        break;
                    case '(':
                        positions.push(current);
                        depth = new Integer(depth.intValue() + 1);
                        depths.put(depth, new LinkedList<>());
                        break;
                    case ')':
                        depths.get(depth).add(new State(current, previous, Integer.MAX_VALUE));
                        positions.pop();

                        Integer newDepth = new Integer(depth.intValue() - 1);
                        if (newDepth > 0) {
                            depths.get(newDepth).addAll(depths.get(depth));
                        } else {
                            for (State pop : depths.get(depth)) {
                                todo.add(new State(pop.current, pop.previous, pos));
                            }
                        }
                        depth = newDepth;
                        break;
                    case '|':
                        depths.get(depth).add(new State(current, previous, Integer.MAX_VALUE));
                        current = positions.peek();
                        break;
                    default:
                        throw new IllegalArgumentException("Unable to understand '" + c + "'");
                }
                previous = current;
            }
        }
        return distances;
    }

    public Day20ARegularMap(String filename) {
        String line = FileUtilities.readLines(filename, Parsers::ToString).get(0);

        Map<Point, Integer> distances = process(line);

        part1Answer = distances.values().stream().max(Comparator.naturalOrder()).get();
        part2Answer = distances.values().stream().filter(dist -> dist >= 1000).count();
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Long getPart2Answer() {
        return part2Answer;
    }
}
