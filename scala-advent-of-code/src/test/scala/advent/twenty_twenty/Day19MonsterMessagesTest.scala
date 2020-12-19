package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day19MonsterMessagesTest extends FlatSpec with Matchers {
  "2020 Day 19 - Input" should "calculate answers" in {
    val dp = new Day19MonsterMessages("/twenty_twenty/Day19-MonsterMessages-input.txt")

    dp.part1Answer should be(122)
    dp.part2Answer should be(287)
  }

  "2020 Day 19 - Example #1" should "calculate answers" in {
    val dp = new Day19MonsterMessages("/twenty_twenty/Day19-MonsterMessages-example#1.txt")

    dp.part1Answer should be(2)
    dp.part2Answer should be(2)
  }

  "2020 Day 19 - Example #2" should "calculate answers" in {
    val dp = new Day19MonsterMessages("/twenty_twenty/Day19-MonsterMessages-example#2.txt")

    dp.part1Answer should be(3)
    dp.part2Answer should be(12)
  }
}


