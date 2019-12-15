package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day15OxygenSystemTest extends FlatSpec with Matchers {
  "2019 Day 15 - Input" should "calculate answers" in {
    val dp = new Day15OxygenSystem("/twenty_nineteen/Day15-OxygenSystem-input.txt")

    dp.part1Answer should be(424)
    dp.part2Answer should be(446)
  }
}


