package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

object Instruction {

  val noop = "noop".r
  val addx = "addx ([-]*[0-9]+)".r
  def parse(line : String) : Instruction = {
    line match {
      case noop() => NoOperation()
      case addx(v) => AddX(v.toInt)
    }
  }
}

abstract class Instruction {
  def execute(list : List[Int]) : List[Int]
}

case class NoOperation() extends Instruction {
  override def execute(list: List[Int]): List[Int] = {
    val prev = list.head
    prev :: list
  }
}

case class AddX(value : Int) extends Instruction {
  override def execute(list: List[Int]): List[Int] = {
    val prev = list.head
    (prev+value) :: (prev :: list)
  }
}

class Day10CathodeRayTube(filename: String) extends DailyProblem[Int, String] {
  private val input = FileUtilities.readFile(filename, Instruction.parse)

  val part1 = input.foldLeft(List(1))((acc, instruction) => instruction.execute(acc)).reverse
  val part2 = (0 until 240).foldLeft("")((acc, idx) => if (Math.abs(part1(idx)  - idx % 40)  <= 1) acc + '#' else acc + ".")

  override val part1Answer: Int = List(20,60,100,140,180,220).foldLeft(0)((acc, cycle) => acc + cycle * part1(cycle-1))
  override val part2Answer: String = part2.grouped(40).toList.foldLeft("")((acc, line) => acc + line + "\n")
}

