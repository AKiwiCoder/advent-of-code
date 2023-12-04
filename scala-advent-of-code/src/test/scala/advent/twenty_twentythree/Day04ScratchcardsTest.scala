package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day04ScratchcardsTest extends FlatSpec with Matchers {
  "2023 Day 04 - Input" should "calculate answers" in {
    val dp = new Day04Scratchcards("/twenty_twentythree/Day04-Scratchcards-input.txt")

    dp.part1Answer should be(20667)
    dp.part2Answer should be(5833065)
  }

  "2023 Day 04 - Example #1" should "calculate answers" in {
    val dp = new Day04Scratchcards("/twenty_twentythree/Day04-Scratchcards-example#1.txt")

    dp.part1Answer should be(13)
    dp.part2Answer should be(30)
  }
}


