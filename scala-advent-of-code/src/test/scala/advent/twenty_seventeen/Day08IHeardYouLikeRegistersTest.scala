package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day08IHeardYouLikeRegistersTest  extends FlatSpec with Matchers {
  "2017 Day 08 - Input" should "calculate answers" in {
    val dp = new Day08IHeardYouLikeRegisters("/twenty_seventeen/Day08-IHeardYouLikeRegisters-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day08IHeardYouLikeRegisters("/twenty_seventeen/Day08-IHeardYouLikeRegisters-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
