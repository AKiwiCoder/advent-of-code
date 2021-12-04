package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day04GiantSquidTest extends FlatSpec with Matchers {
  "2020 Day 04 - Input" should "calculate answers" in {
    val dp = new Day04GiantSquid("/twenty_twentyone/Day04-GiantSquid-input.txt")

    dp.part1Answer should be(65325)
    dp.part2Answer should be(4624)
  }

  "2020 Day 04 - Example #1" should "calculate answers" in {
    val dp = new Day04GiantSquid("/twenty_twentyone/Day04-GiantSquid-example#1.txt")

    dp.part1Answer should be(4512)
    dp.part2Answer should be(1924)
  }
}


