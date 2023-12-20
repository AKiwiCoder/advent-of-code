package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day20PulsePropagationTest extends FlatSpec with Matchers {
  "2023 Day 20 - Input" should "calculate answers" in {
    val dp = new Day20PulsePropagation("/twenty_twentythree/Day20-PulsePropagation-input.txt", "rx", Set("tt", "qz", "cq", "jx"))

    dp.part1Answer should be(925955316)
    dp.part2Answer should be(241528477694627L)
  }

  "2023 Day 20 - Example #1" should "calculate answers" in {
    val dp = new Day20PulsePropagation("/twenty_twentythree/Day20-PulsePropagation-example#1.txt", "output", Set())

    dp.part1Answer should be(32000000)
    dp.part2Answer should be(0)
  }

  "2023 Day 20 - Example #2" should "calculate answers" in {
    val dp = new Day20PulsePropagation("/twenty_twentythree/Day20-PulsePropagation-example#2.txt", "output", Set())

    dp.part1Answer should be(11687500)
    dp.part2Answer should be(0)
  }
}


