package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day24ItHangsInTheBalanceTest  extends FlatSpec with Matchers {
  "2015 Day 24 - Input" should "calculate answers" in {
    val dp = new Day24ItHangsInTheBalance("/twenty_fifteen/Day24-ItHangsInTheBalance-input.txt")
    dp.part1Answer should be(10439961859L)
    dp.part2Answer should be(72050269L)
  }

  "2015 Day 24 - Example #1" should "calculate answers" in {
    val dp = new Day24ItHangsInTheBalance("/twenty_fifteen/Day24-ItHangsInTheBalance-example#1.txt")
    dp.part1Answer should be(88)
    dp.part2Answer should be(33)
  }
}
