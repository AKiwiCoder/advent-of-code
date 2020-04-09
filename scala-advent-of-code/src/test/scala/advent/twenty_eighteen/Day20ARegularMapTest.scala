package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day20ARegularMapTest  extends FlatSpec with Matchers {
  "2018 Day 20 - Input" should "calculate answers" in {
    val dp = new Day20ARegularMap("/twenty_eighteen/Day20-ARegularMap-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day20ARegularMap("/twenty_eighteen/Day20-ARegularMap-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
