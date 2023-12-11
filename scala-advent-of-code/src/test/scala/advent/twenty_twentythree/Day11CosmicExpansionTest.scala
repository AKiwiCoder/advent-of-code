package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day11CosmicExpansionTest extends FlatSpec with Matchers {
  "2023 Day 11 - Input" should "calculate answers" in {
    val dp = new Day11CosmicExpansion("/twenty_twentythree/Day11-CosmicExpansion-input.txt", 1000000)

    dp.part1Answer should be(10231178L)
    dp.part2Answer should be(622120986954L)
  }

  "2023 Day 11 - Example #1" should "calculate answers" in {
    val dp = new Day11CosmicExpansion("/twenty_twentythree/Day11-CosmicExpansion-example#1.txt", 10)

    dp.part1Answer should be(374L)
    dp.part2Answer should be(1030L)
  }

  "2023 Day 11 - Example #2" should "calculate answers" in {
    val dp = new Day11CosmicExpansion("/twenty_twentythree/Day11-CosmicExpansion-example#1.txt", 100)

    dp.part1Answer should be(374L)
    dp.part2Answer should be(8410L)
  }
}


