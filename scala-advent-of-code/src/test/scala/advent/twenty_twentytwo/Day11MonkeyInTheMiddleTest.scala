package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day11MonkeyInTheMiddleTest extends FlatSpec with Matchers {
  "2020 Day 11 - Input" should "calculate answers" in {
    val dp = new Day11MonkeyInTheMiddle("/twenty_twentytwo/Day11-MonkeyInTheMiddle-input.txt")

    dp.part1Answer should be(119715)
    dp.part2Answer should be(18085004878L)
  }

  "2020 Day 11 - Example #1" should "calculate answers" in {
    val dp = new Day11MonkeyInTheMiddle("/twenty_twentytwo/Day11-MonkeyInTheMiddle-example#1.txt")

    dp.part1Answer should be(10605)
    dp.part2Answer should be(2713310158L)
  }
}


