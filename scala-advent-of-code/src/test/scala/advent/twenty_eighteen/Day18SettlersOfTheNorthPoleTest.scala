package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day18SettlersOfTheNorthPoleTest  extends FlatSpec with Matchers {
  "2018 Day 18 - Input" should "calculate answers" in {
    val dp = new Day18SettlersOfTheNorthPole("/twenty_eighteen/Day18-SettlersOfTheNorthPole-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day18SettlersOfTheNorthPole("/twenty_eighteen/Day18-SettlersOfTheNorthPole-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
