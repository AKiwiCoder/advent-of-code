package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day23CoprocessorConflagrationTest  extends FlatSpec with Matchers {
  "2017 Day 23 - Input" should "calculate answers" in {
    val dp = new Day23CoprocessorConflagration("/twenty_seventeen/Day23-CoprocessorConflagration-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day23CoprocessorConflagration("/twenty_seventeen/Day23-CoprocessorConflagration-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
