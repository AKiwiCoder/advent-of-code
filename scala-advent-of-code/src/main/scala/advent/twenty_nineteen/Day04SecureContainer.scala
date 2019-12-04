package advent.twenty_nineteen

import advent.common.DailyProblem

import scala.annotation.tailrec

class Day04SecureContainer(lower: Int, upper: Int) extends DailyProblem[Int, Int] {
  @tailrec
  private def count(current: Int, upper: Int, part1: Int, part2: Int): (Int, Int) = {
    if (current > upper) {
      (part1, part2)
    } else {
      val digits = current.toString.map(_.asDigit)

      if (digits.sorted == digits) {
        val counts = digits.groupBy(i => i).map(entry => entry._1 -> entry._2.length).values.toSet
        count(current + 1, upper, if (counts.count(c => c >= 2) > 0) part1 + 1 else part1, if (counts.contains(2)) part2 + 1 else part2)
      } else {
        count(current + 1, upper, part1, part2)
      }
    }
  }

  private val (p1, p2) = count(lower, upper, 0, 0)

  override val part1Answer: Int = p1
  override val part2Answer: Int = p2
}