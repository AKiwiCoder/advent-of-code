package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day23SafeCrackingTest  extends FlatSpec with Matchers {
  "2016 Day 23 - Input" should "calculate answers" in {
    val dp = new Day23SafeCracking("/twenty_sixteen/Day23-SafeCracking-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day23SafeCracking("/twenty_sixteen/Day23-SafeCracking-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
