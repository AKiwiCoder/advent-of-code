package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day09StreamProcessingTest  extends FlatSpec with Matchers {
  "2017 Day 09 - Input" should "calculate answers" in {
    val dp = new Day09StreamProcessing("/twenty_seventeen/Day09-StreamProcessing-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day09StreamProcessing("/twenty_seventeen/Day09-StreamProcessing-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
