package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day22SlamShuffleTest extends FlatSpec with Matchers {
  "2019 Day 22 - Input" should "calculate answers" in {
    val dp = new Day22SlamShuffle("/twenty_nineteen/Day22-SlamShuffle-input.txt", 10007, 2019, 119315717514047L, 101741582076661L, 2020)

    dp.part1Answer should be(3749)
    dp.part2Answer should be(77225522112241L)
  }

  "2019 Day 22 - Example #1" should "calculate answers" in {
    val dp = new Day22SlamShuffle("/twenty_nineteen/Day22-SlamShuffle-example#1.txt", 10, 1)

    dp.part1Answer should be(7)
    dp.part2Answer should be(0)
  }

  "2019 Day 22 - Example #2" should "calculate answers" in {
    val dp = new Day22SlamShuffle("/twenty_nineteen/Day22-SlamShuffle-example#2.txt", 10, 1)

    dp.part1Answer should be(4)
    dp.part2Answer should be(0)
  }

  "2019 Day 22 - Example #3" should "calculate answers" in {
    val dp = new Day22SlamShuffle("/twenty_nineteen/Day22-SlamShuffle-example#3.txt", 10, 1)

    dp.part1Answer should be(5)
    dp.part2Answer should be(0)
  }

  "2019 Day 22 - Example #4" should "calculate answers" in {
    val dp = new Day22SlamShuffle("/twenty_nineteen/Day22-SlamShuffle-example#4.txt", 10, 1)

    dp.part1Answer should be(4)
    dp.part2Answer should be(0)
  }
}


