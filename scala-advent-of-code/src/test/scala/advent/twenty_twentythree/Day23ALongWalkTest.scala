package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day23ALongWalkTest extends FlatSpec with Matchers {
  "2023 Day 23 - Input" should "calculate answers" in {
    val dp = new Day23ALongWalk("/twenty_twentythree/Day23-ALongWalk-input.txt")

    dp.part1Answer should be(2222)
    dp.part2Answer should be(6590)
  }

  "2023 Day 23 - Example #1" should "calculate answers" in {
    val dp = new Day23ALongWalk("/twenty_twentythree/Day23-ALongWalk-example#1.txt")

    dp.part1Answer should be(94)
    dp.part2Answer should be(154)
  }
}


