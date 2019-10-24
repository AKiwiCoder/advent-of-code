package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

abstract class TwoFactorOperation {
  def update(data: Array[Array[Boolean]]): Array[Array[Boolean]] = ???
}

case class RectOperation(width: Int, height: Int) extends TwoFactorOperation {
  override def update(data: Array[Array[Boolean]]): Array[Array[Boolean]] = {
    for (x <- 0 to width-1) {
      for (y <- 0 to height-1) {
        data(y)(x) = true
      }
    }
    data
  }
}

case class RotateRowOperation(row: Int, count: Int) extends TwoFactorOperation {
  override def update(data: Array[Array[Boolean]]): Array[Array[Boolean]] = {
    val rightIndex = data(0).size -1
    (1 to count).foreach(_ => {
      val temp = data(row)(rightIndex)
      for (col <- rightIndex to 1 by -1) {
        data(row)(col) = data(row)(col-1)
      }
      data(row)(0) = temp
    })
    data
  }
}

case class RotateColumnOperation(col: Int, count: Int) extends TwoFactorOperation {
  override def update(data: Array[Array[Boolean]]): Array[Array[Boolean]] = {
    val bottomIndex = data.size -1
    (1 to count).foreach(c => {
      val temp = data(bottomIndex)(col)
      for (row <- bottomIndex to 1 by -1) {
        data(row)(col) = data(row-1)(col)
      }
      data(0)(col) = temp
    })
    data
  }
}

class Day08TwoFactorAuthentication(filename: String, width: Int, height: Int) extends DailyProblem[Int, String] {
  private val rectPattern = "rect ([a-z0-9]+)x([a-z0-9]+)".r
  private val rowPattern = "rotate row y=([a-z0-9]+) by ([a-z0-9]+)".r
  private val colPattern = "rotate column x=([a-z0-9]+) by ([a-z0-9]+)".r

  private def parser(line: String): TwoFactorOperation =
    line match {
      case rectPattern(width, height) => RectOperation(width.toInt, height.toInt)
      case rowPattern(row, count) => RotateRowOperation(row.toInt, count.toInt)
      case colPattern(col, count) => RotateColumnOperation(col.toInt, count.toInt)
      case _ => throw new IllegalStateException(s"Cannot match '$line'")
    }

  private val commands = FileUtilities.readFile(filename, parser)

  private val result = commands.foldLeft(Array.ofDim[Boolean](height, width))((array, command) => {
    command.update(array)
  })

  override val part1Answer: Int = result.foldLeft(0)((acc, row) => acc + row.count(p => p))
  override val part2Answer: String = result.foldLeft("")((acc, row) => acc + row.foldLeft("")((acc, cell) => acc + (if (cell) "#" else ".")) + "\n")
}
