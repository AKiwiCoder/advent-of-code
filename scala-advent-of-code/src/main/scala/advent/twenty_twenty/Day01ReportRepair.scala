package advent.twenty_twenty 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day01ReportRepair(filename : String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename, parse)

  private def parse(line: String): Int = line.toInt

  def calcPair(target : Int, h : Int, t : List[Int]) : Int = {
    if (t.isEmpty) {
      -1
    } else {
      val found = t.filter(n => n + h == target)
      if (found.size == 1) {
        h * found.head
      } else {
        calcPair(target, t.head, t.tail)
      }
    }
  }

  def calcTriple(target : Int, h : Int, t : List[Int]) : Int = {
    if (t.isEmpty) {
      -1
    } else {
      val found = calcPair(target - h, t.head, t.tail)
      if (found != -1) {
        h * found
      } else {
        calcTriple(target, t.head, t.tail)
      }
    }
  }

  override val part1Answer: Int = calcPair(2020, input.head, input.tail)
  override val part2Answer: Int = calcTriple(2020, input.head, input.tail)
}


