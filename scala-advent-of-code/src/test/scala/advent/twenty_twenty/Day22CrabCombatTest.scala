package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day22CrabCombatTest extends FlatSpec with Matchers {
  "2020 Day 22 - Input" should "calculate answers" in {
    val dp = new Day22CrabCombat("/twenty_twenty/Day22-CrabCombat-input.txt")

    dp.part1Answer should be(30138)
    dp.part2Answer should be(31587)
  }

  "2020 Day 22 - Example #1" should "calculate answers" in {
    val dp = new Day22CrabCombat("/twenty_twenty/Day22-CrabCombat-example#1.txt")

    dp.part1Answer should be(306)
    dp.part2Answer should be(291)
  }

  "2020 Day 22 - Example #2" should "calculate answers" in {
    val dp = new Day22CrabCombat("/twenty_twenty/Day22-CrabCombat-example#2.txt", false)

    dp.part1Answer should be(-1)
    dp.part2Answer should be(105)
  }
}


