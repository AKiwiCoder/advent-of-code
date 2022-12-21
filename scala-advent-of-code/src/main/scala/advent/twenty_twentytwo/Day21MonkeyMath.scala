package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day21MonkeyMath(filename: String, partTwoHint : Long) extends DailyProblem[Long, Long] {

  abstract class Monkey {
    def name(): String

    def value(): Long
  }

  case class MonkeyConst(name: String, value: Long) extends Monkey
  case class MonkeyMath(name: String, lhs: String, rhs: String, op: Char) extends Monkey {
    override def value(): Long = ???
  }

  private val input = FileUtilities.readFile(filename).map(l => {
    val split = l.split(":")
    val name = split(0).trim()
    val value = split(1).trim

    if (value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/")) {
      val lhs = value.substring(0, 4)
      val rhs = value.substring(7)
      val operator = value(5)
      MonkeyMath(name, lhs, rhs, operator)
    } else {
      MonkeyConst(name, value.toLong)
    }
  }).map(m => m.name -> m).toMap

  @tailrec
  private def doMath(monkeys: Map[String, Monkey]): Map[String, Monkey] = {
    val round = monkeys.map(entry => {
      entry._1 -> (entry._2 match {
        case c: MonkeyConst => c
        case m: MonkeyMath =>
          if (monkeys(m.rhs).isInstanceOf[MonkeyConst] && monkeys(m.lhs).isInstanceOf[MonkeyConst]) {
            MonkeyConst(m.name, m.op match {
              case '+' => monkeys(m.lhs).value + monkeys(m.rhs).value
              case '-' => monkeys(m.lhs).value - monkeys(m.rhs).value
              case '*' => monkeys(m.lhs).value * monkeys(m.rhs).value
              case '/' => monkeys(m.lhs).value / monkeys(m.rhs).value
              case '=' => if (monkeys(m.lhs).value == monkeys(m.rhs).value) monkeys("humn").value() else (monkeys(m.lhs).value - monkeys(m.rhs).value)
            })
          } else {
            m
          }
      })
    })

    if (round == monkeys)
      round
    else
      doMath(round)
  }

  private val originalRoot = input("root").asInstanceOf[MonkeyMath]
  private val modifiedInput = input + (originalRoot.name -> MonkeyMath(originalRoot.name, originalRoot.lhs, originalRoot.rhs, '='))

  @tailrec
  private def run(count : Long, attempt : Map[String, Monkey]) : Long = {
    val thisRun = attempt + ("humn" -> MonkeyConst("humn", count))
    val result = doMath(thisRun)

    if (result("root").value() == count) {
      count
    } else {
      run(count + 1, attempt)
    }
  }

  private val p1 = doMath(input)

  override val part1Answer: Long = p1("root").value()
  override val part2Answer: Long = run(partTwoHint, modifiedInput)
}
