package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;

import java.util.*;

public class Day17NoSuchThingAsTooMuch implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    static class Container implements Comparable<Container> {
        final int volume;

        Container(int volume) {
            this.volume = volume;
        }

        @Override
        public String toString() {
            return volume + " (" + hashCode() + ")";
        }

        @Override
        public int compareTo(Container o) {
            int c = Integer.compare(volume, o.volume);
            if (c == 0) {
                return Integer.compare(hashCode(), o.hashCode());
            }
            return c;
        }
    }

    public Day17NoSuchThingAsTooMuch(String filename, int target) {
        List<Container> containers = FileUtilities.readLines(filename, s -> new Container(Integer.parseInt(s)));

        int[] volumes = new int[containers.size()];
        BitSet remaining = new BitSet(containers.size());
        for (int i = 0; i != containers.size(); i++) {
            volumes[i] = containers.get(i).volume;
            remaining.set(i);
        }

        Set<BitSet> groups = generate(volumes, new BitSet(), remaining, 0, target);

        this.part1Answer = groups.size();

        Map<Integer, Integer> counts = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (BitSet g : groups) {
            int key = g.cardinality();
            if (key < min) {
                min = key;
            }
            counts.put(key, counts.getOrDefault(key, 0) + 1);
        }

        this.part2Answer = counts.get(min);
    }

    private static Set<BitSet> generate(int[] volumes, BitSet soFar, BitSet remaining, int sum, int target) {
        Set<BitSet> result = new HashSet<>();
        for (int b = 0; b != remaining.length(); b++) {
            if (remaining.get(b)) {
                if (volumes[b] + sum > target) {
                    continue;
                }

                BitSet newSoFar = (BitSet) soFar.clone();
                newSoFar.set(b);

                if (volumes[b] + sum == target) {
                    result.add(newSoFar);
                    continue;
                }

                BitSet newRemaining = (BitSet) remaining.clone();
                newRemaining.clear(b);
                result.addAll(generate(volumes, newSoFar, newRemaining, sum + volumes[b], target));
            }
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
