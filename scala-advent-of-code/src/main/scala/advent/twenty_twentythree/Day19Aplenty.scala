package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day19Aplenty(filename: String) extends DailyProblem[Int, Long] {

  case class Part(x: Int, m: Int, a: Int, s: Int)

  case class Rule(name: String, actions: List[Action])

  private val input = FileUtilities.readFile(filename)

  private val rulePattern = "([a-z]+)\\{([^}]+)}".r

  private val partPattern = "\\{x=([0-9]+),m=([0-9]+),a=([0-9]+),s=([0-9]+)}".r

  sealed trait Action {
    def doesApply(part: Part): Boolean

    def target(): String
  }

  case class DefaultAction(target: String) extends Action {
    override def doesApply(part: Part): Boolean = true
  }

  case class CompareAction(parameter: String, isLessThan: Boolean, value: Int, target: String) extends Action {
    override def doesApply(part: Part): Boolean = {
      parameter match {
        case "x" => if (isLessThan) part.x < value else part.x > value
        case "m" => if (isLessThan) part.m < value else part.m > value
        case "a" => if (isLessThan) part.a < value else part.a > value
        case "s" => if (isLessThan) part.s < value else part.s > value
      }
    }

    def doesApply(part: PartRange): Boolean = {
      parameter match {
        case "x" => if (isLessThan) part.x._1 < value else part.x._2 > value
        case "m" => if (isLessThan) part.m._1 < value else part.m._2 > value
        case "a" => if (isLessThan) part.a._1 < value else part.a._2 > value
        case "s" => if (isLessThan) part.s._1 < value else part.s._2 > value
      }
    }
  }

  private val comparePattern = "([xmas])([<>])([0-9]+):([ARa-z]+)".r
  private val defaultPattern = "([ARa-z]+)".r

  private def parseAction(action: String): Action = {
    action match {
      case comparePattern(p, a, v, t) => CompareAction(p, a.equals("<"), v.toInt, t)
      case defaultPattern(t) => DefaultAction(t)
    }
  }

  private def parseRule(line: String): Rule = {
    line match {
      case rulePattern(n, a) => Rule(n, a.split(",").toList.map(parseAction))
    }
  }

  private def parsePart(line: String): Part = {
    line match {
      case partPattern(x, m, a, s) => Part(x.toInt, m.toInt, a.toInt, s.toInt)
    }
  }

  private val rules = input.takeWhile(l => l.nonEmpty).map(parseRule).map(r => r.name -> r).toMap
  private val parts = input.dropWhile(_.trim().nonEmpty).drop(1).map(parsePart)

  @tailrec
  private def process(part: Part, workflow: String): Option[Part] = {
    if (workflow.equals("A")) {
      Some(part)
    } else if (workflow.equals("R")) {
      None
    } else {
      val rule = rules(workflow).actions.find(p => p.doesApply(part)).get
      process(part, rule.target())
    }
  }

  case class PartRange(x: (Int, Int), m: (Int, Int), a: (Int, Int), s: (Int, Int)) {
    def score(): Long = {
      val xs = (1 + x._2 - x._1).toLong
      val ms = (1 + m._2 - m._1).toLong
      val as = (1 + a._2 - a._1).toLong
      val ss = (1 + s._2 - s._1).toLong
      xs * ms * as * ss
    }

    def isValid: Boolean = {
      x._2 > x._1 && m._2 > m._1 && a._2 > a._1 && s._2 > s._1
    }
  }

  private def modify(p: (Int, Int), value: Int, isLessThan: Boolean): (Int, Int) = {
    if (isLessThan) {
      (if (p._1 < value) p._1 else Integer.MAX_VALUE, if (p._2 < value) p._2 else value - 1)
    } else {
      (if (p._1 > value) p._1 else value + 1, if (p._2 < value) Integer.MIN_VALUE else p._2)
    }
  }

  private def modifyInverted(p: (Int, Int), value: Int, isLessThan: Boolean): (Int, Int) = {
    if (isLessThan) {
      (if (p._1 < value) p._1 else Integer.MAX_VALUE, if (p._2 < value) p._2 else value)
    } else {
      (if (p._1 > value) p._1 else value, if (p._2 < value) Integer.MIN_VALUE else p._2)
    }
  }

  @tailrec
  private def processPart2(parts: List[(String, PartRange)], acc: List[PartRange]): List[PartRange] = {
    if (parts.isEmpty) {
      acc
    } else {
      val current = parts.head

      val (workflow, part) = current

      if (workflow.equals("A")) {
        processPart2(parts.tail, part :: acc)
      } else if (workflow.equals("R")) {
        processPart2(parts.tail, acc)
      } else {
        val nextSteps = rules(workflow).actions.foldLeft((List[(String, PartRange)](), part))((acc, action) => {
          val prevPart = acc._2
          val (newTarget: String, newPart: PartRange, passToNextStage: PartRange) = action match {
            case d: DefaultAction => (d.target, acc._2, acc._2)
            case c: CompareAction =>
              c.parameter match {
                case "x" => (c.target, PartRange(modify(prevPart.x, c.value, c.isLessThan), prevPart.m, prevPart.a, prevPart.s), PartRange(modifyInverted(prevPart.x, c.value, !c.isLessThan), prevPart.m, prevPart.a, prevPart.s))
                case "m" => (c.target, PartRange(prevPart.x, modify(prevPart.m, c.value, c.isLessThan), prevPart.a, prevPart.s), PartRange(prevPart.x, modifyInverted(prevPart.m, c.value, !c.isLessThan), prevPart.a, prevPart.s))
                case "a" => (c.target, PartRange(prevPart.x, prevPart.m, modify(prevPart.a, c.value, c.isLessThan), prevPart.s), PartRange(prevPart.x, prevPart.m, modifyInverted(prevPart.a, c.value, !c.isLessThan), prevPart.s))
                case "s" => (c.target, PartRange(prevPart.x, prevPart.m, prevPart.a, modify(prevPart.s, c.value, c.isLessThan)), PartRange(prevPart.x, prevPart.m, prevPart.a, modifyInverted(prevPart.s, c.value, !c.isLessThan)))
              }
          }
          ((newTarget, newPart) :: acc._1, passToNextStage)
        })

        val validNextSteps = nextSteps._1.filter( p => p._2.isValid)
        processPart2(validNextSteps ::: parts.tail, acc)
      }
    }
  }

  private val acceptable_parts = processPart2(List(("in", PartRange((1, 4000), (1, 4000), (1, 4000), (1, 4000)))), List())

  override val part1Answer: Int = parts.map(p => process(p, "in")).map {
    case Some(p) => p.x + p.m + p.a + p.s
    case None => 0
  }.sum
  override val part2Answer: Long = acceptable_parts.foldLeft(0L)((acc, part) => acc + part.score())
}


