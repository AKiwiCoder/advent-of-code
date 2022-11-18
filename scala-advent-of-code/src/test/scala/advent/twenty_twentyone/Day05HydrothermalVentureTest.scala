package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day05HydrothermalVentureTest extends FlatSpec with Matchers {
  "2020 Day 05 - Input" should "calculate answers" in {
    val dp = new Day05HydrothermalVenture("/twenty_twentyone/Day05-HydrothermalVenture-input.txt")

    dp.part1Answer should be(8060)
    dp.part2Answer should be(21577)
  }

  "2020 Day 05 - Example #1" should "calculate answers" in {
    val dp = new Day05HydrothermalVenture("/twenty_twentyone/Day05-HydrothermalVenture-example#1.txt")

    dp.part1Answer should be(5)
    dp.part2Answer should be(12)
  }
}


