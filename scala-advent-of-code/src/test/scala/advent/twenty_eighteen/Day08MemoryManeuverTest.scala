package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day08MemoryManeuverTest  extends FlatSpec with Matchers {
  "2018 Day 08 - Input" should "calculate answers" in {
    val dp = new Day08MemoryManeuver("/twenty_eighteen/Day08-MemoryManeuver-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day08MemoryManeuver("/twenty_eighteen/Day08-MemoryManeuver-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
