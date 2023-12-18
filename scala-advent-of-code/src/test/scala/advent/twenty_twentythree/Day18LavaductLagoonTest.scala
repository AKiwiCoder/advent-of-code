package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day18LavaductLagoonTest extends FlatSpec with Matchers {
  "2023 Day 18 - Input" should "calculate answers" in {
    val dp = new Day18LavaductLagoon("/twenty_twentythree/Day18-LavaductLagoon-input.txt")

    dp.part1Answer should be(41019L)
    dp.part2Answer should be(96116995735219L)
  }

  "2023 Day 18 - Example #1" should "calculate answers" in {
    val dp = new Day18LavaductLagoon("/twenty_twentythree/Day18-LavaductLagoon-example#1.txt")

    dp.part1Answer should be(62L)
    dp.part2Answer should be(952408144115L)
  }
}


