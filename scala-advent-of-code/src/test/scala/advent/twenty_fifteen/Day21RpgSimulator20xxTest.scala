package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day21RpgSimulator20xxTest  extends FlatSpec with Matchers {
  "2015 Day 21 - Input" should "calculate answers" in {
    val dp = new Day21RpgSimulator20xx("/twenty_fifteen/Day21-RpgSimulator20xx-input.txt")
    dp.part1Answer should be(91)
    dp.part2Answer should be(158)
  }
}
