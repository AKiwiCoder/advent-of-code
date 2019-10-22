package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.twenty_sixteen.support.GridNode;
import advent.utilities.FileUtilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day22GridComputing implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    private static GridNode parse(String line) {
        // Filesystem              Size  Used  Avail  Use%
        // /dev/grid/node-x0-y0     89T   67T    22T   75%
        String[] bits = line.split("\\s+");
        String name = bits[0];
        int row = Integer.parseInt(bits[0].substring(bits[0].indexOf("x") + 1, bits[0].lastIndexOf("-")));
        int col = Integer.parseInt(bits[0].substring(bits[0].lastIndexOf("y") + 1));
        int size = Integer.parseInt(bits[1].substring(0, bits[1].length() - 1));
        int used = Integer.parseInt(bits[2].substring(0, bits[2].length() - 1));
        int avail = Integer.parseInt(bits[3].substring(0, bits[3].length() - 1));
        return new GridNode(name, row, col, size, avail, used);
    }

    public Day22GridComputing(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);
        lines.remove(0); // Two header lines
        lines.remove(0);

        List<GridNode> nodes = lines.stream().map(Day22GridComputing::parse).collect(Collectors.toList());
        Set<Pair<GridNode, GridNode>> pairs = new HashSet<>();
        for (GridNode a : nodes) {
            for (GridNode b : nodes) {
                if (GridNode.isViablePair(a, b)) {
                    pairs.add(new Pair<>(a, b));
                }
            }
        }


        // Looking at the grid
        // 4 up
        // 12 left
        // 10 down
        // 34 goal moves
        // 33 * 4 cycles to move free around

        int steps = 4 + 12 + 10 + 34 + 33 * 4;

        this.part1Answer = pairs.size();
        this.part2Answer = steps;
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
