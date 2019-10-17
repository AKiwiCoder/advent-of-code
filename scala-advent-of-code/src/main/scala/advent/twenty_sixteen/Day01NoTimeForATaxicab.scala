package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.{East, Facing, FileUtilities, Left, LocationHelper, North, Point, Right, South, Turn, West}

import scala.annotation.tailrec

class Day01NoTimeForATaxicab(filename: String) extends DailyProblem[Int, Int] {
  private def parser(line: String): List[(Turn, Int)] = line.split(", ").map(step => {
    if (step.charAt(0) == 'L') {
      (Left(), step.substring(1).toInt)
    } else {
      (Right(), step.substring(1).toInt)
    }
  }).toList

  private val moves = parser(FileUtilities.readFile(filename)(0))

  private val journey = moves.foldLeft[(Facing, Point, List[Point])]((North(), Point(0, 0), List(Point(0, 0))))((current, step) => {
    val newFacing : Facing = LocationHelper.turn(current._1, step._1)
    val (newPosition : Point, steps : List[Point]) = LocationHelper.steps(current._2, newFacing, step._2, List())
    (newFacing, newPosition, steps ::: current._3)
  })

  def manhattanDistance(current: Point, start: Point): Int = Math.abs(start.y - current.y) + Math.abs(start.x - current.x)

  @tailrec
  private def search(seen: Set[Point], remaining: List[Point]): Point = {
    if (remaining.isEmpty) {
      Point(0, 0)
    } else {
      val current = remaining.head
      if (seen.contains(current)) {
        current
      } else {
        search(seen + current, remaining.tail)
      }
    }
  }

  override val part1Answer: Int = manhattanDistance(journey._2, Point(0, 0))
  override val part2Answer: Int = manhattanDistance(search(Set(), journey._3.reverse), Point(0, 0))
}
