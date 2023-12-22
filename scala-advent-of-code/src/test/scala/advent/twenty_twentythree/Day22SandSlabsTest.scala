package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day22SandSlabsTest extends FlatSpec with Matchers {
  "2023 Day 22 - Input" should "calculate answers" in {
    val dp = new Day22SandSlabs("/twenty_twentythree/Day22-SandSlabs-input.txt")

    dp.part1Answer should be(421)
    dp.part2Answer should be(39247)
  }

  "2023 Day 22 - Example #1" should "calculate answers" in {
    val dp = new Day22SandSlabs("/twenty_twentythree/Day22-SandSlabs-example#1.txt")

    dp.part1Answer should be(5)
    dp.part2Answer should be(7)
  }
}


