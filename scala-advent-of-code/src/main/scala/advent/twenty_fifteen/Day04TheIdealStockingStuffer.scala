package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.{ChecksumUtilities, FileUtilities}

case class Point(col: Int, row: Int)

class Day04TheIdealStockingStuffer(key: String) extends DailyProblem[Int, Int] {

  override val part1Answer = ChecksumUtilities.findNextHashWithXZeros(key, 0, 5)._2
  override val part2Answer = ChecksumUtilities.findNextHashWithXZeros(key, 0, 6)._2
}
