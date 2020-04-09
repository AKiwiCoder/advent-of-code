package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day24AirDuctSpelunkingTest  extends FlatSpec with Matchers {
  "2016 Day 24 - Input" should "calculate answers" in {
    val dp = new Day24AirDuctSpelunking("/twenty_sixteen/Day24-AirDuctSpelunking-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day24AirDuctSpelunking("/twenty_sixteen/Day24-AirDuctSpelunking-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
