package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day21MonkeyMathTest extends FlatSpec with Matchers {
  "2020 Day 21 - Input" should "calculate answers" in {
    val dp = new Day21MonkeyMath("/twenty_twentytwo/Day21-MonkeyMath-input.txt", 3373767893060L)

    dp.part1Answer should be(157714751182692L)
    dp.part2Answer should be(3373767893067L)
  }

  "2020 Day 21 - Example #1" should "calculate answers" in {
    val dp = new Day21MonkeyMath("/twenty_twentytwo/Day21-MonkeyMath-example#1.txt", 0L)

    dp.part1Answer should be(152)
    dp.part2Answer should be(301)
  }
}


