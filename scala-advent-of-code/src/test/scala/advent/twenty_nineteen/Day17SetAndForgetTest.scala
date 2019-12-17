package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day17SetAndForgetTest extends FlatSpec with Matchers {
  "2019 Day 17 - Input" should "calculate answers" in {
    val dp = new Day17SetAndForget("/twenty_nineteen/Day17-SetAndForget-input.txt")

    dp.part1Answer should be(4112)
    dp.part2Answer should be(578918)
  }
}


