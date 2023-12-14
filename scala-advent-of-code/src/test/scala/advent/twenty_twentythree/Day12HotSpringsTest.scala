package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day12HotSpringsTest extends FlatSpec with Matchers {
  "2023 Day 12 - Input" should "calculate answers" in {
    val dp = new Day12HotSprings("/twenty_twentythree/Day12-HotSprings-input.txt")

    dp.part1Answer should be(7718)
    dp.part2Answer should be(128741994134728L)
  }

  "2023 Day 12 - Example #1" should "calculate answers" in {
    val dp = new Day12HotSprings("/twenty_twentythree/Day12-HotSprings-example#1.txt")

    dp.part1Answer should be(21)
    dp.part2Answer should be(525152)
  }
}


