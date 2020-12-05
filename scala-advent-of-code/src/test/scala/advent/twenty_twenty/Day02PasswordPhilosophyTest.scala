package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day02PasswordPhilosophyTest extends FlatSpec with Matchers {
  "2020 Day 02 - Input" should "calculate answers" in {
    val dp = new Day02PasswordPhilosophy("/twenty_twenty/Day02-PasswordPhilosophy-input.txt")

    dp.part1Answer should be(418)
    dp.part2Answer should be(616)
  }

  "2020 Day 02 - Example #1" should "calculate answers" in {
    val dp = new Day02PasswordPhilosophy("/twenty_twenty/Day02-PasswordPhilosophy-example#1.txt")

    dp.part1Answer should be(2)
    dp.part2Answer should be(1)
  }
}


