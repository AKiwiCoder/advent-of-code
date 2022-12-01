package advent.twenty_twentytwo 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day01CalorieCounting(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  val elves = input.foldLeft((0, List[Int]()))((acc, str) => if (str.trim().isEmpty()) (0, acc._1 :: acc._2) else (acc._1 + str.toInt, acc._2))._2.sorted.reverse

  override val part1Answer: Int = elves.head
  override val part2Answer: Int = elves.take(3).sum
}


