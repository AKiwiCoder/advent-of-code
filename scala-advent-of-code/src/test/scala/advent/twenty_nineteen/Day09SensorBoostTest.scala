package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day09SensorBoostTest extends FlatSpec with Matchers {
  "2019 Day 09 - Input" should "calculate answers" in {
    val dp = new Day09SensorBoost("/twenty_nineteen/Day09-SensorBoost-input.txt")

    dp.part1Answer should be(2932210790L)
    dp.part2Answer should be(73144)
  }

  "2019 Day 09 - Example #1" should "calculate answers" in {
    val dp = new Day09SensorBoost("/twenty_nineteen/Day09-SensorBoost-example#1.txt")

    dp.part1Answer should be(109)
    dp.part2Answer should be(109)
  }

  "2019 Day 09 - Example #2" should "calculate answers" in {
    val dp = new Day09SensorBoost("/twenty_nineteen/Day09-SensorBoost-example#2.txt")

    dp.part1Answer should be(1219070632396864L)
    dp.part2Answer should be(1219070632396864L)
  }

  "2019 Day 09 - Example #3" should "calculate answers" in {
    val dp = new Day09SensorBoost("/twenty_nineteen/Day09-SensorBoost-example#3.txt")

    dp.part1Answer should be(1125899906842624L)
    dp.part2Answer should be(1125899906842624L)
  }
}


