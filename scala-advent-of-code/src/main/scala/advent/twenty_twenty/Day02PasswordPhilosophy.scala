package advent.twenty_twenty 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class PasswordPolicy(min: Int, max :Int, c : Char, password : String) {
  def isValidPart1() : Boolean = {
    val count = password.toList.count(pc => pc == c)
    count >= min && count <= max
  }

  def isValidPart2() : Boolean = {
    val charAtOne = password.charAt(min-1) == c
    val charAtTwo = password.charAt(max-1) == c
    (charAtOne && !charAtTwo) || (!charAtOne && charAtTwo)
  }
}

class Day02PasswordPhilosophy(filename : String) extends DailyProblem[Int, Int] {
  private val linePattern = "([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)".r

  private val input = FileUtilities.readFile(filename, parse)

  private def parse(line: String): PasswordPolicy = {
    line match {
      case linePattern(pMin, pMax, pChar, pPassword) => PasswordPolicy(pMin.toInt, pMax.toInt, pChar.charAt(0), pPassword)
    }
  }

  override val part1Answer: Int = input.count(p => p.isValidPart1())
  override val part2Answer: Int = input.count(p => p.isValidPart2())
}


