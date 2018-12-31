package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Day17ReservoirResearch implements DailyProblem<Integer, Integer> {
    static class Range {
        public int min;
        public int max;
    }

    static class Data {
        public Range x;
        public Range y;
    }

    private Integer part1Answer;
    private Integer part2Answer;

    public Day17ReservoirResearch(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::TO_STRING);

        List<Data> data = new LinkedList<>();

        for (String line : lines) {
            Data d = new Data();
            String bits[] = line.split(",");
            for (String bit : bits) {
                String b = bit.trim();
                if (b.startsWith(("x="))) {
                    d.x = range(b.substring(2));
                } else if (b.startsWith("y=")) {
                    d.y = range(b.substring(2));
                } else {
                    throw new IllegalArgumentException("Cannot parse " + b);
                }
            }
            data.add(d);
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (Data d : data) {
            minX = Math.min(minX, d.x.min);
            maxX = Math.max(maxX, d.x.max);
            minY = Math.min(minY, d.y.min);
            maxY = Math.max(maxY, d.y.max);
        }

        minX -= 1;
        maxX += 1;
        maxY += 2;
        minY -= 1;

        int rangeY = maxY - minY + 1;
        int rangeX = maxX - minX + 1;

        char map[][] = new char[rangeY][];
        for (int y = 0; y != rangeY; y++) {
            map[y] = new char[rangeX];
            for (int x = 0; x != map[y].length; x++) {
                map[y][x] = '.';
            }
        }

        for (Data d : data) {
            for (int y = d.y.min; y != d.y.max + 1; y++) {
                for (int x = d.x.min; x != d.x.max + 1; x++) {
                    map[y - minY][x - minX] = '#';
                }
            }
        }

        map[0][500 - minX] = '+';

        water(map, 0, 500 - minX);

        int standing = 0;
        int flowing = 0;
        for (int y = 0; y != map.length - 2; y++) {
            for (int x = 0; x != map[y].length; x++) {
                if (map[y][x] == '|') {
                    flowing++;
                }
                if (map[y][x] == '~') {
                    standing++;
                }
            }
        }

        this.part1Answer = flowing + standing;
        this.part2Answer = standing;
    }

    private static Range range(String r) {
        Range result = new Range();
        int index = r.indexOf("..");
        if (index > 0) {
            int min = Integer.parseInt(r.substring(0, index));
            int max = Integer.parseInt(r.substring(index + 2));
            result.min = Math.min(min, max);
            result.max = Math.max(min, max);
        } else {
            result.min = result.max = Integer.parseInt(r);
        }
        return result;
    }

    private static void water(char[][] map, int y, int x) {
        int oW = -1;
        int nW = count1(map);

        while (oW != nW) {
            new Calculator(map, y, x).drop();

            oW = nW;
            nW = count1(map);
        }

        map[y][x] = '+';
    }

    private static int count1(char[][] map) {
        int result = 0;
        for (int y = 0; y != map.length - 2; y++) {
            for (int x = 0; x != map[y].length; x++) {
                if (map[y][x] == '|' || map[y][x] == '~') {
                    result++;
                }
            }
        }
        return result;
    }

    static class Calculator {
        private final char[][] map;

        static class Point {
            public final int y;
            public final int x;

            Point(int y, int x) {

                this.y = y;
                this.x = x;
            }

            @Override
            public String toString() {
                return "(" + y + "," + x + ")";
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Point point = (Point) o;
                return y == point.y &&
                        x == point.x;
            }

            @Override
            public int hashCode() {
                return Objects.hash(y, x);
            }
        }

        private Stack<Point> dropPoints = new Stack<>();


        public Calculator(char[][] map, int y, int x) {
            this.map = map;
            dropPoints.add(new Point(y, x));
        }

        public void drop() {
            while (!dropPoints.isEmpty()) {
                Point p = dropPoints.pop();

                int currentY = p.y;
                int currentX = p.x;
                while (!supported(currentY, currentX)) {
                    map[currentY][currentX] = '|';
                    currentY++;

                    if (currentY >= map.length - 1) {
                        break;
                    }
                }

                if (currentY >= map.length - 1) {
                    continue;
                }

                int leftSupport = supported(currentY, currentX, -1);
                int rightSupport = supported(currentY, currentX, +1);

                if (leftSupport >= 0 && rightSupport == -1) {// Left Wall
                    for (int i = leftSupport + 1; i != map[0].length; i++) {
                        if (supported(currentY, i))
                            map[currentY][i] = '|';
                        else {
                            map[currentY][i] = '|';
                            dropPoints.add(new Point(currentY, i));
                            break;
                        }
                    }
                } else if (leftSupport == -1 && rightSupport >= 0) { // Right Wall
                    for (int i = rightSupport - 1; i != 0; i--) {
                        if (supported(currentY, i))
                            map[currentY][i] = '|';
                        else {
                            map[currentY][i] = '|';
                            dropPoints.add(new Point(currentY, i));
                            break;
                        }
                    }
                } else if (leftSupport >= 0 && rightSupport >= 0) { // Supported
                    for (int i = leftSupport + 1; i != rightSupport; i++) {
                        map[currentY][i] = '~';
                    }
                    currentY--;
                } else { // No Walls
                    for (int i = currentX; i != 0; i--) {
                        if (supported(currentY, i))
                            map[currentY][i] = '|';
                        else {
                            map[currentY][i] = '|';
                            dropPoints.add(new Point(currentY, i));
                            break;
                        }
                    }
                    for (int i = currentX; i != 0; i++) {
                        if (supported(currentY, i))
                            map[currentY][i] = '|';
                        else {
                            map[currentY][i] = '|';
                            dropPoints.add(new Point(currentY, i));
                            break;
                        }
                    }
                }
            }
        }

        public int supported(int y, int x, int diff) {
            for (int i = x; i >= 0 && i < map[0].length; i += diff) {
                if (map[y][i] == '#')
                    return i;
                if (!supported(y, i))
                    return -1;
            }
            return -1;
        }

        public boolean supported(int y, int x) {
            if (map[y + 1][x] == '#' || map[y + 1][x] == '~') {
                return true;
            }
            return false;
        }
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
