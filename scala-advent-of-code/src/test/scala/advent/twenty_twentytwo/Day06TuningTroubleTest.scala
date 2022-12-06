package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day06TuningTroubleTest extends FlatSpec with Matchers {
  "2020 Day 06 - Input" should "calculate answers" in {
    val dp = new Day06TuningTrouble("/twenty_twentytwo/Day06-TuningTrouble-input.txt")

    dp.part1Answer should be(1544)
    dp.part2Answer should be(2145)
  }

  "2020 Day 06 - Example #1" should "calculate answers" in {
    val dp = new Day06TuningTrouble("/twenty_twentytwo/Day06-TuningTrouble-example#1.txt")

    dp.part1Answer should be(7)
    dp.part2Answer should be(19)
  }

  "2020 Day 06 - Example #2" should "calculate answers" in {
    val dp = new Day06TuningTrouble("/twenty_twentytwo/Day06-TuningTrouble-example#2.txt")

    dp.part1Answer should be(5)
    dp.part2Answer should be(23)
  }

  "2020 Day 06 - Example #3" should "calculate answers" in {
    val dp = new Day06TuningTrouble("/twenty_twentytwo/Day06-TuningTrouble-example#3.txt")

    dp.part1Answer should be(6)
    dp.part2Answer should be(23)
  }

  "2020 Day 06 - Example #4" should "calculate answers" in {
    val dp = new Day06TuningTrouble("/twenty_twentytwo/Day06-TuningTrouble-example#4.txt")

    dp.part1Answer should be(10)
    dp.part2Answer should be(29)
  }

  "2020 Day 06 - Example #5" should "calculate answers" in {
    val dp = new Day06TuningTrouble("/twenty_twentytwo/Day06-TuningTrouble-example#5.txt")

    dp.part1Answer should be(11)
    dp.part2Answer should be(26)
  }
}


