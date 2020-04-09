package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day15BeverageBanditsTest  extends FlatSpec with Matchers {
  "2018 Day 15 - Input" should "calculate answers" in {
    val dp = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
