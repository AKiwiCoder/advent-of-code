package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day14OneTimePadTest  extends FlatSpec with Matchers {
  "2016 Day 14 - Input" should "calculate answers" in {
    val dp = new Day14OneTimePad("/twenty_sixteen/Day14-OneTimePad-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day14OneTimePad("/twenty_sixteen/Day14-OneTimePad-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
