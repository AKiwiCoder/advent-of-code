package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day06CustomCustomsTest extends FlatSpec with Matchers {
  "2020 Day 06 - Input" should "calculate answers" in {
    val dp = new Day06CustomCustoms("/twenty_twenty/Day06-CustomCustoms-input.txt")

    dp.part1Answer should be(6534)
    dp.part2Answer should be(3402)
  }

  "2020 Day 06 - Example #1" should "calculate answers" in {
    val dp = new Day06CustomCustoms("/twenty_twenty/Day06-CustomCustoms-example#1.txt")

    dp.part1Answer should be(11)
    dp.part2Answer should be(6)
  }
}


