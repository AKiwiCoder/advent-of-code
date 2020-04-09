package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day05AlchemicalReductionTest  extends FlatSpec with Matchers {
  "2018 Day 05 - Input" should "calculate answers" in {
    val dp = new Day05AlchemicalReduction("/twenty_eighteen/Day05-AlchemicalReduction-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day05AlchemicalReduction("/twenty_eighteen/Day05-AlchemicalReduction-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
