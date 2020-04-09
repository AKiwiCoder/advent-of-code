package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day22WizardSimulator20xxTest  extends FlatSpec with Matchers {
  "2015 Day 22 - Input" should "calculate answers" in {
    val dp = new Day22WizardSimulator20xx("/twenty_fifteen/Day22-WizardSimulator20xx-input.txt")
    dp.part1Answer should be(900)
    dp.part2Answer should be(1216)
  }
}
