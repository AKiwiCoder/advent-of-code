package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day14ExtendedPolymerizationTest extends FlatSpec with Matchers {
  "2020 Day 14 - Input" should "calculate answers" in {
    val dp = new Day14ExtendedPolymerization("/twenty_twentyone/Day14-ExtendedPolymerization-input.txt")

    dp.part1Answer should be(3411)
    dp.part2Answer should be(7477815755570L)
  }

  "2020 Day 14 - Example #1" should "calculate answers" in {
    val dp = new Day14ExtendedPolymerization("/twenty_twentyone/Day14-ExtendedPolymerization-example#1.txt")

    dp.part1Answer should be(1588)
    dp.part2Answer should be(2188189693529L)
  }
}


