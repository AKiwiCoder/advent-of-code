package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day02CorruptionChecksumTest  extends FlatSpec with Matchers {
  "2017 Day 02 - Input" should "calculate answers" in {
    val dp = new Day02CorruptionChecksum("/twenty_seventeen/Day02-CorruptionChecksum-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day02CorruptionChecksum("/twenty_seventeen/Day02-CorruptionChecksum-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
