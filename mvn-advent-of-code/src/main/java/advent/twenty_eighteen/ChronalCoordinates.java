package advent.twenty_eighteen;

import advent.common.DailyProblem;
import advent.common.IndexedPoint;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;
import advent.utilities.PointUtilities;

import java.util.*;

public class ChronalCoordinates implements DailyProblem<Integer, Integer> {
    private final List<IndexedPoint> input;
    private final int part2Limit;
    private final int maxWidth;
    private final int maxHeight;
    private final int minWidth;
    private final int minHeight;

    public ChronalCoordinates(String filename, int part2Limit) {
        this.input = FileUtilities.readLinesWithIndex(filename, Parsers::ToIndexedPoint);
        this.part2Limit = part2Limit;

        this.minWidth = input.stream().map(p -> p.getY()).min(Comparator.naturalOrder()).get();
        this.minHeight = input.stream().map(p -> p.getX()).min(Comparator.naturalOrder()).get();

        this.maxWidth = input.stream().map(p -> p.getY()).max(Comparator.naturalOrder()).get();
        this.maxHeight = input.stream().map(p -> p.getX()).max(Comparator.naturalOrder()).get();
    }

    @Override
    public Integer getPart1Answer() {
        Set<IndexedPoint> edgePoints = new HashSet<>();
        IndexedPoint tie = new IndexedPoint(-1, -999, -999);
        IndexedPoint[][] area = new IndexedPoint[maxWidth - minWidth + 1][maxHeight - minHeight + 1];
        for (int x = 0; x != area.length; x++) {
            for (int y = 0; y != area[x].length; y++) {
                IndexedPoint n = new IndexedPoint(-2, x + minWidth, y + minWidth);
                IndexedPoint winner = null;
                int minimumDistance = Integer.MAX_VALUE;
                for (IndexedPoint point : input) {
                    int distance = PointUtilities.calculateManhattenDistance(n, point);
                    if (distance < minimumDistance) {
                        minimumDistance = distance;
                        winner = point;
                    } else if (distance == minimumDistance) {
                        winner = tie;
                        minimumDistance = distance;
                    }
                }
                area[x][y] = winner;
                if (x == 0 || x == area.length || y == 0 || y == area[x].length) {
                    edgePoints.add(winner);
                }
            }
        }

        Map<IndexedPoint, Integer> count = new HashMap<>();
        for (IndexedPoint p : input) {
            count.put(p, 0);
        }

        int maxCount = -1;
        for (int x = 0; x != area.length; x++) {
            for (int y = 0; y != area[x].length; y++) {
                IndexedPoint p = area[x][y];
                if (p != tie) {
                    int c = count.get(p) + 1;
                    if (c > maxCount && !edgePoints.contains(p)) {
                        maxCount = c;
                    }
                    count.put(p, c);
                }
            }
        }

        return maxCount;
    }

    @Override
    public Integer getPart2Answer() {
        int safe = 0;
        for (int x = minHeight; x != maxHeight; x++) {
            for (int y = minWidth; y != maxWidth; y++) {
                IndexedPoint n = new IndexedPoint(-2, x, y);
                int total = 0;
                for (IndexedPoint p : input) {
                    total += PointUtilities.calculateManhattenDistance(n, p);
                }
                if (total < part2Limit) {
                    safe++;
                }
            }
        }
        return safe;
    }
}
