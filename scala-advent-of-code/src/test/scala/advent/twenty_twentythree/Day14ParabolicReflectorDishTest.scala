package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day14ParabolicReflectorDishTest extends FlatSpec with Matchers {
  "2023 Day 14 - Input" should "calculate answers" in {
    val dp = new Day14ParabolicReflectorDish("/twenty_twentythree/Day14-ParabolicReflectorDish-input.txt")

    dp.part1Answer should be(107053)
    dp.part2Answer should be(88371)
  }

  "2023 Day 14 - Example #1" should "calculate answers" in {
    val dp = new Day14ParabolicReflectorDish("/twenty_twentythree/Day14-ParabolicReflectorDish-example#1.txt")

    dp.part1Answer should be(136)
    dp.part2Answer should be(64)
  }
}


