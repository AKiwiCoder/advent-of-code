package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day04HighEntropyPassphrasesTest  extends FlatSpec with Matchers {
  "2017 Day 04 - Input" should "calculate answers" in {
    val dp = new Day04HighEntropyPassphrases("/twenty_seventeen/Day04-HighEntropyPassphrases-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day04HighEntropyPassphrases("/twenty_seventeen/Day04-HighEntropyPassphrases-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
