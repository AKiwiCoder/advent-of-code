package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day07RecursiveCircusTest  extends FlatSpec with Matchers {
  "2017 Day 07 - Input" should "calculate answers" in {
    val dp = new Day07RecursiveCircus("/twenty_seventeen/Day07-RecursiveCircus-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day07RecursiveCircus("/twenty_seventeen/Day07-RecursiveCircus-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
