package advent.twenty_twenty

import org.scalatest.{FlatSpec, Matchers}

class Day07HandyHaversacksTest extends FlatSpec with Matchers {
  "2020 Day 07 - Input" should "calculate answers" in {
    val dp = new Day07HandyHaversacks("/twenty_twenty/Day07-HandyHaversacks-input.txt")

    dp.part1Answer should be(278)
    dp.part2Answer should be(45157)
  }

  "2020 Day 07 - Example #1" should "calculate answers" in {
    val dp = new Day07HandyHaversacks("/twenty_twenty/Day07-HandyHaversacks-example#1.txt")

    dp.part1Answer should be(4)
    dp.part2Answer should be(32)
  }

  "2020 Day 07 - Example #2" should "calculate answers" in {
    val dp = new Day07HandyHaversacks("/twenty_twenty/Day07-HandyHaversacks-example#2.txt")

    dp.part1Answer should be(0)
    dp.part2Answer should be(126)
  }
}


