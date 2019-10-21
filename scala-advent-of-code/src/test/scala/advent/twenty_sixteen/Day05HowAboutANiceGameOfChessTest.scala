package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day05HowAboutANiceGameOfChessTest extends FlatSpec with Matchers {
  "2016 Day 5 - Input" should "calculate answers" in {
    val dp = new Day05HowAboutANiceGameOfChess("abbhdwsy")

    dp.part1Answer should be("801b56a7")
    dp.part2Answer should be("424a0197")
  }

  "2016 Day 5 - Example #1" should "calculate answers" in {
    val dp = new Day05HowAboutANiceGameOfChess("abc")

    dp.part1Answer should be("18f47a30")
    dp.part2Answer should be("05ace8e3")
  }

}
