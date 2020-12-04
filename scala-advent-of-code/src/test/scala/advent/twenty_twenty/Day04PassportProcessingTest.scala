package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day04PassportProcessingTest extends FlatSpec with Matchers {
  "2020 Day 04 - Input" should "calculate answers" in {
    val dp = new Day04PassportProcessing("/twenty_twenty/Day04-PassportProcessing-input.txt")

    dp.part1Answer should be(256)
    dp.part2Answer should be(0)
  }

  "2020 Day 04 - Example #1" should "calculate answers" in {
    val dp = new Day04PassportProcessing("/twenty_twenty/Day04-PassportProcessing-example#1.txt")

    dp.part1Answer should be(2)
    dp.part2Answer should be(2)
  }


  "2020 Day 04 - Example #2" should "calculate answers" in {
    val dp = new Day04PassportProcessing("/twenty_twenty/Day04-PassportProcessing-example#2.txt")

    dp.part1Answer should be(4)
    dp.part2Answer should be(0)
  }


  "2020 Day 04 - Example #3" should "calculate answers" in {
    val dp = new Day04PassportProcessing("/twenty_twenty/Day04-PassportProcessing-example#3.txt")

    dp.part1Answer should be(4)
    dp.part2Answer should be(4)
  }
}


