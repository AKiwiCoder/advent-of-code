package advent.twenty_twenty 

import org.scalatest.{FlatSpec, Matchers}

class Day21AllergenAssessmentTest extends FlatSpec with Matchers {
  "2020 Day 21 - Input" should "calculate answers" in {
    val dp = new Day21AllergenAssessment("/twenty_twenty/Day21-AllergenAssessment-input.txt")

    dp.part1Answer should be(2734)
    dp.part2Answer should be("kbmlt,mrccxm,lpzgzmk,ppj,stj,jvgnc,gxnr,plrlg")
  }

  "2020 Day 21 - Example #1" should "calculate answers" in {
    val dp = new Day21AllergenAssessment("/twenty_twenty/Day21-AllergenAssessment-example#1.txt")

    dp.part1Answer should be(5)
    dp.part2Answer should be("mxmxvkd,sqjhc,fvjkl")
  }
}


