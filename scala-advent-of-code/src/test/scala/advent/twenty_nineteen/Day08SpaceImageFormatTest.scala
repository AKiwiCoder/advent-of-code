package advent.twenty_nineteen

import org.scalatest.{FlatSpec, Matchers}

class Day08SpaceImageFormatTest extends FlatSpec with Matchers {


  private val inputAnswer2 = "" + //
    "+####+##+##++##+++##+##+#\n" + //
    "+####+##+#+##+#+##+#+##+#\n" + //
    "+####++++#+####+##+#++++#\n" + //
    "+####+##+#+####+++##+##+#\n" + //
    "+####+##+#+##+#+####+##+#\n" + //
    "++++#+##+##++##+####+##+#\n";

  "2019 Day 08 - Input" should "calculate answers" in {
    val dp = new Day08SpaceImageFormat("/twenty_nineteen/Day08-SpaceImageFormat-input.txt", 25, 6)

    dp.part1Answer should be(1215)
    dp.part2Answer should be(inputAnswer2)
  }

  private val example1Answer2 = "" + //
    "+..\n" + //
    "#+.\n";

  "2019 Day 08 - Example #1" should "calculate answers" in {
    val dp = new Day08SpaceImageFormat("/twenty_nineteen/Day08-SpaceImageFormat-example#1.txt", 3, 2)

    dp.part1Answer should be(1)
    dp.part2Answer should be(example1Answer2)
  }

  private val example2Answer2 = "" + //
    "#+\n" + //
    "+#\n";

  "2019 Day 08 - Example #2" should "calculate answers" in {
    val dp = new Day08SpaceImageFormat("/twenty_nineteen/Day08-SpaceImageFormat-example#2.txt", 2, 2)

    dp.part1Answer should be(3)
    dp.part2Answer should be(example2Answer2)
  }
}


