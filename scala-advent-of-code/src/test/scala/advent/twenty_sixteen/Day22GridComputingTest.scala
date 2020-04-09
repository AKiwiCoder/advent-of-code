package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day22GridComputingTest  extends FlatSpec with Matchers {
  "2016 Day 22 - Input" should "calculate answers" in {
    val dp = new Day22GridComputing("/twenty_sixteen/Day22-GridComputing-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day22GridComputing("/twenty_sixteen/Day22-GridComputing-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
