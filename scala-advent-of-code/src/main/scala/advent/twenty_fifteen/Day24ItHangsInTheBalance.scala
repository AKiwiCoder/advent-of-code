package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.collection.BitSet

class Day24ItHangsInTheBalance(filename: String) extends DailyProblem[Long, Long] {

  private val weights = FileUtilities.readFile(filename, s => s.toInt)

  private val totalWeights = weights.sum

  private val starting = (0 to weights.length).toList.foldLeft(BitSet())((acc, idx) => acc + idx)

  /*
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
   */

  private def calculate( soFar: BitSet, remaining: BitSet, sum: Int, target: Int, limit: Int, bestSoFar: Long) = {
    0
  }



  override val part1Answer: Long = calculate( BitSet(), starting, 0, totalWeights / 3, 4, Long.MaxValue)
  override val part2Answer: Long = calculate( BitSet(), starting, 0, totalWeights / 3, 4, Long.MaxValue)
}
