package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day05HowAboutANiceGameOfChessTest  extends FlatSpec with Matchers {
  "2016 Day 05 - Input" should "calculate answers" in {
    val dp = new Day05HowAboutANiceGameOfChess("/twenty_sixteen/Day05-HowAboutANiceGameOfChess-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day05HowAboutANiceGameOfChess("/twenty_sixteen/Day05-HowAboutANiceGameOfChess-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
