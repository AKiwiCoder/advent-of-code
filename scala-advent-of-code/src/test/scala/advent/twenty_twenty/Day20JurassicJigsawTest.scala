package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day20JurassicJigsawTest extends FlatSpec with Matchers {
  "2020 Day 20 - Input" should "calculate answers" in {
    val dp = new Day20JurassicJigsaw("/twenty_twenty/Day20-JurassicJigsaw-input.txt", 12)

    dp.part1Answer should be(16937516456219L)
    dp.part2Answer should be(1858)
  }

  "2020 Day 20 - Example #1" should "calculate answers" in {
    val dp = new Day20JurassicJigsaw("/twenty_twenty/Day20-JurassicJigsaw-example#1.txt", 3)

    dp.part1Answer should be(20899048083289L)
    dp.part2Answer should be(273)
  }
}


