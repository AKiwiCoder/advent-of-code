package advent.twenty_nineteen 

import org.scalatest.{FlatSpec, Matchers}

class Day14SpaceStoichiometryTest extends FlatSpec with Matchers {
  "2019 Day 14 - Input" should "calculate answers" in {
    val dp = new Day14SpaceStoichiometry("/twenty_nineteen/Day14-SpaceStoichiometry-input.txt")

    dp.part1Answer should be(579797)
    dp.part2Answer should be(2521844)
  }

  "2019 Day 14 - Example #1" should "calculate answers" in {
    val dp = new Day14SpaceStoichiometry("/twenty_nineteen/Day14-SpaceStoichiometry-example#1.txt")

    dp.part1Answer should be(31)
    dp.part2Answer should be(34482758620L)
  }

  "2019 Day 14 - Example #2" should "calculate answers" in {
    val dp = new Day14SpaceStoichiometry("/twenty_nineteen/Day14-SpaceStoichiometry-example#2.txt")

    dp.part1Answer should be(165)
    dp.part2Answer should be(6323777402L)
  }

  "2019 Day 14 - Example #3" should "calculate answers" in {
    val dp = new Day14SpaceStoichiometry("/twenty_nineteen/Day14-SpaceStoichiometry-example#3.txt")

    dp.part1Answer should be(13312)
    dp.part2Answer should be(82892753)
  }

    "2019 Day 14 - Example #4" should "calculate answers" in {
      val dp = new Day14SpaceStoichiometry("/twenty_nineteen/Day14-SpaceStoichiometry-example#4.txt")

      dp.part1Answer should be(180697)
      dp.part2Answer should be(5586022)
    }


    "2019 Day 14 - Example #5" should "calculate answers" in {
      val dp = new Day14SpaceStoichiometry("/twenty_nineteen/Day14-SpaceStoichiometry-example#5.txt")

      dp.part1Answer should be(2210736)
      dp.part2Answer should be(460664)
    }
}


