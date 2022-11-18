package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.MathHelpers.sign
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

class Day05HydrothermalVenture(filename: String) extends DailyProblem[Int, Int] {

  private val inputPattern = "([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)".r

  private def parser(line: String): (Point2d, Point2d) = {
    line match {
      case inputPattern(x1, y1, x2, y2) => (Point2d(x1.toInt, y1.toInt), Point2d(x2.toInt, y2.toInt))
    }
  }

  private val input = FileUtilities.readFile(filename, parser)

  private def generatePointsOnLine(start: Point2d, end: Point2d): List[Point2d] = {
    def walkLine(current: Point2d, delta: Point2d, end: Point2d, points: List[Point2d]): List[Point2d] = {
      if (current == end) {
        points
      } else {
        val newCurrent = Point2d(current.x + delta.x, current.y + delta.y)
        walkLine(newCurrent, delta, end, newCurrent :: points)
      }
    }

    val delta = Point2d(sign(end.x - start.x), sign(end.y - start.y))
    walkLine(start, delta, end, List(start))
  }

  private def findSafePoints(diagonals: Boolean): Int = {
    @tailrec
    def generatePointMap(lines: List[(Point2d, Point2d)], covered: Map[Point2d, Int]): Int = {
      if (lines.isEmpty) {
        covered.count(_._2 >= 2)
      } else {
        val (start, end) = lines.head
        val points = if (start.x == end.x || start.y == end.y || diagonals) {
          generatePointsOnLine(start, end)
        } else {
          List()
        }
        val newCovered = points.foldLeft(covered)((acc, point) => acc + (point -> (acc.getOrElse(point, 0) + 1)))
        generatePointMap(lines.tail, newCovered)
      }
    }

    generatePointMap(input, Map[Point2d, Int]())
  }


  override val part1Answer: Int = findSafePoints(false)
  override val part2Answer: Int = findSafePoints(true)
}
