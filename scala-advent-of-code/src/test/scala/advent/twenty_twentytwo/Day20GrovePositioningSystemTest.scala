package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day20GrovePositioningSystemTest extends FlatSpec with Matchers {
  "2020 Day 20 - Input" should "calculate answers" in {
    val dp = new Day20GrovePositionSystem("/twenty_twentytwo/Day20-GrovePositioningSystem-input.txt")

    dp.part1Answer should be(3346)
    dp.part2Answer should be(4265712588168L)
  }

  "2020 Day 20 - Example #1" should "calculate answers" in {
    val dp = new Day20GrovePositionSystem("/twenty_twentytwo/Day20-GrovePositioningSystem-example#1.txt")

    dp.part1Answer should be(3)
    dp.part2Answer should be(1623178306L)
  }
}


