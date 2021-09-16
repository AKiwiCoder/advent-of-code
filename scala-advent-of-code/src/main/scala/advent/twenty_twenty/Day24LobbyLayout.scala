package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

class Day24LobbyLayout(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private val east = "^e([nesw]*)".r
  private val southEast = "^se([nesw]*)".r
  private val southWest = "^sw([nesw]*)".r
  private val west = "^w([nesw]*)".r
  private val northWest = "^nw([nesw]*)".r
  private val northEast = "^ne([nesw]*)".r

  private def east(current: Point2d): Point2d = Point2d(current.x + 2, current.y)

  private def southEast(current: Point2d): Point2d = Point2d(current.x + 1, current.y + 1)

  private def southWest(current: Point2d): Point2d = Point2d(current.x - 1, current.y + 1)

  private def west(current: Point2d): Point2d = Point2d(current.x - 2, current.y)

  private def northWest(current: Point2d): Point2d = Point2d(current.x - 1, current.y - 1)

  private def northEast(current: Point2d): Point2d = Point2d(current.x + 1, current.y - 1)

  @tailrec
  private def walk(current: Point2d, grid: Map[Point2d, Boolean], steps: String): Map[Point2d, Boolean] = {
    if (steps.isEmpty) {
      grid + (current -> !grid.getOrElse(current, false))
    } else {
      val (newSteps, newCurrent) = steps match {
        case east(tail) => (tail, east(current))
        case southEast(tail) => (tail, southEast(current))
        case southWest(tail) => (tail, southWest(current))
        case west(tail) => (tail, west(current))
        case northWest(tail) => (tail, northWest(current))
        case northEast(tail) => (tail, northEast(current))
      }
      walk(newCurrent, grid, newSteps)
    }
  }

  private val inputGrid = input.foldLeft(Map[Point2d, Boolean]())((acc, line) => acc ++ walk(Point2d(0, 0), acc, line)).filter(_._2).keys.toSet

  def part2(): Int = {
    def countAdjacentBlack(current : Point2d, grid : Set[Point2d]) : Int = {
      val e = grid.contains(east(current))
      val se  = grid.contains(southEast(current))
      val sw = grid.contains(southWest(current))
      val w = grid.contains(west(current))
      val nw =  grid.contains(northWest(current))
      val ne =  grid.contains(northEast(current))

      List(e, se, sw, w, nw, ne).count(e => e)
    }

    def checkBlack(current: Point2d, grid: Set[Point2d]): Boolean = {
      val count = countAdjacentBlack(current, grid)
      !(count == 0 || count > 2)
    }

    def checkWhite(grid: Set[Point2d]): Set[Point2d] = {
      val setOfWhiteToCheck = grid.foldLeft(Set[Point2d]())((acc, current) => acc.union(Set(east(current), southEast(current),southWest(current),west(current),northWest(current),northEast(current))))
      setOfWhiteToCheck.filter(white => countAdjacentBlack(white, grid) == 2)
    }

    @tailrec
    def flip(move: Int, grid: Set[Point2d]): Set[Point2d] = {
      if (move == 100) {
        grid
      } else {
        val newGrid = grid.filter(point => checkBlack(point, grid))
        flip(move + 1, newGrid.union(checkWhite(grid)))
      }
    }

    flip(0, inputGrid).size
  }

  override val part1Answer: Int = inputGrid.size
  override val part2Answer: Int = part2()
}


