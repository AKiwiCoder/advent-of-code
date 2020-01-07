package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day18ManyWorldsInterpretationTest extends FlatSpec with Matchers {
  "2019 Day 18 - Input" should "calculate answers" in {
    val dp = new Day18ManyWorldsInterpretation("/twenty_nineteen/Day18-ManyWorldsInterpretation-input.txt")

    dp.part1Answer should be(2946)
    dp.part2Answer should be(1222)
  }

  "2019 Day 18 - Example #1" should "calculate answers" in {
    val dp = new Day18ManyWorldsInterpretation("/twenty_nineteen/Day18-ManyWorldsInterpretation-example#1.txt")

    dp.part1Answer should be(8)
    dp.part2Answer should be(0)
  }

  "2019 Day 18 - Example #2" should "calculate answers" in {
    val dp = new Day18ManyWorldsInterpretation("/twenty_nineteen/Day18-ManyWorldsInterpretation-example#2.txt")

    dp.part1Answer should be(86)
    dp.part2Answer should be(0)
  }

  "2019 Day 18 - Example #3" should "calculate answers" in {
    val dp = new Day18ManyWorldsInterpretation("/twenty_nineteen/Day18-ManyWorldsInterpretation-example#3.txt")

    dp.part1Answer should be(132)
    dp.part2Answer should be(0)
  }

  "2019 Day 18 - Example #4" should "calculate answers" in {
    val dp = new Day18ManyWorldsInterpretation("/twenty_nineteen/Day18-ManyWorldsInterpretation-example#4.txt")

    dp.part1Answer should be(136)
    dp.part2Answer should be(0)
  }

  "2019 Day 18 - Example #5" should "calculate answers" in {
    val dp = new Day18ManyWorldsInterpretation("/twenty_nineteen/Day18-ManyWorldsInterpretation-example#5.txt")

    dp.part1Answer should be(81)
    dp.part2Answer should be(81)
  }

  "2019 Day 18 - Example #6" should "calculate answers" in {
    val dp = new Day18ManyWorldsInterpretation("/twenty_nineteen/Day18-ManyWorldsInterpretation-example#6.txt")

    dp.part1Answer should be(26)
    dp.part2Answer should be(8)
  }

  "2019 Day 18 - Example #7" should "calculate answers" in {
    val dp = new Day18ManyWorldsInterpretation("/twenty_nineteen/Day18-ManyWorldsInterpretation-example#7.txt")

    dp.part1Answer should be(114)
    dp.part2Answer should be(72)
  }
}
