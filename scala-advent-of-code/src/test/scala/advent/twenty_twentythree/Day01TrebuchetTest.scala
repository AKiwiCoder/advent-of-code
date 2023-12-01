package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day01TrebuchetTest extends FlatSpec with Matchers {
  "2023 Day 01 - Input" should "calculate answers" in {
    val dp = new Day01Trebuchet("/twenty_twentythree/Day01-Trebuchet-input.txt")

    dp.part1Answer should be(54605)
    dp.part2Answer should be(55429)
  }

  "2023 Day 01 - Example #1" should "calculate answers" in {
    val dp = new Day01Trebuchet("/twenty_twentythree/Day01-Trebuchet-example#1.txt")

    dp.part1Answer should be(142)
    dp.part2Answer should be(142)
  }

  "2023 Day 01 - Example #2" should "calculate answers" in {
    val dp = new Day01Trebuchet("/twenty_twentythree/Day01-Trebuchet-example#2.txt")

    dp.part1Answer should be(209)
    dp.part2Answer should be(281)
  }
}


