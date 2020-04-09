package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day11RadioisotopeThermoelectricGeneratorsTest  extends FlatSpec with Matchers {
  "2016 Day 11 - Input" should "calculate answers" in {
    val dp = new Day11RadioisotopeThermoelectricGenerators("/twenty_sixteen/Day11-RadioisotopeThermoelectricGenerators-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day11RadioisotopeThermoelectricGenerators("/twenty_sixteen/Day11-RadioisotopeThermoelectricGenerators-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
