package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day19MonsterMessages(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private val originalRules = input.filter(line => line.contains(":")).map(line => {
    val num = line.substring(0, line.indexOf(":")).trim.toInt
    val rules = line.substring(line.indexOf(":") + 1).trim.split("\\|").map(chunk => chunk.trim().split(" ").toList.map(
      bit => if (bit.startsWith("\"")) bit.substring(1, 2) else bit.toInt)).toList
    num -> rules
  }).toMap

  private val messages = input.filterNot(line => line.contains(":") || line.isEmpty)

  private def matches(rules: Map[Int, List[List[Any]]], toCheck: List[Any], message: String): Boolean = {
    if (message.isEmpty) {
      toCheck.isEmpty
    } else if (toCheck.isEmpty) {
      false
    } else {
      val next = toCheck.head
      if (next.isInstanceOf[String]) {
        message.startsWith(next.toString) && matches(rules, toCheck.tail, message.substring(1))
      } else {
        val rule = rules(next.toString.toInt)
        rule.exists(option => matches(rules, option ::: toCheck.tail, message))
      }
    }
  }

  private def part1(): Int = {
    messages.count(message => matches(originalRules, List(0), message))
  }


  def part2(): Int = {
    val mod8 = originalRules + (8 -> List(List(42), List(42, 42), List(42, 42, 42), List(42, 42, 42, 42), List(42, 42, 42, 42, 42)))
    val mod11 = mod8 + (11 -> List(List(42, 31), List(42, 11, 31)))
    messages.count(message => matches(mod11, List(0), message))
  }

  override val part1Answer: Int = part1()
  override val part2Answer: Int = part2()
}


