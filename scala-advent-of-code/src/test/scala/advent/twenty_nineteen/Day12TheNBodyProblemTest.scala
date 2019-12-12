package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day12TheNBodyProblemTest extends FlatSpec with Matchers {
  "2019 Day 12 - Input" should "calculate answers" in {
    val dp = new Day12TheNBodyProblem("/twenty_nineteen/Day12-TheNBodyProblem-input.txt", 1000)

    dp.part1Answer should be(9743)
    dp.part2Answer should be(288684633706728L)
  }

  "2019 Day 12 - Example #1" should "calculate answers" in {
    val dp = new Day12TheNBodyProblem("/twenty_nineteen/Day12-TheNBodyProblem-example#1.txt", 10)

    dp.part1Answer should be(179)
    dp.part2Answer should be(2772L)
  }

  "2019 Day 12 - Example #2" should "calculate answers" in {
    val dp = new Day12TheNBodyProblem("/twenty_nineteen/Day12-TheNBodyProblem-example#2.txt", 100)

    dp.part1Answer should be(1940)
    dp.part2Answer should be(4686774924L)
  }
}


