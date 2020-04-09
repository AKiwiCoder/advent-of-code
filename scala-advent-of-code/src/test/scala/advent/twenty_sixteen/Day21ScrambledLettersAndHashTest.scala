package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day21ScrambledLettersAndHashTest  extends FlatSpec with Matchers {
  "2016 Day 21 - Input" should "calculate answers" in {
    val dp = new Day21ScrambledLettersAndHash("/twenty_sixteen/Day21-ScrambledLettersAndHash-input.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }

  "2015 Day 9 - Example #1" should "calculate answers" in {
    val dp = new Day21ScrambledLettersAndHash("/twenty_sixteen/Day21-ScrambledLettersAndHash-example#1.txt")
    dp.part1Answer should be(0)
    dp.part2Answer should be(0)
  }
}
