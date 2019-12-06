package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day06UniversalOrbitMapTest extends FlatSpec with Matchers {
  "2019 Day 06 - Input" should "calculate answers" in {
    val dp = new Day06UniversalOrbitMap("/twenty_nineteen/Day06-UniversalOrbitMap-input.txt")

    dp.part1Answer should be(160040)
    dp.part2Answer should be(373)
  }

  "2019 Day 06 - Example #1" should "calculate answers" in {
    val dp = new Day06UniversalOrbitMap("/twenty_nineteen/Day06-UniversalOrbitMap-example#1.txt")

    dp.part1Answer should be(54)
    dp.part2Answer should be(4)
  }
}


