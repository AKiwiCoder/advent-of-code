package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

object Operation {
  private val oldPlus = "Operation: new = old \\+ ([0-9]+)".r
  private val oldTimes = "Operation: new = old \\* ([0-9]+)".r
  private val oldSquared = "Operation: new = old \\* old".r

  def parse(line: String): Operation =
    line match {
      case oldPlus(num) => OldPlus(num.toLong)
      case oldTimes(num) => OldTimes(num.toLong)
      case oldSquared() => OldSquared()
    }
}

abstract class Operation {
  def execute(old: Long): Long
}

case class OldPlus(constant: Long) extends Operation {
  override def execute(old: Long): Long = old + constant
}

case class OldTimes(constant: Long) extends Operation {
  override def execute(old: Long): Long = old * constant
}

case class OldSquared() extends Operation {
  override def execute(old: Long): Long = old * old
}

case class Monkey(id: Int, items: List[Long], operation: Operation, divisibleBy: Int, tTarget: Int, fTarget: Int, inspected: Long) {
  def withInspections(count : Long): Monkey = {
    Monkey(id, List(), operation, divisibleBy, tTarget, fTarget, inspected + count)
  }

  def addItem(newItem : Long): Monkey = {
    Monkey(id, items :+ newItem, operation, divisibleBy, tTarget, fTarget, inspected)
  }
}

class Day11MonkeyInTheMiddle(filename: String) extends DailyProblem[Long, Long] {
  private val input = FileUtilities.readFile(filename).filter(_.nonEmpty).grouped(6).map(lst => parse(lst)).map(m => m.id -> m).toMap

  private def parse(lines: List[String]): Monkey = {
    val monkeyNum = lines(0).trim.substring(6).replace(":", " ").trim.toInt
    val items = lines(1).substring(17).split(",").map(item => item.trim.toLong).toList
    val operation = Operation.parse(lines(2).trim)
    val divisibleBy = lines(3).substring(20).trim.toInt
    val tTarget = lines(4).trim.substring(24).trim.toInt
    val fTarget = lines(5).trim.substring(25).trim.toInt
    Monkey(monkeyNum, items, operation, divisibleBy.toInt, tTarget, fTarget, 0)
  }

  @tailrec
  private def runKeepAway(roundLimit : Int, divideBy : Long, gcd : Long, round: Int, monkey: Int, monkeys: Map[Int, Monkey]): Map[Int, Monkey] = {
    if (round == roundLimit)
      monkeys
    else if (monkey == monkeys.size) {
      runKeepAway(roundLimit, divideBy, gcd, round + 1, 0, monkeys)
    } else {
      val src_monkey = monkeys(monkey)

      if (src_monkey.items.isEmpty) {
        runKeepAway(roundLimit, divideBy, gcd, round, monkey + 1, monkeys)
      } else {
        val after_throws = src_monkey.items.foldLeft(monkeys)((mons, old) => {
          val newItem = src_monkey.operation.execute(old) / divideBy
          if (newItem % src_monkey.divisibleBy == 0) {
            mons + (src_monkey.tTarget -> mons(src_monkey.tTarget).addItem(newItem % gcd))
          } else {
            mons + (src_monkey.fTarget -> mons(src_monkey.fTarget).addItem(newItem % gcd))
          }
        })

        val new_monkeys = after_throws + (src_monkey.id -> src_monkey.withInspections(src_monkey.items.size))
        runKeepAway(roundLimit, divideBy, gcd, round, monkey + 1, new_monkeys)
      }
    }
  }


  private val gcd: Long = input.foldLeft(1)((acc, m) => m._2.divisibleBy * acc)

  private val part1Inspected = runKeepAway(20,3,gcd, 0, 0, input).map(p => p._2.inspected).toList.sorted.reverse
  private val part2Inspected = runKeepAway(10000,1, gcd,0, 0, input).map(p => p._2.inspected).toList.sorted.reverse

  override val part1Answer: Long = part1Inspected(0) * part1Inspected(1)
  override val part2Answer: Long = part2Inspected(0) * part2Inspected(1)
}

