package advent.twenty_twentythree 

import org.scalatest.{FlatSpec, Matchers}

class Day16TheFloorWillBeLavaTest extends FlatSpec with Matchers {
  "2023 Day 16 - Input" should "calculate answers" in {
    val dp = new Day16TheFloorWillBeLava("/twenty_twentythree/Day16-TheFloorWillBeLava-input.txt")

    dp.part1Answer should be(6740)
    dp.part2Answer should be(7041)
  }

  "2023 Day 16 - Example #1" should "calculate answers" in {
    val dp = new Day16TheFloorWillBeLava("/twenty_twentythree/Day16-TheFloorWillBeLava-example#1.txt")

    dp.part1Answer should be(46)
    dp.part2Answer should be(51)
  }
}


