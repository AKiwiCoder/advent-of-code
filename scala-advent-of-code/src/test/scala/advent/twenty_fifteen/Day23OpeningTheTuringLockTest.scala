package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day23OpeningTheTuringLockTest  extends FlatSpec with Matchers {
  "2015 Day 23 - Input" should "calculate answers" in {
    val dp = new Day23OpeningTheTuringLock("/twenty_fifteen/Day23-OpeningTheTuringLock-input.txt")
    dp.part1Answer("b") should be(184)
    dp.part2Answer("b") should be(231)
  }

  "2015 Day 23 - Example #1" should "calculate answers" in {
    val dp = new Day23OpeningTheTuringLock("/twenty_fifteen/Day23-OpeningTheTuringLock-example#1.txt")
    dp.part1Answer("a") should be(2)
    dp.part2Answer("a") should be(7)
  }
}
