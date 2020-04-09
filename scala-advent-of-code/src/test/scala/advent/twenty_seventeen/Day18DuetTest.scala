package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day18DuetTest  extends FlatSpec with Matchers {
  "2017 Day 18 - Input" should "calculate answers" in {
    val dp = new Day18Duet("/twenty_seventeen/Day18-Duet-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day18Duet("/twenty_seventeen/Day18-Duet-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
