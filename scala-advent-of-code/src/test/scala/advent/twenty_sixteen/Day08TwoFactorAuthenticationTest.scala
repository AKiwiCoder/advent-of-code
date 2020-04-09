package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day08TwoFactorAuthenticationTest  extends FlatSpec with Matchers {
  "2016 Day 08 - Input" should "calculate answers" in {
    val dp = new Day08TwoFactorAuthentication("/twenty_sixteen/Day08-TwoFactorAuthentication-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day08TwoFactorAuthentication("/twenty_sixteen/Day08-TwoFactorAuthentication-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
