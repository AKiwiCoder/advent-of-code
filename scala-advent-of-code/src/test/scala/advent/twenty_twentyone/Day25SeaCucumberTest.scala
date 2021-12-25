package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day25SeaCucumberTest extends FlatSpec with Matchers {
  "2020 Day 25 - Input" should "calculate answers" in {
    val dp = new Day25SeaCucumber("/twenty_twentyone/Day25-SeaCucumber-input.txt")

    dp.part1Answer should be(417)
    dp.part2Answer should be(0)
  }

  "2020 Day 25 - Example #1" should "calculate answers" in {
    val dp = new Day25SeaCucumber("/twenty_twentyone/Day25-SeaCucumber-example#1.txt")

    dp.part1Answer should be(58)
    dp.part2Answer should be(0)
  }
}


