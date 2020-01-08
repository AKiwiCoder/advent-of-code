package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day19TractorBeamTest extends FlatSpec with Matchers {
  "2019 Day 19 - Input" should "calculate answers" in {
    val dp = new Day19TractorBeam("/twenty_nineteen/Day19-TractorBeam-input.txt")

    dp.part1Answer should be(176)
    dp.part2Answer should be(6751081)
  }
}


