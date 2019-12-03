package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point}

import scala.annotation.tailrec

class Day03CrossedWires(filename: String) extends DailyProblem[Int, Int] {

  case class Move(direction: Char, count: Int)

  private def parser(line: String): List[Move] = {
    line.split(",").map(str => Move(str.charAt(0), str.substring(1).toInt)).toList
  }

  private val input = FileUtilities.readFile(filename, parser)

  @tailrec
  private def generatePath(steps: List[Move], current: Point, path: List[Point]): List[Point] = {
    if (steps.isEmpty) {
      path.reverse
    } else {
      val step = steps.head
      val next = step.direction match {
        case 'U' => Point(current.y + 1, current.x)
        case 'D' => Point(current.y - 1, current.x)
        case 'L' => Point(current.y, current.x - 1)
        case 'R' => Point(current.y, current.x + 1)
      }
      generatePath(if (step.count > 1) Move(step.direction, step.count - 1) :: steps.tail else steps.tail, next, next :: path)
    }
  }

  private val paths = input.map(steps => generatePath(steps, Point(0, 0), List(Point(0, 0))))
  private val crossings = paths(0).toSet.intersect(paths(1).toSet) - Point(0, 0)

  override val part1Answer: Int = crossings.map(crossing => Point.manhattanDistance(crossing, Point(0, 0))).min
  override val part2Answer: Int = crossings.map(point => paths(0).indexOf(point) + paths(1).indexOf(point)).min
}
