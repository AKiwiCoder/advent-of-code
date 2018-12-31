package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.LinkedList;
import java.util.List;

public class Day25FourDimensionalAdventure implements DailyProblem<Integer, Void> {
    private final int part1Answer;

    static class Point {
        int a;
        int b;
        int c;
        int d;

        public Point(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }

    public static int manhatten(Point p1, Point p2) {
        return Math.abs(p1.a - p2.a) + Math.abs(p1.b - p2.b) + Math.abs(p1.c - p2.c) + Math.abs(p1.d - p2.d);
    }

    public Day25FourDimensionalAdventure(String filename) {
        List<String> lines = FileUtilities.readLines(filename, Parsers::ToString);

        LinkedList<Point> points = new LinkedList<>();
        for (String line : lines) {
            String[] bits = line.split(",");

            points.add(new Point(Integer.parseInt(bits[0].trim()), Integer.parseInt(bits[1].trim()), Integer.parseInt(bits[2].trim()), Integer.parseInt(bits[3].trim())));
        }

        List<List<Point>> constellations = new LinkedList<List<Point>>();
        constellations.add(new LinkedList<>());
        constellations.get(0).add(points.pop());
        while (!points.isEmpty()) {
            Point added = process(points, constellations);

            if (added == null) {
                added = points.pop();
                List<Point> newConstellation = new LinkedList<>();
                newConstellation.add(added);
                constellations.add(newConstellation);
            }
            points.remove(added);
        }

        this.part1Answer = constellations.size();
    }

    private static Point process(List<Point> points, List<List<Point>> constellations) {
        for (Point p : points) {
            for (List<Point> lp : constellations) {
                for (Point lpp : lp) {
                    if (manhatten(p, lpp) <= 3) {
                        lp.add(p);
                        return p;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Void getPart2Answer() {
        return null;
    }
}
