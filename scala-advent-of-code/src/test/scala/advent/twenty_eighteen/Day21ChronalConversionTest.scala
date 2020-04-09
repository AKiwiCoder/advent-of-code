package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day21ChronalConversionTest  extends FlatSpec with Matchers {
  "2018 Day 21 - Input" should "calculate answers" in {
    val dp = new Day21ChronalConversion("/twenty_eighteen/Day21-ChronalConversion-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day21ChronalConversion("/twenty_eighteen/Day21-ChronalConversion-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
