package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day10AdapterArrayTest extends FlatSpec with Matchers {
  "2020 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10AdapterArray("/twenty_twenty/Day10-AdapterArray-input.txt")

    dp.part1Answer should be(2343)
    dp.part2Answer should be(31581162962944l)
  }

  "2020 Day 10 - Example #1" should "calculate answers" in {
    val dp = new Day10AdapterArray("/twenty_twenty/Day10-AdapterArray-example#1.txt")

    dp.part1Answer should be(35)
    dp.part2Answer should be(8)
  }

  "2020 Day 10 - Example #2" should "calculate answers" in {
    val dp = new Day10AdapterArray("/twenty_twenty/Day10-AdapterArray-example#2.txt")

    dp.part1Answer should be(220)
    dp.part2Answer should be(19208)
  }
}


