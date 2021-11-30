package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day18LikeAGifForYourYard(filename: String, steps: Int) extends DailyProblem[Int, Int] {
  private def parser(line: String): Array[Boolean] = line.map(c => c == '#').toArray

  private val startPattern = FileUtilities.readFile(filename, parser).toArray

  private def count(grid: Array[Array[Boolean]]) = grid.foldLeft(0)((acc, row) => acc + row.foldLeft(0)((acc, cell) => if (cell) acc + 1 else acc))

  private def calculate(row: Int, col: Int, grid: Array[Array[Boolean]]): Boolean = {
    val atTop = row == 0
    val atBottom = row == grid.length - 1
    val atLeft = col == 0
    val atRight = col == grid(0).length - 1


    val tl = if (!atTop && !atLeft && grid(row - 1)(col - 1)) 1 else 0
    val tc = if (!atTop && grid(row - 1)(col + 0)) 1 else 0
    val tr = if (!atTop && !atRight && grid(row - 1)(col + 1)) 1 else 0
    val cl = if (!atLeft && grid(row + 0)(col - 1)) 1 else 0
    val cr = if (!atRight && grid(row + 0)(col + 1)) 1 else 0
    val bl = if (!atBottom && !atLeft && grid(row + 1)(col - 1)) 1 else 0
    val bc = if (!atBottom && grid(row + 1)(col + 0)) 1 else 0
    val br = if (!atBottom && !atRight && grid(row + 1)(col + 1)) 1 else 0

    val count = tl + tc + tr + cl + cr + bl + bc + br

    if (grid(row)(col)) {
      count == 2 || count == 3
    } else {
      count == 3
    }
  }

  @tailrec
  private def stepGrid(grid: Array[Array[Boolean]], step: Int, func: (Array[Array[Boolean]]) => Array[Array[Boolean]]): Array[Array[Boolean]] = {
    func(grid)
    if (step <= 0) {
      grid
    } else {
      val new_grid = Array.ofDim[Array[Boolean]](grid.length)
      for (row <- grid.indices) {
        new_grid.update(row, Array.ofDim[Boolean](grid(0).length))
        for (col <- grid(row).indices) {
          new_grid(row)(col) = calculate(row, col, grid)
        }
      }
      stepGrid(new_grid, step - 1, func)
    }
  }

  private def doNothing(grid: Array[Array[Boolean]]): Array[Array[Boolean]] = grid

  private def resetCorners(grid: Array[Array[Boolean]]): Array[Array[Boolean]] = {
    grid(0)(0) = true
    grid(0)(grid(0).length - 1) = true
    grid(grid.length - 1)(0) = true
    grid(grid.length - 1)(grid(0).length - 1) = true

    grid
  }

  override val part1Answer: Int = count(stepGrid(startPattern, steps, doNothing))
  override val part2Answer: Int = count(stepGrid(startPattern, steps, resetCorners))
}
