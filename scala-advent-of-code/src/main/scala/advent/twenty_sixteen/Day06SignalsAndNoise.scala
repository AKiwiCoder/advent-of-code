package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day06SignalsAndNoise(filename: String) extends DailyProblem[String, String] {

  private val input = FileUtilities.readFile(filename)

  private def processInput(lines: List[String]): Map[Int, Map[Char, Int]] = {

    @tailrec
    def countCharacters(line: String, pos: Int, characters: Map[Int, Map[Char, Int]]): Map[Int, Map[Char, Int]] = {
      if (pos >= line.size) {
        characters
      } else {
        val c = line.charAt(pos)
        countCharacters(line, pos + 1, characters + (pos -> (characters(pos) + (c -> (characters(pos)(c) + 1)))))
      }
    }

    input.foldLeft(Map[Int, Map[Char, Int]]().withDefaultValue(Map[Char, Int]().withDefaultValue(0)))((acc, line) => countCharacters(line, 0, acc))
  }

  val result = processInput(input).toList.sortBy(entry => entry._1)

  override val part1Answer: String = result.map(entry => entry._2.toList.sortWith((l,r) => l._2  > r._2).head._1).mkString
  override val part2Answer: String = result.map(entry => entry._2.toList.sortWith((l,r) => l._2  < r._2).head._1).mkString
}
