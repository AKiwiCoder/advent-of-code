package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day11CorporatePolicyTest extends FlatSpec with Matchers {
  "2015 Day 11 - Input" should "calculate answers" in {
    val dp = new Day11CorporatePolicy("cqjxjnds")

    dp.part1Answer should be("cqjxxyzz")
    dp.part2Answer should be("cqkaabcc")
  }

  "2015 Day 11 - Example #1" should "calculate answers" in {
    val dp = new Day11CorporatePolicy("abcdefgh")

    dp.part1Answer should be("abcdffaa")
    dp.part2Answer should be("abcdffbb")
  }

  "2015 Day 11 - Example #2" should "calculate answers" in {
    val dp = new Day11CorporatePolicy("ghijklmn")

    dp.part1Answer should be("ghjaabcc")
    dp.part2Answer should be("ghjbbcdd")
  }

  "2015 Day 11 - Valid Password" should "calculate answers" in {
    Day11CorporatePolicy.validatePassword("hijklmmn") should be(false)
    Day11CorporatePolicy.validatePassword("abbceffg") should be(false)
    Day11CorporatePolicy.validatePassword("abbcegjk") should be(false)

    Day11CorporatePolicy.validatePassword("abcaaaa") should be(true)
    Day11CorporatePolicy.validatePassword("abcaabb") should be(true)
  }

  "2015 Day 11 - Increment Password" should "calculate answers" in {
    Day11CorporatePolicy.incrementPassword("aaa") should be("aab")
    Day11CorporatePolicy.incrementPassword("baz") should be("bba")
  }
}