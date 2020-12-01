package advent.twenty_twenty 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day01ReportRepair(filename : String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename, parse)

  private def parse(line: String): Int = line.toInt

  private def part1(): Int = {
    def calc(h : Int, t : List[Int]) : Int = {
      if (t.isEmpty) {
        -1
      } else {
        val found = t.filter(n => n + h == 2020)
        if (found.size == 1) {
          h * found.head
        } else {
          calc(t.head, t.tail)
        }
      }
    }
    calc(input.head, input.tail)
  }

  private def part2(): Int = {
    val t = input.flatMap(a => input.tail.map(b => input.tail.tail.map(c => if (a + b + c == 2020) a * b * c else 0))).map(t => t.sum).filter(t => t > 0).head
    t
  }

  override val part1Answer: Int = part1()
  override val part2Answer: Int = part2()
}


