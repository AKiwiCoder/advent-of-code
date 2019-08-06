package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day25LetItSnow(filename: String) extends DailyProblem[Long, Void] {
  private val manualPattern = "To continue, please consult the code grid in the manual\\.  Enter the code at row ([0-9]+), column ([0-9]+)\\.".r

  private def parser(line: String): (Int, Int) =
    line match {
      case manualPattern(row, column) => (row.toInt, column.toInt)
      case _ => throw new IllegalStateException(s"Cannot match '$line'")
    }

  private val code = FileUtilities.readFile(filename, parser)(0)

  private def calc(previous : Long) : Long = (previous * 252533) % 33554393

  private def calculate(targetRow: Int, targetCol: Int): Long = {
    @tailrec
    def walk(r: Int, c: Int, previous: Long): Long = {
      if (r == targetRow && c == targetCol) {
        calc(previous)
      } else {
        if (r == 1) {
          walk(c + 1, 1,  calc(previous))
        } else {
          walk(r - 1, c + 1, calc(previous))
        }
      }
    }

    walk(2, 1, 20151125)
  }

  override val part1Answer: Long = calculate(code._1, code._2)
  override val part2Answer: Void = null
}
