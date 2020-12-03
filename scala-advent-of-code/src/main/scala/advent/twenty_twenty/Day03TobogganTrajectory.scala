package advent.twenty_twenty 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day03TobogganTrajectory(filename : String) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename)

  def walk(x : Int, y : Int, dX : Int, dY : Int, count : Long): Long = {
    if (y >= input.size) {
      count
    } else {
      val nX = (x + dX) % input(0).size
      val nY = y + dY
      walk(nX, nY, dX, dY, if (input(y)(x) == '#') count +1 else count)
    }
  }

  private val run1 = walk(0,0, 1,  1, 0)
  private val run2 = walk(0,0, 3,  1, 0)
  private val run3 = walk(0,0, 5,  1, 0)
  private val run4 = walk(0,0, 7,  1, 0)
  private val run5 = walk(0,0, 1,  2, 0)

  override val part1Answer: Long = run2
  override val part2Answer: Long = run1 * run2 * run3 * run4 * run5
}


