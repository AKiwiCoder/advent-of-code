package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day09EncodingError(filename: String, preamble: Int) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename, _.toLong)

  @tailrec
  private def calcPair(target: Long, h: Long, t: List[Long]): Boolean = {
    if (t.isEmpty) {
      false
    } else {
      val found = t.filter(n => n + h == target)
      if (found.size == 1) {
        true
      } else {
        calcPair(target, t.head, t.tail)
      }
    }
  }

  private def check(idx: Int, current: Long): Boolean = {
    val lst = input.slice(idx - preamble, idx)
    calcPair(current, lst.head, lst.tail)
  }

  private def part1(): Long = {
    @tailrec
    def calc(idx: Int): Long = {
      val current = input(idx)
      if (!check(idx, current)) {
        current
      } else {
        calc(idx + 1)
      }
    }

    calc(preamble + 1)
  }

  private def part2(): Long = {
    val target = part1()

    @tailrec
    def calc(idx: Int, length: Int): Long = {
      val current = input.slice(idx, idx + length).sum
      if (current == target) {
        input.slice(idx, idx + length).min + input.slice(idx, idx + length).max
      } else if (current < target) {
        calc(idx, length + 1)
      } else {
        calc(idx + 1, 1)
      }
    }

    calc(0, 1)
  }

  override val part1Answer: Long = part1()
  override val part2Answer: Long = part2()
}


