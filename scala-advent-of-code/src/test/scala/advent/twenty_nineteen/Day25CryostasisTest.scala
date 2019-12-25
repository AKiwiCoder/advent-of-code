package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day25CryostasisTest extends FlatSpec with Matchers {
  "2019 Day 25 - Input" should "calculate answers" in {
    val dp = new Day25Cryostasis("/twenty_nineteen/Day25-Cryostasis-input.txt")

    dp.part1Answer should be(805307408)
    dp.part2Answer should be(0)
  }
}


