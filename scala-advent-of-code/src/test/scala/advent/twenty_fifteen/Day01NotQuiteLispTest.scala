package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day01NotQuiteLispTest extends FlatSpec with Matchers {
  "2015 Day 1 - Input" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-input.txt")

    dp.part1Answer should be(74)
    dp.part2Answer should be(1795)
  }

  "2015 Day 1 - Example #1" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#1.txt")

    dp.part1Answer should be(0)
    dp.part2Answer should be(-1)
  }

  "2015 Day 1 - Example #2" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#2.txt")

    dp.part1Answer should be(0)
    dp.part2Answer should be(-1)
  }

  "2015 Day 1 - Example #3" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#3.txt")

    dp.part1Answer should be(3)
    dp.part2Answer should be(-1)
  }

  "2015 Day 1 - Example #4" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#4.txt")

    dp.part1Answer should be(3)
    dp.part2Answer should be(-1)
  }

  "2015 Day 1 - Example #5" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#5.txt")

    dp.part1Answer should be(3)
    dp.part2Answer should be(1)
  }

  "2015 Day 1 - Example #6" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#6.txt")

    dp.part1Answer should be(-1)
    dp.part2Answer should be(3)
  }

  "2015 Day 1 - Example #7" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#7.txt")

    dp.part1Answer should be(-1)
    dp.part2Answer should be(1)
  }

  "2015 Day 1 - Example #8" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#8.txt")

    dp.part1Answer should be(-3)
    dp.part2Answer should be(1)
  }

  "2015 Day 1 - Example #9" should "calculate answers" in {
    val dp = new Day01NotQuiteLisp("/twenty_fifteen/Day01-NotQuiteLisp-example#9.txt")

    dp.part1Answer should be(-3)
    dp.part2Answer should be(1)
  }
}
