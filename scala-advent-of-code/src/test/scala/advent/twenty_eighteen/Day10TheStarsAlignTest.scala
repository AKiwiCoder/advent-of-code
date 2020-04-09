package advent.twenty_eighteen

import org.scalatest.{FlatSpec, Matchers}

class Day10TheStarsAlignTest  extends FlatSpec with Matchers {
  "2018 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10TheStarsAlign("/twenty_eighteen/Day10-TheStarsAlign-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day10TheStarsAlign("/twenty_eighteen/Day10-TheStarsAlign-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
