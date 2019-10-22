package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day07InternetProtocolVersionSeven(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private def part1(lines: List[String]): Int = {
    @tailrec
    def abba(str: String): Boolean = {
      if (str.size < 4) {
        false
      } else {
        (str(0) == str(3)) && (str(1) == str(2) && (str(0) != str(1))) || abba(str.tail)
      }
    }

    def check(line: String): Boolean = {
      val chunks = line.split("[\\[\\]]").foldLeft((false, false, false))((acc, chunk) => (!acc._1,
        if (!acc._1) acc._2 || abba(chunk) else acc._2,
        if (acc._1) acc._3 || abba(chunk) else acc._3))
      chunks._2 && !chunks._3
    }

    lines.foldLeft(0)((acc, line) => if (check(line)) acc + 1 else acc)
  }

  private def part2(lines: List[String]): Int = {
    @tailrec
    def aba(str: String, acc: Set[String]): Set[String] = {
      if (str.size < 3) {
        acc
      } else {
        aba(str.tail, if (str(0) == str(2) && str(0) != str(1)) acc + str.take(3) else acc)
      }
    }

    def check(line: String): Boolean = {
      val (_, outside, inside) = line.split("[\\[\\]]").foldLeft((false, Set[String](), Set[String]()))((acc, chunk) => (!acc._1,
        if (!acc._1) aba(chunk, acc._2) else acc._2,
        if (acc._1) aba(chunk, acc._3) else acc._3))

      outside.toList.foldLeft(false)((acc, aba) => if (inside.contains("" + aba(1) + aba(0) + aba(1))) true else acc)
    }

    lines.foldLeft(0)((acc, line) => if (check(line)) acc + 1 else acc)
  }

  override val part1Answer: Int = part1(input)
  override val part2Answer: Int = part2(input)
}
