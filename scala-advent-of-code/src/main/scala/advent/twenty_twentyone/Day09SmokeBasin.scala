package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

class Day09SmokeBasin(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).map(_.toCharArray.map(c => ("" + c).toInt))

  private val maxX = input.head.length - 1
  private val maxY = input.length - 1

  private def higher(value: Int, x: Int, y: Int): Boolean = {
    if (x < 0 || y < 0 || x > maxX || y > maxY) {
      true
    } else {
      value < input(y)(x)
    }
  }

  private def findLowPointsRisk(): List[Point2d] = {
    @tailrec
    def walk(x: Int, y: Int, acc: List[Point2d]): List[Point2d] = {
      if (y > maxY) {
        acc
      } else {
        val value = input(y)(x)
        val newAcc = if (higher(value, x - 1, y) && higher(value, x + 1, y) && higher(value, x, y - 1) && higher(value, x, y + 1)) {
          Point2d(x, y) :: acc
        } else {
          acc
        }
        val (newX, newY) = if (x >= maxX) (0, y + 1) else (x + 1, y)
        walk(newX, newY, newAcc)
      }
    }

    walk(0, 0, List())
  }

  private def walkLowPoint(point: Point2d): List[Point2d] = {
    def check(p: Point2d): Boolean = {
      p.x >= 0 && p.y >= 0 && p.x <= maxX && p.y <= maxY && input(p.y)(p.x) != 9
    }

    @tailrec
    def visit(toVisit: List[Point2d], visited: Set[Point2d], included: Set[Point2d]): Set[Point2d] = {
      if (toVisit.isEmpty) {
        included
      } else {
        val current = toVisit.head
        val newIncluded = if (input(current.y)(current.x) < 9) included + current else included
        val newToVisit = List(Point2d(current.x - 1, current.y), Point2d(current.x + 1, current.y), Point2d(current.x, current.y - 1), Point2d(current.x, current.y + 1)).filter(check) ::: toVisit.tail
        visit(newToVisit.filter(p => !visited.contains(p)), visited + current, newIncluded)
      }
    }

    visit(List(point), Set(), Set()).toList
  }

  override val part1Answer: Int = findLowPointsRisk().map(point => input(point.y)(point.x) + 1).sum;
  override val part2Answer: Int = findLowPointsRisk().map(point => walkLowPoint(point).length).sorted.reverse.take(3).product
}


