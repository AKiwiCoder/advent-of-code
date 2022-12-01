package advent.twenty_twentytwo 

import org.scalatest.{FlatSpec, Matchers}

class Day01CalorieCountingTest extends FlatSpec with Matchers {
  "2020 Day 01 - Input" should "calculate answers" in {
    val dp = new Day01CalorieCounting("/twenty_twentytwo/Day01-CalorieCounting-input.txt")

    dp.part1Answer should be(70720)
    dp.part2Answer should be(207148)
  }

  "2020 Day 01 - Example #1" should "calculate answers" in {
    val dp = new Day01CalorieCounting("/twenty_twentytwo/Day01-CalorieCounting-example#1.txt")

    dp.part1Answer should be(24000)
    dp.part2Answer should be(41000)
  }
}


