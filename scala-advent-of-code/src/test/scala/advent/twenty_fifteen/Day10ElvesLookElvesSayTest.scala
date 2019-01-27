package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day10ElvesLookElvesSayTest extends FlatSpec with Matchers {
  "2015 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10ElvesLookElvesSay("1113222113")

    dp.part1Answer should be(252594)
    dp.part2Answer should be(3579328)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day10ElvesLookElvesSay("1")

    dp.part1Answer should be(82350)
    dp.part2Answer should be(1166642)
  }
}
