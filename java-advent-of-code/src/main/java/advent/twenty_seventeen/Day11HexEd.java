package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.LinkedList;
import java.util.List;

public class Day11HexEd implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    /*

      \ n  /
    nw +--+ ne
      /    \
    -+      +-
      \    /
    sw +--+ se
      / s  \

        Algorithm from: https://www.redblobgames.com/grids/hexagons/
     */

    public Day11HexEd(String filename) {
        List<String> lines = FileUtilities.readLines(filename, String::trim);

        LinkedList<Integer> distances = new LinkedList<>();

        String[] steps = lines.get(0).split(",");

        int sx = 0;
        int sy = 0;
        int sz = 0;

        int x = sx;
        int y = sy;
        int z = sz;

        for (String step : steps) {
            switch (step) {
                case "n":
                    y++;
                    z--;
                    break;
                case "s":
                    y--;
                    z++;
                    break;
                case "nw":
                    x--;
                    y++;
                    break;
                case "ne":
                    x++;
                    z--;
                    break;
                case "se":
                    x++;
                    y--;
                    break;
                case "sw":
                    x--;
                    z++;
                    break;
                default:
                    throw new IllegalArgumentException("Cannot parse " + step);
            }

            int d = (Math.abs(x) + Math.abs(y) + Math.abs(z)) / 2;
            distances.add(d);
        }

        this.part1Answer = distances.peekLast();
        this.part2Answer = distances.stream().mapToInt(Integer::intValue).max().getAsInt();
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
