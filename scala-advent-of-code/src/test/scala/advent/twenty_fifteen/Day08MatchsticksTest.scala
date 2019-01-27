package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day08MatchsticksTest extends FlatSpec with Matchers {
  "2015 Day 8 - Input" should "calculate answers" in {
    val dp = new Day08Matchsticks("/twenty_fifteen/Day08-Matchsticks-input.txt")

    dp.part1Answer should be(1350)
    dp.part2Answer should be(2085)
  }

  "2015 Day 8 - Example #1" should "calculate answers" in {
    val dp = new Day08Matchsticks("/twenty_fifteen/Day08-Matchsticks-example#1.txt")

    dp.part1Answer should be(12)
    dp.part2Answer should be(19)
  }

  "2015 Day 8 - Encode" should "calculate answers" in {
    val dp = new Day08Matchsticks("/twenty_fifteen/Day08-Matchsticks-example#1.txt")

    dp.encode("\"abc\"", "") should be("\"\\\"abc\\\"\"")
    dp.encode("\"aaa\\\"aaa\"", "") should be("\"\\\"aaa\\\\\\\"aaa\\\"\"")
    dp.encode("\"\\x27\"", "") should be("\"\\\"\\\\x27\\\"\"")
  }

  "2015 Day 8 - Decode" should "calculate answers" in {
    val dp = new Day08Matchsticks("/twenty_fifteen/Day08-Matchsticks-example#1.txt")

    dp.decode("\"abc\"", "") should be("abc")
    dp.decode("\"aaa\\\"aaa\"", "") should be("aaa\"aaa")
    dp.decode("\"\\x27\"", "") should be("'")
  }
}
