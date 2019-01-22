package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.twenty_fifteen.support._
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day07SomeAssemblyRequired(filename: String) extends DailyProblem[Map[String, Int], Map[String, Int]] {
  private val signalPattern = "([a-z0-9]+) -> ([a-z]+)".r
  private val andPattern = "([a-z0-9]+) AND ([a-z]+) -> ([a-z]+)".r
  private val orPattern = "([a-z0-9]+) OR ([a-z]+) -> ([a-z]+)".r
  private val lshiftPattern = "([a-z]+) LSHIFT ([0-9]+) -> ([a-z]+)".r
  private val rshiftPattern = "([a-z]+) RSHIFT ([0-9]+) -> ([a-z]+)".r
  private val notPattern = "NOT ([a-z]+) -> ([a-z]+)".r

  private val wirePattern = "([a-z]+)".r
  private val constantPattern = "([0-9]+)".r

  private def parse(value: String): Input = {
    value match {
      case wirePattern(name) => WireInput(name)
      case constantPattern(constant) => ConstantInput(constant.toInt)
      case _ => throw new IllegalStateException("Cannot match '" + value + "'")
    }
  }

  private def parser(line: String): LogicOperation =
    line match {
      case signalPattern(in, out) => SignalOperation(parse(in), out)
      case andPattern(lhs, rhs, out) => AndOperation(parse(lhs), parse(rhs), out)
      case orPattern(lhs, rhs, out) => OrOperation(parse(lhs), parse(rhs), out)
      case lshiftPattern(in, count, out) => LShiftOperation(parse(in), count.toInt, out)
      case rshiftPattern(in, count, out) => RShiftOperation(parse(in), count.toInt, out)
      case notPattern(in, out) => NotOperation(parse(in), out)
      case _ => throw new IllegalStateException("Cannot match '" + line + "'")
    }

  private val operations = FileUtilities.readFile(filename, parser)

  @tailrec
  private def evaluate(operations: List[LogicOperation], pending: List[LogicOperation], wires: Map[String, Int]): Map[String, Int] = {
    if (operations.isEmpty && pending.isEmpty) {
      wires
    } else if (operations.isEmpty && pending.nonEmpty) {
      evaluate(pending, List(), wires)
    } else if (operations.head.ready(wires)) {
      evaluate(operations.tail, pending, operations.head.update(wires))
    } else {
      evaluate(operations.tail, operations.head :: pending, wires)
    }
  }

  override val part1Answer: Map[String, Int] = evaluate(operations, List(), Map())
  override val part2Answer: Map[String, Int] = evaluate(operations.filter(operation => operation match {
    case SignalOperation(_, "b") => false
    case _ => true
  }), List(), Map("b" -> part1Answer.getOrElse("a", 0)))
}
