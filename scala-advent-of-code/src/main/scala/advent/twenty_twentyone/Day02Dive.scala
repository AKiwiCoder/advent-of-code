package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day02Dive(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private val forward = "forward ([0-9])+".r
  private val up = "up ([0-9])+".r
  private val down = "down ([0-9])+".r
  private val aim = "aim ([0-9])+".r

  private def perform1(current: (Int, Int), command: String): (Int, Int) = {
    command match {
      case forward(s) => (current._1, current._2 + s.toInt)
      case up(s) => (current._1 - s.toInt, current._2)
      case down(s) => (current._1 + s.toInt, current._2)
    }
  }

  private def perform2(current: (Int, Int, Int), command: String): (Int, Int, Int) = {
    command match {
      case forward(s) => (current._1 + current._3 * s.toInt, current._2 + s.toInt, current._3)
      case up(s) => (current._1, current._2, current._3 - s.toInt)
      case down(s) => (current._1, current._2, current._3 + s.toInt)
    }
  }

  private val part1EndPos = input.foldLeft((0, 0))((acc, command) => perform1(acc, command))
  private val part2EndPos = input.foldLeft((0, 0, 0))((acc, command) => perform2(acc, command))

  override val part1Answer: Int = part1EndPos._1 * part1EndPos._2
  override val part2Answer: Int = part2EndPos._1 * part2EndPos._2
}


