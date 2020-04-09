package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day16PermutationPromenadeTest  extends FlatSpec with Matchers {
  "2017 Day 16 - Input" should "calculate answers" in {
    val dp = new Day16PermutationPromenade("/twenty_seventeen/Day16-PermutationPromenade-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day16PermutationPromenade("/twenty_seventeen/Day16-PermutationPromenade-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
