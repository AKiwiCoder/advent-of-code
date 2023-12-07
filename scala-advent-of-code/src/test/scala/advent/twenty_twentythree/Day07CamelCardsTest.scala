package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day07CamelCardsTest extends FlatSpec with Matchers {
  "2023 Day 07 - Input" should "calculate answers" in {
    val dp = new Day07CamelCards("/twenty_twentythree/Day07-CamelCards-input.txt")

    dp.part1Answer should be(250120186)
    dp.part2Answer should be(250665248)
  }

  "2023 Day 07 - Example #1" should "calculate answers" in {
    val dp = new Day07CamelCards("/twenty_twentythree/Day07-CamelCards-example#1.txt")

    dp.part1Answer should be(6440)
    dp.part2Answer should be(5905)
  }
}


