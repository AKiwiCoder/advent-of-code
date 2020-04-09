package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day24ElectromagneticMoatTest  extends FlatSpec with Matchers {
  "2017 Day 24 - Input" should "calculate answers" in {
    val dp = new Day24ElectromagneticMoat("/twenty_seventeen/Day24-ElectromagneticMoat-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day24ElectromagneticMoat("/twenty_seventeen/Day24-ElectromagneticMoat-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
