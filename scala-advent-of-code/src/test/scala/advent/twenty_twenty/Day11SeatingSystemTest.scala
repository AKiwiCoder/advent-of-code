package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day11SeatingSystemTest extends FlatSpec with Matchers {
  "2020 Day 11 - Input" should "calculate answers" in {
    val dp = new Day11SeatingSystem("/twenty_twenty/Day11-SeatingSystem-input.txt")

    dp.part1Answer should be(2338)
    dp.part2Answer should be(2134)
  }

  "2020 Day 11 - Example #1" should "calculate answers" in {
    val dp = new Day11SeatingSystem("/twenty_twenty/Day11-SeatingSystem-example#1.txt")

    dp.part1Answer should be(37)
    dp.part2Answer should be(26)
  }
}


