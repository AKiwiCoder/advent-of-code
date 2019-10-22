package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import advent.utilities.Parsers;

import java.util.BitSet;
import java.util.List;

public class Day24ItHangsInTheBalance implements DailyProblem<Long, Long> {
    private final long part1Answer;
    private final long part2Answer;

    public Day24ItHangsInTheBalance(String filename) {
        List<Integer> parcels = FileUtilities.readLines(filename, Parsers::TO_INTEGER);

        int sum = 0;
        int[] weights = new int[parcels.size()];
        BitSet remaining = new BitSet(parcels.size());
        for (int i = 0; i != parcels.size(); i++) {
            int w = parcels.get(i);
            sum += w;
            weights[i] = w;
            remaining.set(i);
        }

        this.part1Answer = generate(weights, new BitSet(), remaining, 0, sum / 3, 4, Long.MAX_VALUE);
        this.part2Answer = generate(weights, new BitSet(), remaining, 0, sum / 4, 4, Long.MAX_VALUE);
    }

    private static long product(int[] weights, BitSet bits) {
        long result = 1;
        for (int i = 0; i != bits.length(); i++) {
            if (bits.get(i)) {
                result = result * weights[i];
            }
        }
        return result;
    }


    private static long generate(int[] weights, BitSet soFar, BitSet remaining, int sum, int target, int limit, long best) {
        for (int b = 0; b != remaining.length(); b++) {
            if (remaining.get(b)) {
                if (weights[b] + sum > target) {
                    continue;
                }

                BitSet newSoFar = (BitSet) soFar.clone();
                newSoFar.set(b);

                if (weights[b] + sum == target) {
                    long currentEntanglement = product(weights, newSoFar);
                    if (currentEntanglement < best)
                        best = currentEntanglement;
                    continue;
                }

                if (soFar.cardinality() > limit) {
                    continue;
                }

                BitSet newRemaining = (BitSet) remaining.clone();
                newRemaining.clear(b);
                best = Math.min(best, generate(weights, newSoFar, newRemaining, sum + weights[b], target, limit, best));
            }
        }
        return best;
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
