package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day19GoWithTheFlowTest  extends FlatSpec with Matchers {
  "2018 Day 19 - Input" should "calculate answers" in {
    val dp = new Day19GoWithTheFlow("/twenty_eighteen/Day19-GoWithTheFlow-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day19GoWithTheFlow("/twenty_eighteen/Day19-GoWithTheFlow-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
