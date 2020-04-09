package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day10BalanceBotsTest  extends FlatSpec with Matchers {
  "2016 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10BalanceBots("/twenty_sixteen/Day10-BalanceBots-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day10BalanceBots("/twenty_sixteen/Day10-BalanceBots-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
