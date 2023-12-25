package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day24NeverTellMeTheOddsTest extends FlatSpec with Matchers {
  "2023 Day 24 - Input" should "calculate answers" in {
    val dp = new Day24NeverTellMeTheOdds("/twenty_twentythree/Day24-NeverTellMeTheOdds-input.txt", 200000000000000L, 400000000000000L, 200000000000000L, 400000000000000L)

    dp.part1Answer should be(13149)
    dp.part2Answer should be(0)
  }

  "2023 Day 24 - Example #1" should "calculate answers" in {
    val dp = new Day24NeverTellMeTheOdds("/twenty_twentythree/Day24-NeverTellMeTheOdds-example#1.txt",7,27,7,27)

    dp.part1Answer should be(2)
    dp.part2Answer should be(0)
  }
}


