package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day02RockPaperScissorsTest extends FlatSpec with Matchers {
  "2020 Day 02 - Input" should "calculate answers" in {
    val dp = new Day02RockPaperScissors("/twenty_twentytwo/Day02-RockPaperScissors-input.txt")

    dp.part1Answer should be(14264)
    dp.part2Answer should be(12382)
  }

  "2020 Day 02 - Example #1" should "calculate answers" in {
    val dp = new Day02RockPaperScissors("/twenty_twentytwo/Day02-RockPaperScissors-example#1.txt")

    dp.part1Answer should be(15)
    dp.part2Answer should be(12)
  }
}


