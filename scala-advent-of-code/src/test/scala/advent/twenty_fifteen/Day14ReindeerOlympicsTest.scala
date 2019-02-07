package advent.twenty_fifteen

import org.scalatest.{FlatSpec, Matchers}

class Day14ReindeerOlympicsTest extends FlatSpec with Matchers {
  "2015 Day 14 - Input" should "calculate answers" in {
    val dp = new Day14ReindeerOlympics("/twenty_fifteen/Day14-ReindeerOlympics-input.txt", 2503)

    dp.part1Answer should be(2640)
    dp.part2Answer should be(1102)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day14ReindeerOlympics("/twenty_fifteen/Day14-ReindeerOlympics-example#1.txt", 1000)

    dp.part1Answer should be(1120)
    dp.part2Answer should be(689)
  }
}
