package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day18OperationOrderTest extends FlatSpec with Matchers {
  "2020 Day 18 - Input" should "calculate answers" in {
    val dp = new Day18OperationOrder("/twenty_twenty/Day18-OperationOrder-input.txt")

    dp.part1Answer should be(3159145843816l)
    dp.part2Answer should be(55699621957369l)
  }

  "2020 Day 18 - Example #1" should "calculate answers" in {
    val dp = new Day18OperationOrder("/twenty_twenty/Day18-OperationOrder-example#1.txt")

    dp.part1Answer should be(26457)
    dp.part2Answer should be(694173)
  }
}


