package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day23UnstableDiffusionTest extends FlatSpec with Matchers {
  "2020 Day 23 - Input" should "calculate answers" in {
    val dp = new Day23UnstableDiffusion("/twenty_twentytwo/Day23-UnstableDiffusion-input.txt")

    dp.part1Answer should be(3684)
    dp.part2Answer should be(862)
  }

  "2020 Day 23 - Example #1" should "calculate answers" in {
    val dp = new Day23UnstableDiffusion("/twenty_twentytwo/Day23-UnstableDiffusion-example#1.txt")

    dp.part1Answer should be(110)
    dp.part2Answer should be(20)
  }

  "2020 Day 23 - Example #2" should "calculate answers" in {
    val dp = new Day23UnstableDiffusion("/twenty_twentytwo/Day23-UnstableDiffusion-example#2.txt")

    dp.part1Answer should be(25)
    dp.part2Answer should be(4)
  }
}


