package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day13TransparentOrigamiTest extends FlatSpec with Matchers {

  private val INPUT_ANSWER = //
      "*    ***   **  ***  ***  ****  **  *** \n" + //
      "*    *  * *  * *  * *  * *    *  * *  *\n" + //
      "*    *  * *    *  * *  * ***  *    *** \n" + //
      "*    ***  * ** ***  ***  *    *    *  *\n" + //
      "*    * *  *  * *    * *  *    *  * *  *\n" + //
      "**** *  *  *** *    *  * ****  **  ***"

  "2020 Day 13 - Input" should "calculate answers" in {
    val dp = new Day13TransparentOrigami("/twenty_twentyone/Day13-TransparentOrigami-input.txt")

    dp.part1Answer should be(818)
    dp.part2Answer should be(INPUT_ANSWER)
  }

  private val EXAMPLE_ANSWER =
    """*****
      |*   *
      |*   *
      |*   *
      |*****""".stripMargin

  "2020 Day 13 - Example #1" should "calculate answers" in {
    val dp = new Day13TransparentOrigami("/twenty_twentyone/Day13-TransparentOrigami-example#1.txt")

    dp.part1Answer should be(17)
    dp.part2Answer should be(EXAMPLE_ANSWER)
  }
}


