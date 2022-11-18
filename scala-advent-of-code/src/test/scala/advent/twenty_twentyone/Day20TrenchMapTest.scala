package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day20TrenchMapTest extends FlatSpec with Matchers {
  "2020 Day 20 - Input" should "calculate answers" in {
    val dp = new Day20TrenchMap("/twenty_twentyone/Day20-TrenchMap-input.txt")

    dp.part1Answer should be(5349)
    dp.part2Answer should be(15806)
  }

  "2020 Day 20 - Example #1" should "calculate answers" in {
    val dp = new Day20TrenchMap("/twenty_twentyone/Day20-TrenchMap-example#1.txt")

    dp.part1Answer should be(35)
    dp.part2Answer should be(3351)
  }
}


