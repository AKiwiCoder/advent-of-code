package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day08HandheldHaltingTest extends FlatSpec with Matchers {
  "2020 Day 08 - Input" should "calculate answers" in {
    val dp = new Day08HandheldHalting("/twenty_twenty/Day08-HandheldHalting-input.txt")

    dp.part1Answer should be(2058)
    dp.part2Answer should be(1000)
  }

  "2020 Day 08 - Example #1" should "calculate answers" in {
    val dp = new Day08HandheldHalting("/twenty_twenty/Day08-HandheldHalting-example#1.txt")

    dp.part1Answer should be(5)
    dp.part2Answer should be(8)
  }
}


