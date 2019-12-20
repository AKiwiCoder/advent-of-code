package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day20DonutMazeTest extends FlatSpec with Matchers {
  "2019 Day 20 - Input" should "calculate answers" in {
    val dp = new Day20DonutMaze("/twenty_nineteen/Day20-DonutMaze-input.txt")

    dp.part1Answer should be(482)
    dp.part2Answer should be(5912)
  }

  "2019 Day 20 - Example #1" should "calculate answers" in {
    val dp = new Day20DonutMaze("/twenty_nineteen/Day20-DonutMaze-example#1.txt")

    dp.part1Answer should be(23)
    dp.part2Answer should be(26)
  }


  "2019 Day 20 - Example #2" should "calculate answers" in {
    val dp = new Day20DonutMaze("/twenty_nineteen/Day20-DonutMaze-example#2.txt")

    dp.part1Answer should be(58)
    dp.part2Answer should be(-1)
  }

  "2019 Day 20 - Example #3" should "calculate answers" in {
    val dp = new Day20DonutMaze("/twenty_nineteen/Day20-DonutMaze-example#3.txt")

    dp.part1Answer should be(77)
    dp.part2Answer should be(396)
  }
}
