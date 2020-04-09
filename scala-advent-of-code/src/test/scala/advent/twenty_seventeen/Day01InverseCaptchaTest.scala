package advent.twenty_seventeen

import org.scalatest.{FlatSpec, Matchers}

class Day01InverseCaptchaTest  extends FlatSpec with Matchers {
  "2017 Day 01 - Input" should "calculate answers" in {
    val dp = new Day01InverseCaptcha("/twenty_seventeen/Day01-InverseCaptcha-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day01InverseCaptcha("/twenty_seventeen/Day01-InverseCaptcha-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
