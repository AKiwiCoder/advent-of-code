package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day03GearRatiosTest extends FlatSpec with Matchers {
  "2023 Day 03 - Input" should "calculate answers" in {
    val dp = new Day03GearRatios("/twenty_twentythree/Day03-GearRatios-input.txt")

    dp.part1Answer should be(553079)
    dp.part2Answer should be(84363105)
  }

  "2023 Day 03 - Example #1" should "calculate answers" in {
    val dp = new Day03GearRatios("/twenty_twentythree/Day03-GearRatios-example#1.txt")

    dp.part1Answer should be(4361)
    dp.part2Answer should be(467835)
  }
}


