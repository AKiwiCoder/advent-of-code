package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day01NotQuiteLisp(filename: String) extends DailyProblem[Int, Int] {
  private val line = FileUtilities.readFile(filename).head

  @tailrec
  private def walk(line: List[Char], floor: Int, basement: Int, index: Int): (Int, Int) = {
    if (line.isEmpty) {
      (floor, basement)
    } else {
      val newFloor = if (line.head == '(') floor + 1 else floor - 1
      walk(line.tail, newFloor, if (newFloor == -1 && basement == -1) index else basement, index + 1)
    }
  }

  private val (finalFloor, firstBasement) = walk(line.toList, 0, -1, 1)

  override val part1Answer: Int = finalFloor
  override val part2Answer: Int = firstBasement
}
