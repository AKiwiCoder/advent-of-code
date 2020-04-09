package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day12LeonardosMonorailTest  extends FlatSpec with Matchers {
  "2016 Day 12 - Input" should "calculate answers" in {
    val dp = new Day12LeonardosMonorail("/twenty_sixteen/Day12-LeonardosMonorail-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day12LeonardosMonorail("/twenty_sixteen/Day12-LeonardosMonorail-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
