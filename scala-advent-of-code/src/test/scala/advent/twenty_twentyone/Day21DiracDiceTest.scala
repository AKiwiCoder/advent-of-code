package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day21DiracDiceTest extends FlatSpec with Matchers {
  "2020 Day 21 - Input" should "calculate answers" in {
    val dp = new Day21DiracDice("/twenty_twentyone/Day21-DiracDice-input.txt")

    dp.part1Answer should be(929625)
    dp.part2Answer should be(175731756652760L)
  }

  "2020 Day 21 - Example #1" should "calculate answers" in {
    val dp = new Day21DiracDice("/twenty_twentyone/Day21-DiracDice-example#1.txt")

    dp.part1Answer should be(739785)
    dp.part2Answer should be(444356092776315L)
  }
}


