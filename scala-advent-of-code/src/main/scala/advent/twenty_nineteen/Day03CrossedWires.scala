package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point}

import scala.annotation.tailrec

case class Move(direction: Char, count: Int)

class Day03CrossedWires(filename: String) extends DailyProblem[Int, Int] {
  private def parser(line: String): List[Move] = {
    line.split(",").map(str => Move(str.charAt(0), str.substring(1).toInt)).toList
  }

  private val input = FileUtilities.readFile(filename, parser)

  def manhattanDistance(current: Point, start: Point): Int = Math.abs(start.y - current.y) + Math.abs(start.x - current.x)

  def generatePath(steps: List[Move]): List[Point] = {
    var result = scala.collection.mutable.ListBuffer[Point]()
    var current = Point(0, 0)
    result += current
    for (step <- steps) {
      for (s <- 0 until step.count) {
        step.direction match {
          case 'U' => current = Point(current.y + 1, current.x)
          case 'D' => current = Point(current.y - 1, current.x)
          case 'L' => current = Point(current.y, current.x - 1)
          case 'R' => current = Point(current.y, current.x + 1)
        }
        result += current
      }
    }
    result.toList
  }

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

  private val paths = input.map(steps => generatePath(steps, Point(0, 0), List(Point(0,0))))
  private val crossings = paths(0).toSet.intersect(paths(1).toSet) - Point(0, 0)

  override val part1Answer: Int = crossings.map(crossing => manhattanDistance(crossing, Point(0, 0))).min
  override val part2Answer: Int = crossings.map(point => paths(0).indexOf(point) + paths(1).indexOf(point)).min
}
