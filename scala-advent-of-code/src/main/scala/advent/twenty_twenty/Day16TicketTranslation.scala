package advent.twenty_twenty 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class Rule(min: Int, max : Int) {
  def isValid(value : Int) : Boolean = min <= value && value <= max
}

case class Field(name :String, rule1 : Rule, rule2 : Rule) {
  def isValid(value: Int): Boolean = rule1.isValid(value) || rule2.isValid(value)
}

class Day16TicketTranslation(filename : String) extends DailyProblem[Int, Long] {

  private val fieldPattern = "([a-z ]+): ([0-9]+)-([0-9]+) or ([0-9]+)-([0-9]+)".r

  private val input = FileUtilities.readFile(filename)

  private val fields = input.foldLeft(List[Field]())((acc, line) => line match {
    case fieldPattern(name, r1min, r1max, r2min, r2max) => Field(name, Rule(r1min.toInt, r1max.toInt), Rule(r2min.toInt, r2max.toInt)) :: acc
    case _ => acc
  })

  private val myTickets = input.dropWhile(line => !line.startsWith("your"))(1).split(",").map(_.toInt).toList

  private val otherTickets = input.dropWhile(line => !line.startsWith("nearby")).tail.map(line => line.split(",").map(_.toInt).toList)

  private def part1(): Int = {
    def invalidFields(ticket : List[Int]) : List[Int] = {
      ticket.filter(value => !fields.exists(field => field.isValid(value)))
    }
    otherTickets.flatMap(ticket => invalidFields(ticket)).sum
  }

  private def isValidTicket(ticket :List[Int]) : Boolean = {
    ticket.forall(value => fields.exists(field => field.isValid(value)))
  }

  private val validTickets = otherTickets.filter(ticket => isValidTicket(ticket))

  private def part2(): Long = {
    @tailrec
    def process(remainingFields : List[(List[Field], Int)], matched : List[(Field, Int)]) : List[(Field, Int)] = {
      if (remainingFields.isEmpty) {
        matched
      } else {
        val filtered = remainingFields.map(entry => (entry._1.filter(field => validTickets.forall(values => field.isValid(values(entry._2)))), entry._2))
        val identified = filtered.filter(fields => fields._1.size == 1).map(entry => (entry._1.head, entry._2))
        val newRemaining = remainingFields.filterNot(entry => identified.exists(id => id._2 == entry._2)).map(entry => (entry._1.filterNot(field => identified.exists(key => key._1 == field)), entry._2))
        process(newRemaining, identified ++ matched)
      }
    }

    val numFields = validTickets.head.size
    val possibleFields = (0 until numFields).foldLeft(List[List[Field]]())((acc, _) => fields:: acc).zipWithIndex
    val fieldIndexes = process(possibleFields, List())
    fieldIndexes.filter(_._1.name.startsWith("departure")).map(e => myTickets(e._2).toLong).product
  }

  override val part1Answer: Int = part1()
  override val part2Answer: Long = part2()
}


