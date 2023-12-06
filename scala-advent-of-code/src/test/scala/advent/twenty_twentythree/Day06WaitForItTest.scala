package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day06WaitForItTest extends FlatSpec with Matchers {
  "2023 Day 06 - Input" should "calculate answers" in {
    val dp = new Day06WaitForIt("/twenty_twentythree/Day06-WaitForIt-input.txt")

    dp.part1Answer should be(1084752)
    dp.part2Answer should be(28228952)
  }

  "2023 Day 06 - Example #1" should "calculate answers" in {
    val dp = new Day06WaitForIt("/twenty_twentythree/Day06-WaitForIt-example#1.txt")

    dp.part1Answer should be(288)
    dp.part2Answer should be(71503)
  }
}


