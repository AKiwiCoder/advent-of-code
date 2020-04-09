package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day13MineCartMadnessTest  extends FlatSpec with Matchers {
  "2018 Day 13 - Input" should "calculate answers" in {
    val dp = new Day13MineCartMadness("/twenty_eighteen/Day13-MineCartMadness-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day13MineCartMadness("/twenty_eighteen/Day13-MineCartMadness-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
