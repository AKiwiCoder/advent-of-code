package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.StringUtilities

import scala.annotation.tailrec

object Day11CorporatePolicy {
  def incrementPassword(original: String): String = {
    def incrementPasswordWorker(working: List[Char]): (List[Char], Boolean) = {
      if (working.isEmpty) {
        (List[Char](), true)
      } else {
        val prev = incrementPasswordWorker(working.tail)
        if (prev._2) {
          if (working.head == 'z') {
            ('a' :: prev._1, true)
          } else {
            ((working.head.toInt + 1).toChar :: prev._1, false)
          }
        } else {
          (working.head :: prev._1, false)
        }
      }
    }
    incrementPasswordWorker(original.toCharArray.toList)._1.mkString
  }

  private val invalidChars = "[iol]".r

  def findNonOverlappingTriples(password: String): List[String] = {
    @tailrec
    def process(working: String, acc: List[String]): List[String] = {
      if (working.isEmpty || working.length <= 2) {
        acc
      } else if (working.charAt(0) + 1 == working.charAt(1) && working.charAt(1) + 1 == working.charAt(2)) {
        process(working.drop(3), working.take(3) :: acc)
      } else {
        process(working.tail, acc)
      }
    }
    process(password, List())
  }

  def validatePassword(password: String): Boolean = {
    if (invalidChars.findFirstMatchIn(password).isDefined) {
      return false
    }
    StringUtilities.findNonOverlappingPairs(password).size >= 2 && findNonOverlappingTriples(password).nonEmpty
  }

  @tailrec
  private def findNextPassword(input: String): String = {
    val next = incrementPassword(input)
    if (validatePassword(next)) next else findNextPassword(next)
  }
}

class Day11CorporatePolicy(input: String) extends DailyProblem[String, String] {
  override val part1Answer: String = Day11CorporatePolicy.findNextPassword(input)
  override val part2Answer: String = Day11CorporatePolicy.findNextPassword(part1Answer)
}
