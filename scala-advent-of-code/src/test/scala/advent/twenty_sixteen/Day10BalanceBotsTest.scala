package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day10BalanceBotsTest extends FlatSpec with Matchers {
  "2016 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10BalanceBots("/twenty_sixteen/Day10-BalanceBots-input.txt", (17, 61))

    dp.part1Answer should be(47)
    dp.part2Answer should be(2666)
  }

  "2016 Day 10 - Example #1" should "calculate answers" in {
    val dp = new Day10BalanceBots("/twenty_sixteen/Day10-BalanceBots-example#1.txt", (2, 5))

    dp.part1Answer should be(2)
    dp.part2Answer should be(30)
  }
}
