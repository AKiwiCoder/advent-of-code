package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day19AplentyTest extends FlatSpec with Matchers {
  "2023 Day 19 - Input" should "calculate answers" in {
    val dp = new Day19Aplenty("/twenty_twentythree/Day19-Aplenty-input.txt")

    dp.part1Answer should be(373302)
    dp.part2Answer should be(130262715574114L)
  }

  "2023 Day 19 - Example #1" should "calculate answers" in {
    val dp = new Day19Aplenty("/twenty_twentythree/Day19-Aplenty-example#1.txt")

    dp.part1Answer should be(19114)
    dp.part2Answer should be(167409079868000L)
  }
}


