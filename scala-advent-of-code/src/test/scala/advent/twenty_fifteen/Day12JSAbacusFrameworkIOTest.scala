package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day12JSAbacusFrameworkIOTest extends FlatSpec with Matchers {
  "2015 Day 12 - Input" should "calculate answers" in {
    val dp = new Day12JSAbacusFrameworkIO("/twenty_fifteen/Day12-JSAbacusFrameworkIO-input.txt")

    dp.part1Answer should be(111754)
    dp.part2Answer should be(65402)
  }

  "2015 Day 12 - Example #1" should "calculate answers" in {
    val dp = new Day12JSAbacusFrameworkIO("/twenty_fifteen/Day12-JSAbacusFrameworkIO-example#1.txt")

    dp.part1Answer should be(18)
    dp.part2Answer should be(18)
  }

  "2015 Day 12 - Example #2" should "calculate answers" in {
    val dp = new Day12JSAbacusFrameworkIO("/twenty_fifteen/Day12-JSAbacusFrameworkIO-example#2.txt")

    dp.part1Answer should be(33)
    dp.part2Answer should be(16)
  }
}
