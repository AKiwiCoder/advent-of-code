package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day11SeatingSystem(filename: String) extends DailyProblem[Int, Int] {
  private val FLOOR = '.'
  private val EMPTY_SEAT = 'L'
  private val FULL_SEAT = '#'

  private val input = FileUtilities.readFile(filename, _.toList.zipWithIndex).zipWithIndex.flatMap(row => row._1.map(col => ((row._2, col._2) -> col._1))).toMap.filter(e => e._2 != FLOOR)

  private val maxRow = input.map(e => e._1._1).max
  private val maxCol = input.map(e => e._1._2).max

  @tailrec
  private def process(current: Map[(Int, Int), Char], seatLimit : Int, countEmptySeats : (Map[(Int, Int), Char], (Int,Int)) => Int): Int = {
    val next =  current.map(old => (old._1, old._2 match {
      case EMPTY_SEAT => if (countEmptySeats(current, old._1) == 0) FULL_SEAT else EMPTY_SEAT
      case FULL_SEAT => if (countEmptySeats(current, old._1) >= seatLimit) EMPTY_SEAT else FULL_SEAT
    }))

    if (next.equals(current)) {
      current.count(p => p._2 == FULL_SEAT)
    } else {
      process(next, seatLimit, countEmptySeats)
    }
  }

  private def countAdjecent(current: Map[(Int, Int), Char], pos: (Int, Int)): Int = {
    val tl = if (current.getOrElse((pos._1 - 1, pos._2 - 1), EMPTY_SEAT) == EMPTY_SEAT) 0 else 1
    val tt = if (current.getOrElse((pos._1 - 1, pos._2 - 0), EMPTY_SEAT) == EMPTY_SEAT) 0 else 1
    val tr = if (current.getOrElse((pos._1 - 1, pos._2 + 1), EMPTY_SEAT) == EMPTY_SEAT) 0 else 1
    val ll = if (current.getOrElse((pos._1 + 0, pos._2 - 1), EMPTY_SEAT) == EMPTY_SEAT) 0 else 1
    val rr = if (current.getOrElse((pos._1 + 0, pos._2 + 1), EMPTY_SEAT) == EMPTY_SEAT) 0 else 1
    val bl = if (current.getOrElse((pos._1 + 1, pos._2 - 1), EMPTY_SEAT) == EMPTY_SEAT) 0 else 1
    val bb = if (current.getOrElse((pos._1 + 1, pos._2 + 0), EMPTY_SEAT) == EMPTY_SEAT) 0 else 1
    val br = if (current.getOrElse((pos._1 + 1, pos._2 + 1), EMPTY_SEAT) == EMPTY_SEAT) 0 else 1
    tl + tt + tr + ll + bl + bb + br + rr
  }

  private def countVisible(current: Map[(Int, Int), Char], pos: (Int, Int)): Int = {
    @tailrec
    def lookDirection(current: Map[(Int, Int), Char], pos: (Int, Int), dC: Int, dR: Int): Char = {
      val pR = pos._1
      val pC = pos._2
      if (pR < 0 || pR > maxRow || pC < 0 || pC > maxCol) {
        EMPTY_SEAT
      } else {
        val newPos = (pos._1 + dR, pos._2 + dC)
        current.get(newPos) match {
          case None => lookDirection(current, newPos, dC, dR)
          case Some(c) => c
        }
      }
    }

    val tl = if (lookDirection(current, pos, -1, -1) == EMPTY_SEAT) 0 else 1
    val tt = if (lookDirection(current, pos, -1, -0) == EMPTY_SEAT) 0 else 1
    val tr = if (lookDirection(current, pos, -1, +1) == EMPTY_SEAT) 0 else 1
    val ll = if (lookDirection(current, pos, +0, -1) == EMPTY_SEAT) 0 else 1
    val rr = if (lookDirection(current, pos, +0, +1) == EMPTY_SEAT) 0 else 1
    val bl = if (lookDirection(current, pos, +1, -1) == EMPTY_SEAT) 0 else 1
    val bb = if (lookDirection(current, pos, +1, +0) == EMPTY_SEAT) 0 else 1
    val br = if (lookDirection(current, pos, +1, +1) == EMPTY_SEAT) 0 else 1
    tl + tt + tr + ll + bl + bb + br + rr
  }

  override val part1Answer: Int = process(input, 4, countAdjecent)
  override val part2Answer: Int = process(input, 5, countVisible)
}


