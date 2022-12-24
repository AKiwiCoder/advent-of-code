package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec


class Day24BlizzardBasin(filename: String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename).map(_.toCharArray.toList)

  private val startPoint = Point(input(0).indexWhere(_ == '.'), 0)
  private val endPoint = Point(input.last.indexWhere(_ == '.'), input.size - 1)

  private val (startingUp, startingDown, startingLeft, startingRight) = input.indices.foldLeft((Set[Point](), Set[Point](), Set[Point](), Set[Point]()))((acc, y) => input(0).indices.foldLeft(acc)((a, x) => input(y)(x) match {
    case '.' => a
    case '^' => (a._1 + Point(x, y), a._2, a._3, a._4)
    case 'v' => (a._1, a._2 + Point(x, y), a._3, a._4)
    case '<' => (a._1, a._2, a._3 + Point(x, y), a._4)
    case '>' => (a._1, a._2, a._3, a._4 + Point(x, y))
    case '#' => a
  }))

  case class Point(x: Int, y: Int)

  private val minX = 1
  private val maxX = input(0).size - 2
  private val minY = 1
  private val maxY = input.size - 2

  def up(p: Point) = Point(p.x, p.y - 1)

  def down(p: Point) = Point(p.x, p.y + 1)

  def left(p: Point) = Point(p.x - 1, p.y)

  def right(p: Point) = Point(p.x + 1, p.y)

  def moveStormsUp(s: Set[Point]): Set[Point] = s.map(p => if (p.y == minY) Point(p.x, maxY) else up(p))

  def moveStormsDown(s: Set[Point]): Set[Point] = s.map(p => if (p.y == maxY) Point(p.x, minY) else down(p))

  def moveStormsLeft(s: Set[Point]): Set[Point] = s.map(p => if (p.x == minX) Point(maxX, p.y) else left(p))

  def moveStormsRight(s: Set[Point]): Set[Point] = s.map(p => if (p.x == maxX) Point(minX, p.y) else right(p))

  def isValidLocation(p: Point) = startPoint == p || endPoint == p || ((p.x >= minX) && (p.x <= maxX) && (p.y >= minY) && (p.y <= maxY))

  @tailrec
  private def generateStorms(step: Int, up: Set[Point], down: Set[Point], left: Set[Point], right: Set[Point], acc: Map[Int, (Set[Point], Set[Point], Set[Point], Set[Point])]): Map[Int, (Set[Point], Set[Point], Set[Point], Set[Point])] = {
    val nUp = moveStormsUp(up)
    val nDown = moveStormsDown(down)
    val nLeft = moveStormsLeft(left)
    val nRight = moveStormsRight(right)

    val nextState = (nUp, nDown, nLeft, nRight)
    if (acc.values.toSet.contains(nextState)) {
      acc
    } else {
      generateStorms(step + 1, nUp, nDown, nLeft, nRight, acc + ((step + 1) -> nextState))
    }
  }

  private val STORM_POSITIONS = generateStorms(0, startingUp, startingDown, startingLeft, startingRight, Map(0 -> (startingUp, startingDown, startingLeft, startingRight)))

  private val STORM_MAP = STORM_POSITIONS.map(entry => entry._1 -> (entry._2._1 ++ entry._2._2 ++ entry._2._3 ++ entry._2._4))

  @tailrec
  private def run(minute : Int, target : Point, positions : Set[Point]) : Int = {
    if (positions.contains(target)) {
      minute
    } else {
      val newPositions = positions.foldLeft(Set[Point]())((acc, src) => {
        val mUp = up(src)
        val mDown = down(src)
        val mLeft = left(src)
        val mRight = right(src)

        val allMoves = List(mUp, mDown, mLeft, mRight, src)
        val stormNumber = (minute + 1) % STORM_MAP.size
        val goodMoves = allMoves.filter(p => isValidLocation(p) && !STORM_MAP(stormNumber).contains(p)).toSet
        acc ++ goodMoves
      })
      run(minute + 1, target, newPositions)
    }
  }

  private def part2() : Int = {
    val toEnd = run(0, endPoint, Set(startPoint))
    val toStart = run(toEnd, startPoint, Set(endPoint))
    val andBack = run(toStart, endPoint, Set(startPoint))
    andBack
  }

  override val part1Answer: Int = run(0, endPoint, Set(startPoint))
  override val part2Answer: Int = part2()
}
