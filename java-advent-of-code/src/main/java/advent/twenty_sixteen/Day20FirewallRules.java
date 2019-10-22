package advent.twenty_sixteen;

import advent.common.DailyProblem;
import advent.common.Pair;
import advent.utilities.FileUtilities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Day20FirewallRules implements DailyProblem<Long, Long> {
    private final long part1Answer;
    private final long part2Answer;

    private final Function<String, Pair<Long, Long>> parse = line -> {
        String[] bits = line.split("-");
        return new Pair<>(Long.parseLong(bits[0]), Long.parseLong(bits[1]));
    };

    private final Comparator<Pair<Long, Long>> sorter = Comparator.comparingLong(Pair::getFirst);

    public Day20FirewallRules(String filename, long min, long max) {
        List<Pair<Long, Long>> rules = FileUtilities.readLines(filename, parse);

        Collections.sort(rules, sorter);

        int i = 0;
        while (i < rules.size() - 1) {
            Pair<Long, Long> r1 = rules.get(i);
            Pair<Long, Long> r2 = rules.get(i + 1);

            if (overlap(r1.getFirst(), r1.getSecond(), r2.getFirst(), r2.getSecond())) {
                Pair<Long, Long> combined = new Pair<>(Math.min(r1.getFirst(), r1.getFirst()), Math.max(r1.getSecond(), r2.getSecond()));
                   rules.set(i, combined);
                rules.remove(i + 1);
                Collections.sort(rules, sorter);
                i = 0;
            } else {
                i++;
            }
        }

        Collections.sort(rules, sorter);

        long lowest = 0;
        long barred = 0;
        for (Pair<Long, Long> rule : rules) {
            long start = rule.getFirst();
            long end = rule.getSecond();
            long thisBass = end - start + 1;
            barred += thisBass;

            if (lowest == start) {
                lowest = end + 1;
            }
        }

        this.part1Answer = (lowest > max)?-1:lowest;
        this.part2Answer = (max - min + 1) - barred;
    }

    private boolean overlap(long s1,long e1, long s2, long e2) {
        if (e1 == s2) {
            return true;
        }
        return Math.max(s1,s2) <= Math.min(e1,e2);
    }

    @Override
    public Long getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Long getPart2Answer() {
        return part2Answer;
    }
}
