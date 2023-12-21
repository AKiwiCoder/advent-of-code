package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day21StepCounterTest extends FlatSpec with Matchers {
  "2023 Day 21 - Input" should "calculate answers" in {
    val dp = new Day21StepCounter("/twenty_twentythree/Day21-StepCounter-input.txt", 64, 26501365)

    dp.part1Answer should be(3746)
    dp.part2Answer should be(623540829615589L)
  }

  "2023 Day 21 - Example #1" should "calculate answers" in {
    val dp = new Day21StepCounter("/twenty_twentythree/Day21-StepCounter-example#1.txt", 6, 10)

    dp.part1Answer should be(16)
    dp.part2Answer should be(50)
  }

  "2023 Day 21 - Example #2" should "calculate answers" in {
    val dp = new Day21StepCounter("/twenty_twentythree/Day21-StepCounter-example#1.txt", 50, 100)

    dp.part1Answer should be(1594)
    dp.part2Answer should be(6536)
  }
}


