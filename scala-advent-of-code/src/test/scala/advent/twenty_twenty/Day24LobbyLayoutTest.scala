package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day24LobbyLayoutTest extends FlatSpec with Matchers {
  "2020 Day 24 - Input" should "calculate answers" in {
    val dp = new Day24LobbyLayout("/twenty_twenty/Day24-LobbyLayout-input.txt")

    dp.part1Answer should be(228)
    dp.part2Answer should be(3672)
  }

  "2020 Day 24 - Example #1" should "calculate answers" in {
    val dp = new Day24LobbyLayout("/twenty_twenty/Day24-LobbyLayout-example#1.txt")

    dp.part1Answer should be(10)
    dp.part2Answer should be(2208)
  }
}


