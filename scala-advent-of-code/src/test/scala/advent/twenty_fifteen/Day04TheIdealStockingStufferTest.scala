package advent.twenty_fifteen

import org.scalatest.{FlatSpec, FunSuite, Matchers}

class Day04TheIdealStockingStufferTest extends FlatSpec with Matchers {
  "2015 Day 3 - Input" should "calculate answers" in {
    val dp = new Day04TheIdealStockingStuffer("iwrupvqb")
    dp.part1Answer should be(346386)
    dp.part2Answer should be(9958218)
  }

  "2015 Day 3 - Example #1" should "calculate answers" in {
    val dp = new Day04TheIdealStockingStuffer("abcdef")
    dp.part1Answer should be(609043)
    dp.part2Answer should be(6742839)
  }

  "2015 Day 3 - Example #2" should "calculate answers" in {
    val dp = new Day04TheIdealStockingStuffer("pqrstuv")
    dp.part1Answer should be(1048970)
    dp.part2Answer should be(5714438)
  }
}