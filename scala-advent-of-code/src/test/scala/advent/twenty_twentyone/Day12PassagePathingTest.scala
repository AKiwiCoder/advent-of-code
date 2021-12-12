package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day12PassagePathingTest extends FlatSpec with Matchers {
  "2020 Day 12 - Input" should "calculate answers" in {
    val dp = new Day12PassagePathing("/twenty_twentyone/Day12-PassagePathing-input.txt")

    dp.part1Answer should be(4413)
    dp.part2Answer should be(118803)
  }

  "2020 Day 12 - Example #1" should "calculate answers" in {
    val dp = new Day12PassagePathing("/twenty_twentyone/Day12-PassagePathing-example#1.txt")

    dp.part1Answer should be(10)
    dp.part2Answer should be(36)
  }

  "2020 Day 12 - Example #2" should "calculate answers" in {
    val dp = new Day12PassagePathing("/twenty_twentyone/Day12-PassagePathing-example#2.txt")

    dp.part1Answer should be(19)
    dp.part2Answer should be(103)
  }

  "2020 Day 12 - Example #3" should "calculate answers" in {
    val dp = new Day12PassagePathing("/twenty_twentyone/Day12-PassagePathing-example#3.txt")

    dp.part1Answer should be(226)
    dp.part2Answer should be(3509)
  }
}


