package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day25ComboBreakerTest extends FlatSpec with Matchers {
  "2020 Day 25 - Input" should "calculate answers" in {
    val dp = new Day25ComboBreaker("/twenty_twenty/Day25-ComboBreaker-input.txt")

    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2020 Day 25 - Example #1" should "calculate answers" in {
    val dp = new Day25ComboBreaker("/twenty_twenty/Day25-ComboBreaker-example#1.txt")

    dp.part1Answer should be(14897079)
    dp.part2Answer should be(0)
  }
}


