package advent.twenty_twentyone 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day07TheTreacheryOfWhales(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).head.split(',').toList.map(_.toInt)

  def simple(num: Int) : Int = {
    num
  }

  def triangle(num : Int) : Int = {
    (num * (num + 1)) / 2
  }

  private def minimiseMoveCost( cost : (Int) => Int) : Int = {
    val minPos = input.min
    val maxPos = input.max

    (minPos to maxPos).toList.foldLeft(Int.MaxValue)((min, pos) => Math.min(min, input.foldLeft(0)((fuel, crab) => fuel + cost(Math.abs(crab - pos)))))
  }

  override val part1Answer: Int = minimiseMoveCost(simple)
  override val part2Answer: Int = minimiseMoveCost(triangle)
}


