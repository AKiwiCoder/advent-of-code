package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

case class Point(col: Int, row: Int)

class Day03PerfectlySpericalHousesInAVacuum(filename: String) extends DailyProblem[Int, Int] {
  private val moves = FileUtilities.readFile(filename).head

  private def calculateMove(point : Point, c: Char) = {
    val newP = c match {
      case '^' => Point(point.col, point.row - 1)
      case 'v' => Point(point.col, point.row + 1)
      case '<' => Point(point.col - 1, point.row)
      case '>' => Point(point.col + 1, point.row)
      case _ => throw new IllegalArgumentException(c + " is not parsable")
    }
    newP
  }

  override val part1Answer = moves.toCharArray.foldLeft((Point(0,0), Set(Point(0,0))))((acc,c) => {
    val newP: Point = calculateMove(acc._1, c)
    (newP, acc._2 + newP)
  })._2.size

  override val part2Answer = moves.toCharArray.foldLeft((true, Point(0,0), Point(0,0), Set(Point(0,0))))((acc,c) => {
    if (acc._1) {
      val newP: Point = calculateMove(acc._2, c)
      (false, newP, acc._3, acc._4 + newP)
    } else {
      val newP: Point = calculateMove(acc._3, c)
      (true, acc._2, newP, acc._4 + newP)
    }
  })._4.size
}
