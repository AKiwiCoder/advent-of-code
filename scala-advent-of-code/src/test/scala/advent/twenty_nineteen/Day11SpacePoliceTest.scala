package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day11SpacePoliceTest extends FlatSpec with Matchers {

  private val EXPECTED = """####..##..#..#.#..#..##....##.####.###.
                           |#....#..#.#..#.#.#..#..#....#.#....#..#
                           |###..#....####.##...#.......#.###..#..#
                           |#....#.##.#..#.#.#..#.##....#.#....###.
                           |#....#..#.#..#.#.#..#..#.#..#.#....#.#.
                           |####..###.#..#.#..#..###..##..####.#..#""".stripMargin

  "2019 Day 11 - Input" should "calculate answers" in {
    val dp = new Day11SpacePolice("/twenty_nineteen/Day11-SpacePolice-input.txt")

    dp.part1Answer should be(1932)
    dp.part2Answer should be(EXPECTED)
  }
}


