package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day08TreetopTreeHouse(filename: String) extends DailyProblem[Long, Long] {
  private val input = FileUtilities.readFile(filename).map(a => a.toCharArray.map(h => ("" + h).toInt).toList)

  private val bottom = input.size
  private val right = input.head.length

  @tailrec
  private def is_hidden(x: Int, y: Int, dx: Int, dy: Int, height: Int): Boolean = {
    if (x < 0 || y < 0 || x >= right || y >= bottom) {
      true
    } else {
      if (input(y)(x) >= height) {
        false
      } else {
        is_hidden(x + dx, y + dy, dx, dy, height)
      }
    }
  }

  private def check_hidden(x: Int, y: Int, height: Int): Int = {
    val n = if (y == 0) true else is_hidden(x, y - 1, 0, -1, height)
    val s = if (y == bottom - 1) true else is_hidden(x, y + 1, 0, +1, height)
    val w = if (x == 0) true else is_hidden(x - 1, y, -1, 0, height)
    val e = if (x == right - 1) true else is_hidden(x + 1, y, 1, 0, height)
    if (n || s || w || e) 1 else 0
  }

  private def scan_hidden(): Int = {
    (0 until bottom).foldLeft(0)((a, y) => (0 until right).foldLeft(a)((acc, x) => {
      acc + check_hidden(x, y, input(y)(x))
    }))
  }

  private def count_visible(x: Int, y: Int, dx: Int, dy: Int, height: Int, count : Int): Int = {
    if (x < 0 || y < 0 || x >= right || y >= bottom) {
      0
    } else {
      if (input(y)(x) >= height) {
        1
      } else {
        1 + count_visible(x + dx, y + dy, dx, dy, height, count)
      }
    }
  }

  private def check_view(x: Int, y: Int, height: Int): Int = {
    val n = count_visible(x, y-1, 0, -1, height,0)
    val s = count_visible(x, y+1, 0, +1, height,0)
    val w = count_visible(x-1, y, -1, 0, height,0)
    val e = count_visible(x+1, y, 1, 0, height,0)
    n * s * w * e
  }

  private def scan_view(): Int = {
    (1 until bottom - 1).map(y => (1 until right - 1).map(x => check_view(x, y, input(y)(x))).toList.max).toList.max
  }

  override val part1Answer: Long = scan_hidden()
  override val part2Answer: Long = scan_view()
}

