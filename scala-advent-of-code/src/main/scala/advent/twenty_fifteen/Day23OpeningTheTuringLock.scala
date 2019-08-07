package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.twenty_fifteen.support.{HalfRegisterOperation, IncrementRegisterOperation, JumpIfEvenOperation, JumpIfOneOperation, JumpOperation, TripleRegisterOperation, TuringOperation}
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day23OpeningTheTuringLock(filename: String) extends DailyProblem[Map[String, Int], Map[String, Int]] {
  private val hlfPattern = "hlf ([a-z0-9]+)".r
  private val tplPattern = "tpl ([a-z0-9]+)".r
  private val incPattern = "inc ([a-z0-9]+)".r
  private val jmpPattern = "jmp ([+-][0-9]+)".r
  private val jiePattern = "jie ([a-z]+), ([+-][0-9]+)".r
  private val jioPattern = "jio ([a-z]+), ([+-][0-9]+)".r

  private def parser(line: String): TuringOperation =
    line match {
      case hlfPattern(reg) => HalfRegisterOperation(reg)
      case tplPattern(reg) => TripleRegisterOperation(reg)
      case incPattern(reg) => IncrementRegisterOperation(reg)
      case jmpPattern(off) => JumpOperation(off.toInt)
      case jiePattern(reg, off) => JumpIfEvenOperation(reg, off.toInt)
      case jioPattern(reg, off) => JumpIfOneOperation(reg, off.toInt)
      case _ => throw new IllegalStateException(s"Cannot match '$line'")
    }

  private val operations = FileUtilities.readFile(filename, parser)

  @tailrec
  private def execute(registers : Map[String, Int]) : Map[String, Int] = {
      val pc = registers("pc")
      if (pc >= operations.length)
        return registers
      val operation = operations(pc)
      execute(operation.execute(registers))
  }

  override val part1Answer: Map[String, Int] = execute(Map("a" -> 0, "b" -> 0, "pc" -> 0))
  override val part2Answer: Map[String, Int] = execute(Map("a" -> 1, "b" -> 0, "pc" -> 0))
}
