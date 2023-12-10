package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day10PipeMazeTest extends FlatSpec with Matchers {
  "2023 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10PipeMaze("/twenty_twentythree/Day10-PipeMaze-input.txt", '|')

    dp.part1Answer should be(6599)
    dp.part2Answer should be(477)
  }

  "2023 Day 10 - Example #1" should "calculate answers" in {
    val dp = new Day10PipeMaze("/twenty_twentythree/Day10-PipeMaze-example#1.txt", 'F')

    dp.part1Answer should be(4)
    dp.part2Answer should be(1)
  }

  "2023 Day 10 - Example #2" should "calculate answers" in {
    val dp = new Day10PipeMaze("/twenty_twentythree/Day10-PipeMaze-example#2.txt", 'F')

    dp.part1Answer should be(8)
    dp.part2Answer should be(1)
  }

  "2023 Day 10 - Example #3" should "calculate answers" in {
    val dp = new Day10PipeMaze("/twenty_twentythree/Day10-PipeMaze-example#3.txt", 'F')

    dp.part1Answer should be(23)
    dp.part2Answer should be(4)
  }

  "2023 Day 10 - Example #4" should "calculate answers" in {
    val dp = new Day10PipeMaze("/twenty_twentythree/Day10-PipeMaze-example#4.txt", 'F')

    dp.part1Answer should be(70)
    dp.part2Answer should be(8)
  }

  "2023 Day 10 - Example #5" should "calculate answers" in {
    val dp = new Day10PipeMaze("/twenty_twentythree/Day10-PipeMaze-example#5.txt", '7')

    dp.part1Answer should be(80)
    dp.part2Answer should be(10)
  }
}


