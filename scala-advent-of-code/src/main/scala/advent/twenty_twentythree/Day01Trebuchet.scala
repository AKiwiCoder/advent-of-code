package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day01Trebuchet(filename: String) extends DailyProblem[Int, Int] {
  private val NUMBERS = List(("one", "1"), ("two", "2"), ("three", "3"), ("four", "4"), ("five", "5"), ("six", "6"), ("seven", "7"), ("eight", "8"), ("nine", "9"))
  private val NUMBERS_START = NUMBERS
  private val NUMBERS_BACKWARDS = NUMBERS.map(a => (a._1.reverse, a._2))

  private val input = FileUtilities.readFile(filename)

  private def toNumber(value: String): Int = {
    val f = value.find(c => c.isDigit).getOrElse('0')
    val l = value.findLast(c => c.isDigit).getOrElse('0')
    (f + "" + l).toInt
  }

  @tailrec
  private final def searchForNumberOrString(numbers : List[(String,String)], s: String): (String, String) = {
    if (s.isEmpty) {
      ("", s)
    } else if (s.head.isDigit) {
      ("" + s.head, s.tail)
    } else {
      val m = numbers.find(t => s.startsWith(t._1))
      if (m.isDefined) {
        (m.get._2, s.drop(m.get._1.length))
      } else {
        searchForNumberOrString(numbers, s.tail)
      }
    }
  }

  private def replaceText(str: String) : String = {
    val forwards = searchForNumberOrString(NUMBERS_START, str)
    val reversed = searchForNumberOrString(NUMBERS_BACKWARDS, forwards._2.reverse)
    forwards._1 + reversed._1
  }

  override val part1Answer: Int = input.map(s => toNumber(s)).sum
  override val part2Answer: Int = input.map(s => replaceText(s)).map(s => toNumber(s)).sum
}


