package advent.twenty_sixteen

import org.scalatest.{FlatSpec, Matchers}

class Day08TwoFactorAuthenticationTest extends FlatSpec with Matchers {
  private val REAL = """.##..####.###..#..#.###..####.###....##.###...###.
                       |#..#.#....#..#.#..#.#..#....#.#..#....#.#..#.#....
                       |#..#.###..###..#..#.#..#...#..###.....#.#..#.#....
                       |####.#....#..#.#..#.###...#...#..#....#.###...##..
                       |#..#.#....#..#.#..#.#....#....#..#.#..#.#.......#.
                       |#..#.#....###...##..#....####.###...##..#....###..
                       |""".stripMargin

  "2016 Day 8 - Input" should "calculate answers" in {
    val dp = new Day08TwoFactorAuthentication("/twenty_sixteen/Day08-TwoFactorAuthentication-input.txt", 50, 6)

    dp.part1Answer should be(123)
    dp.part2Answer should be(REAL)
  }

  private val EXAMPLE_1 = """.#..#.#
                            |#.#....
                            |.#.....
                            |""".stripMargin

  "2016 Day 8 - Example #1" should "calculate answers" in {
    val dp = new Day08TwoFactorAuthentication("/twenty_sixteen/Day08-TwoFactorAuthentication-example#1.txt",7, 3)

    dp.part1Answer should be(6)
    dp.part2Answer should be(EXAMPLE_1)
  }
}
