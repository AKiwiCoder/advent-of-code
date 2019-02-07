package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day16AuntSueTest  extends FlatSpec with Matchers {
  "2015 Day 16 - Input" should "calculate answers" in {
    val dp = new Day16AuntSue("/twenty_fifteen/Day16-AuntSue-input.txt")

    dp.part1Answer should be(373)
    dp.part2Answer should be(260)
  }
}
