package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day06LanternfishTest extends FlatSpec with Matchers {
  "2020 Day 06 - Input" should "calculate answers" in {
    val dp = new Day06Lanternfish("/twenty_twentyone/Day06-Lanternfish-input.txt")

    dp.part1Answer should be(388419)
    dp.part2Answer should be(1740449478328L)
  }

  "2020 Day 06 - Example #1" should "calculate answers" in {
    val dp = new Day06Lanternfish("/twenty_twentyone/Day06-Lanternfish-example#1.txt")

    dp.part1Answer should be(5934)
    dp.part2Answer should be(26984457539L)
  }
}


