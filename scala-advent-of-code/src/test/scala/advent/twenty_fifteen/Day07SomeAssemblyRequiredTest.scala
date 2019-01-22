package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day07SomeAssemblyRequiredTest extends FlatSpec with Matchers {
    "2015 Day 7 - Input" should "calculate answers" in {
      val dp = new Day07SomeAssemblyRequired("/twenty_fifteen/Day07-SomeAssemblyRequired-input.txt")
      dp.part1Answer("a") should be(46065)
      dp.part2Answer("a") should be(14134)
    }

    "2015 Day 7 - Example #1" should "calculate answers" in {
      val dp = new Day07SomeAssemblyRequired("/twenty_fifteen/Day07-SomeAssemblyRequired-example#1.txt")
      dp.part1Answer("d") should be(72)
      dp.part1Answer("e") should be(507)
      dp.part1Answer("f") should be(492)
      dp.part1Answer("g") should be(114)
      dp.part1Answer("h") should be(65412)
      dp.part1Answer("i") should be(65079)
      dp.part1Answer("x") should be(123)
      dp.part1Answer("y") should be(456)
    }
}
