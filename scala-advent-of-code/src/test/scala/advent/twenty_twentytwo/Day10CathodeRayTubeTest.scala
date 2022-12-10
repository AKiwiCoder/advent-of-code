package advent.twenty_twentytwo

import org.scalatest.{FlatSpec, Matchers}

class Day10CathodeRayTubeTest extends FlatSpec with Matchers {

  val part2 =
    """####.#....###..#....####..##..####.#....
      |#....#....#..#.#.......#.#..#....#.#....
      |###..#....#..#.#......#..#......#..#....
      |#....#....###..#.....#...#.##..#...#....
      |#....#....#....#....#....#..#.#....#....
      |####.####.#....####.####..###.####.####.
      |""".stripMargin

  "2020 Day 10 - Input" should "calculate answers" in {
    val dp = new Day10CathodeRayTube("/twenty_twentytwo/Day10-CathodeRayTube-input.txt")

    dp.part1Answer should be(14780)
    dp.part2Answer should be(part2)
  }

  val example =
    """##..##..##..##..##..##..##..##..##..##..
      |###...###...###...###...###...###...###.
      |####....####....####....####....####....
      |#####.....#####.....#####.....#####.....
      |######......######......######......####
      |#######.......#######.......#######.....
      |""".stripMargin

  "2020 Day 10 - Example #1" should "calculate answers" in {
    val dp = new Day10CathodeRayTube("/twenty_twentytwo/Day10-CathodeRayTube-example#1.txt")

    dp.part1Answer should be(13140)
    dp.part2Answer should be(example)
  }
}


