package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day22SporificaVirusTest  extends FlatSpec with Matchers {
  "2017 Day 22 - Input" should "calculate answers" in {
    val dp = new Day22SporificaVirus("/twenty_seventeen/Day22-SporificaVirus-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day22SporificaVirus("/twenty_seventeen/Day22-SporificaVirus-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
