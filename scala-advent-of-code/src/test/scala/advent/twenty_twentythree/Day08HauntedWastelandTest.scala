package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day08HauntedWastelandTest extends FlatSpec with Matchers {
  "2023 Day 08 - Input" should "calculate answers" in {
    val dp = new Day08HauntedWasteland("/twenty_twentythree/Day08-HauntedWasteland-input.txt", true, true)

    dp.part1Answer should be(19951)
    dp.part2Answer should be(16342438708751)
  }

  "2023 Day 08 - Example #1" should "calculate answers" in {
    val dp = new Day08HauntedWasteland("/twenty_twentythree/Day08-HauntedWasteland-example#1.txt", true, false)

    dp.part1Answer should be(2)
    dp.part2Answer should be(0)
  }

  "2023 Day 08 - Example #2" should "calculate answers" in {
    val dp = new Day08HauntedWasteland("/twenty_twentythree/Day08-HauntedWasteland-example#2.txt", true, false)

    dp.part1Answer should be(6)
    dp.part2Answer should be(0)
  }

  "2023 Day 08 - Example #3" should "calculate answers" in {
    val dp = new Day08HauntedWasteland("/twenty_twentythree/Day08-HauntedWasteland-example#3.txt", false, true)

    dp.part1Answer should be(0)
    dp.part2Answer should be(6)
  }
}


