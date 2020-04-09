package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day25ClockSignalTest  extends FlatSpec with Matchers {
  "2016 Day 25 - Input" should "calculate answers" in {
    val dp = new Day25ClockSignal("/twenty_sixteen/Day25-ClockSignal-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day25ClockSignal("/twenty_sixteen/Day25-ClockSignal-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
