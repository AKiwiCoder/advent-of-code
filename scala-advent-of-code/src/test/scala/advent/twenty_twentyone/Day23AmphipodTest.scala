package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day23AmphipodTest extends FlatSpec with Matchers {
  "2020 Day 23 - Input" should "calculate answers" in {
    val dp1 = new Day23AmphipodOne("/twenty_twentyone/Day23-Amphipod-input.txt")
    val dp2 = new Day23AmphipodTwo("/twenty_twentyone/Day23-Amphipod-input-2.txt")

    dp1.part1Answer should be(11516L)
    dp2.part2Answer should be(40272L)
  }

  "2020 Day 23 - Example #1" should "calculate answers" in {
    val dp1 = new Day23AmphipodOne("/twenty_twentyone/Day23-Amphipod-example#1.txt")
    val dp2 = new Day23AmphipodTwo("/twenty_twentyone/Day23-Amphipod-example#1-2.txt")

    dp1.part1Answer should be(12521L)
    dp2.part2Answer should be(44169L)
  }

  "2020 Day 23 - Example #2" should "calculate answers" in {
    val dp = new Day23AmphipodOne("/twenty_twentyone/Day23-Amphipod-example#2.txt")

    dp.part1Answer should be(0L)
  }

  "2020 Day 23 - Example #3" should "calculate answers" in {
    val dp = new Day23AmphipodOne("/twenty_twentyone/Day23-Amphipod-example#3.txt")

    dp.part1Answer should be(0L)
  }
}


