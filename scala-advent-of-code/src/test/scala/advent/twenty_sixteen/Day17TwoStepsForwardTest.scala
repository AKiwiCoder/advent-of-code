package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day17TwoStepsForwardTest  extends FlatSpec with Matchers {
  "2016 Day 17 - Input" should "calculate answers" in {
    val dp = new Day17TwoStepsForward("/twenty_sixteen/Day17-TwoStepsForward-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day17TwoStepsForward("/twenty_sixteen/Day17-TwoStepsForward-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
