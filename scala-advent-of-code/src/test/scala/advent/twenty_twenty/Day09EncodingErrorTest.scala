package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day09EncodingErrorTest extends FlatSpec with Matchers {
  "2020 Day 09 - Input" should "calculate answers" in {
    val dp = new Day09EncodingError("/twenty_twenty/Day09-EncodingError-input.txt", 25)

    dp.part1Answer should be(88311122)
    dp.part2Answer should be(13549369)
  }

  "2020 Day 09 - Example #1" should "calculate answers" in {
    val dp = new Day09EncodingError("/twenty_twenty/Day09-EncodingError-example#1.txt", 5)

    dp.part1Answer should be(127)
    dp.part2Answer should be(62)
  }
}


