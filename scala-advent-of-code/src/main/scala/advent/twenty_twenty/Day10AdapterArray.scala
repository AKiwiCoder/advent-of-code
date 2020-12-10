package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day10AdapterArray(filename: String) extends DailyProblem[Int, Long] {

  private val input = FileUtilities.readFile(filename, _.toInt)

  case class Entry(current : Int, used : List[Int], remaining : List[Int])

  private def part1(): Int = {
    val sorted = (0 :: (input.max + 3 :: input)).sorted.reverse
    val ansc = sorted.zip(sorted.tail).map(pair => pair._1 - pair._2)
    val oneCounts = ansc.count(a => a == 1)
    val threeCounts = ansc.count(a => a == 3)
    oneCounts * threeCounts
  }

  private def part2() : Long = {
    val sorted = (input.max + 3 :: input).sorted

    def calcPathsTo(num : Int, prev : Map[Int, Long]) : Long = {
      if (prev.contains(num)) {
        prev(num)
      } else if (sorted.indexOf(num) == -1) {
        0
      } else {
        val route1 = calcPathsTo(num - 1, prev)
        val route2 = calcPathsTo(num - 2, prev)
        val route3 = calcPathsTo(num - 3, prev)
        route1 + route2 + route3
      }
    }

    val paths = sorted.foldLeft(Map[Int, Long](0 -> 1))((map, current) => map + (current -> calcPathsTo(current, map)))
    paths(sorted.max)
  }

  override val part1Answer: Int = part1()
  override val part2Answer: Long = part2()
}


