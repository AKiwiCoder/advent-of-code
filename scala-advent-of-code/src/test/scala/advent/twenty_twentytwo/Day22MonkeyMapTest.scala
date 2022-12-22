package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day22MonkeyMapTest extends FlatSpec with Matchers {
  val rSide1 = (1,  50,   0,  99,  49)
  val rSide2 = (2, 100,   0, 149,  49)
  val rSide3 = (3,  50,  50,  99,  99)
  val rSide4 = (4,   0, 100,  49, 149)
  val rSide5 = (5,  50, 100,  99, 149)
  val rSide6 = (6,   0, 150,  49, 199)

  "2020 Day 22 - Input" should "calculate answers" in {
    val dp = new Day22MonkeyMap("/twenty_twentytwo/Day22-MonkeyMap-input.txt",  50, List(rSide1, rSide2, rSide3, rSide4, rSide5, rSide6), false)

    dp.part1Answer should be(73346)
    dp.part2Answer should be(106392)
  }

  val exSide1 = (1, 8, 0, 11,   3)
  val exSide2 = (2, 0, 4,  3,   7)
  val exSide3 = (3, 4, 4,  7,   7)
  val exSide4 = (4, 8, 4,  11,  7)
  val exSide5 = (5, 8, 8,  11, 11)
  val exSide6 = (6,12, 8,  15, 11)

  "2020 Day 22 - Example #1" should "calculate answers" in {
    val dp = new Day22MonkeyMap("/twenty_twentytwo/Day22-MonkeyMap-example#1.txt", 4, List(exSide1, exSide2, exSide3, exSide4, exSide5, exSide6), true)

    dp.part1Answer should be(6032)
    dp.part2Answer should be(5031)
  }
}


