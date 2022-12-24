package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day24BlizzardBasinTest extends FlatSpec with Matchers {
  "2020 Day 24 - Input" should "calculate answers" in {
    val dp = new Day24BlizzardBasin("/twenty_twentytwo/Day24-BlizzardBasin-input.txt")

    dp.part1Answer should be(288)
    dp.part2Answer should be(861)
  }

  "2020 Day 24 - Example #1" should "calculate answers" in {
    val dp = new Day24BlizzardBasin("/twenty_twentytwo/Day24-BlizzardBasin-example#1.txt")

    dp.part1Answer should be(18)
    dp.part2Answer should be(54)
  }
}


