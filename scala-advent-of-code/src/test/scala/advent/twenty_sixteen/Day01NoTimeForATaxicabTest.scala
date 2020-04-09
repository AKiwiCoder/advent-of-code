package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day01NoTimeForATaxicabTest  extends FlatSpec with Matchers {
  "2016 Day 01 - Input" should "calculate answers" in {
    val dp = new Day01NoTimeForATaxicab("/twenty_sixteen/Day01-NoTimeForATaxicab-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day01NoTimeForATaxicab("/twenty_sixteen/Day01-NoTimeForATaxicab-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
