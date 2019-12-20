package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day18ManyWorldsInterpretationPartTwoTest extends FlatSpec with Matchers {
//  "2019 Day 18 - Input" should "calculate answers" in {
//    val dp = new Day18ManyWorldsInterpretationPartTwo("/twenty_nineteen/Day18-ManyWorldsInterpretation-input.txt")
//
//    dp.part1Answer should be(2946)
//    dp.part2Answer should be(0)
//  }

  "2019 Day 18 - Example #6" should "calculate answers" in {
    val dp = new Day18ManyWorldsInterpretationPartTwo("/twenty_nineteen/Day18-ManyWorldsInterpretation-example#6.txt")

    dp.part1Answer should be(8)
    dp.part2Answer should be(0)
  }
}


