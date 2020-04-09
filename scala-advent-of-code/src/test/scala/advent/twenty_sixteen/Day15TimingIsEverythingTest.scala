package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day15TimingIsEverythingTest  extends FlatSpec with Matchers {
  "2016 Day 15 - Input" should "calculate answers" in {
    val dp = new Day15TimingIsEverything("/twenty_sixteen/Day15-TimingIsEverything-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day15TimingIsEverything("/twenty_sixteen/Day15-TimingIsEverything-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
