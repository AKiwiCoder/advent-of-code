package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day12RainRiskTest extends FlatSpec with Matchers {
  "2020 Day 12 - Input" should "calculate answers" in {
    val dp = new Day12RainRisk("/twenty_twenty/Day12-RainRisk-input.txt")

    dp.part1Answer should be(1687)
    dp.part2Answer should be(20873)
  }

  "2020 Day 12 - Example #1" should "calculate answers" in {
    val dp = new Day12RainRisk("/twenty_twenty/Day12-RainRisk-example#1.txt")

    dp.part1Answer should be(25)
    dp.part2Answer should be(286)
  }
}


