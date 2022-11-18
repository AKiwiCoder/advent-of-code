package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day15ChitonTest extends FlatSpec with Matchers {
  "2020 Day 15 - Input" should "calculate answers" in {
    val dp = new Day15Chiton("/twenty_twentyone/Day15-Chiton-input.txt")

    dp.part1Answer should be(429)
    dp.part2Answer should be(2844)
  }

  "2020 Day 15 - Example #1" should "calculate answers" in {
    val dp = new Day15Chiton("/twenty_twentyone/Day15-Chiton-example#1.txt")

    dp.part1Answer should be(40)
    dp.part2Answer should be(315)
  }
}


