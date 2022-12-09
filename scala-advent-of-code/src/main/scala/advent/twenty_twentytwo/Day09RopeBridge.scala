package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class Point(x: Int, y: Int)

class Day09RopeBridge(filename: String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename).foldLeft(List[Char]())((lst, move) => (0 until move.tail.trim.toInt).foldLeft(lst)((l, _) => move(0) :: l)).reverse

  println(input)

  private def close(a: Point, b: Point): Boolean = {
    Math.abs(a.x - b.x) <= 1 && Math.abs(a.y - b.y) <= 1
  }

  private def tail_moves(headLoc: Point, tailLoc: Point): Point = {
    Point(tailLoc.x + Math.round(Math.signum(headLoc.x - tailLoc.x)), tailLoc.y + Math.round(Math.signum(headLoc.y - tailLoc.y)))
  }

  private def walk(moves: List[Char], headLoc: Point, tailLocs: List[Point], tailVisits: Set[Point]): Int = {
    if (moves.isEmpty) {
      tailVisits.size
    } else {
      val move = moves.head
      val newHeadLoc = move match {
        case 'U' => Point(headLoc.x, headLoc.y + 1)
        case 'D' => Point(headLoc.x, headLoc.y - 1)
        case 'L' => Point(headLoc.x - 1, headLoc.y)
        case 'R' => Point(headLoc.x + 1, headLoc.y)
      }
      val newTailLocs = tailLocs.foldLeft((headLoc, List[Point]()))((acc, me) => if (close(acc._1, me)) (me, me :: acc._2) else (tail_moves(acc._1, me), tail_moves(acc._1, me) :: acc._2))._2.reverse
      println(newTailLocs)
      walk(moves.tail, newHeadLoc, newTailLocs, tailVisits + newTailLocs.last)
    }
  }

  override val part1Answer: Int = walk(input, Point(0, 0), List(Point(0, 0)), Set(Point(0, 0)))
  override val part2Answer: Int = walk(input, Point(0, 0), List(Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0)), Set(Point(0, 0))) + 1
}

