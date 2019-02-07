package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

case class AuntSue(id: Int, attributes: Map[String, Int])

class Day16AuntSue(filename: String) extends DailyProblem[Int, Int] {

  private val tickerTape = Map("children" -> 3, "cats" -> 7, "samoyeds" -> 2, "pomeranians" -> 3, "akitas" -> 0, "vizslas" -> 0, "goldfish" -> 5, "trees" -> 3, "cars" -> 2, "perfumes" -> 1)

  private val pattern = "Sue ([0-9]+): ([A-Za-z]+): ([0-9]+), ([A-Za-z]+): ([0-9]+), ([A-Za-z]+): ([0-9]+)".r

  private def parse(line: String): AuntSue = {
    line match {
      case pattern(id, a, b, c, d, e, f) => AuntSue(id.toInt, Map(a -> b.toInt, c -> d.toInt, e -> f.toInt))
    }
  }

  private def score1(sue: Map[String, Int]): Int = {
    sue.map(entry => if (tickerTape(entry._1) == entry._2) 1 else 0).sum
  }

  private def score2(sue: Map[String, Int]): Int = {
    sue.map(entry =>
      entry._1 match {
        case "cats" | "trees" => if (tickerTape(entry._1) <= entry._2) 1 else 0
        case "pomeranians" | "goldfish" => if (tickerTape(entry._1) >= entry._2) 1 else 0
        case _ => if (tickerTape(entry._1) == entry._2) 1 else 0
      }).sum
  }

  private val sues = FileUtilities.readFile(filename, parse)

  override val part1Answer: Int = sues.map(sue => (sue.id, score1(sue.attributes))).sortWith((a, b) => a._2 > b._2).map(a => a._1).head
  override val part2Answer: Int = sues.map(sue => (sue.id, score2(sue.attributes))).sortWith((a, b) => a._2 > b._2).map(a => a._1).head
}
