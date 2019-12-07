package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day07AmplificationCircuit(filename: String) extends DailyProblem[Int, Int] {

  private val program = FileUtilities.readFile(filename)(0).split(",").zipWithIndex.map(t => t._2 -> t._1.toInt).toMap

  private def get(index: Int, mode: Int, memory: Map[Int, Int]): Int = {
    if (mode == 0) memory(memory(index)) else memory(index)
  }

  private def phaseFilter(phase: (Int, Int, Int, Int, Int)): Boolean = {
    Set(phase._1, phase._2, phase._3, phase._4, phase._5).size == 5
  }

  @tailrec
  private def execute(memory: Map[Int, Int], pc: Int, input: List[Int], output: List[Int]): (Map[Int, Int], Int, List[Int]) = {
    if (memory(pc) == 99) {
      // Finished All Done
      (memory, -1, output)
    } else {
      val opcode = memory(pc)

      val operation = opcode % 100
      val mode1 = opcode / 100 % 10
      val mode2 = opcode / 1000 % 10

      if (operation == 3 && input.size == 0) {
        // Pending Input
        (memory, pc, output)
      } else {

        val (value, newOutput) = operation match {
          case 1 => (get(pc + 3, 1, memory) -> (get(pc + 1, mode1, memory) + get(pc + 2, mode2, memory)), output)
          case 2 => (get(pc + 3, 1, memory) -> (get(pc + 1, mode1, memory) * get(pc + 2, mode2, memory)), output)
          case 3 => (get(pc + 1, 1, memory) -> input.head, output)
          case 4 => (-1 -> -1, get(pc + 1, 0, memory) :: output)
          case 5 => (-1 -> -1, output)
          case 6 => (-1 -> -1, output)
          case 7 => (get(pc + 3, 1, memory) -> (if (get(pc + 1, mode1, memory) < get(pc + 2, mode2, memory)) 1 else 0), output)
          case 8 => (get(pc + 3, 1, memory) -> (if (get(pc + 1, mode1, memory) == get(pc + 2, mode2, memory)) 1 else 0), output)
          case _ => throw new IllegalArgumentException("Unknown OP Code " + memory(pc) + " " + pc)
        }

        val newPc = operation match {
          case 1 => pc + 4
          case 2 => pc + 4
          case 3 => pc + 2
          case 4 => pc + 2
          case 5 => if (get(pc + 1, mode1, memory) != 0) get(pc + 2, mode2, memory) else pc + 3
          case 6 => if (get(pc + 1, mode1, memory) == 0) get(pc + 2, mode2, memory) else pc + 3
          case 7 => pc + 4
          case 8 => pc + 4
          case _ => throw new IllegalArgumentException("Unknown OP Code " + memory(pc) + " " + pc)
        }

        execute(memory + value, newPc, if (operation == 3) input.tail else input, newOutput)
      }
    }
  }

  private def doPart1(): Int = {
    val phases = (for (a <- 0 to 4; b <- 0 to 4; c <- 0 to 4; d <- 0 to 4; e <- 0 to 4) yield (a, b, c, d, e)).filter(p => phaseFilter(p))
    phases.map(phase => {
      val outa = execute(program, 0, List(phase._1, 0), List())._3.head
      val outb = execute(program, 0, List(phase._2, outa), List())._3.head
      val outc = execute(program, 0, List(phase._3, outb), List())._3.head
      val outd = execute(program, 0, List(phase._4, outc), List())._3.head
      val oute = execute(program, 0, List(phase._5, outd), List())._3.head
      oute
    }).max
  }

  private def doPart2() = {
    def executeInParallel(intA: List[Int], intB: List[Int], intC: List[Int], intD: List[Int], intE: List[Int]): Int = {
      var result = 0
      var finished = false

      var statea = (program, 0, intA)
      var stateb = (program, 0, intB)
      var statec = (program, 0, intC)
      var stated = (program, 0, intD)
      var statee = (program, 0, intE)

      while (!finished) {
        val tempa = if (statea._2 == -1) statea else execute(statea._1, statea._2, statea._3, List())
        val tempb = if (stateb._2 == -1) stateb else execute(stateb._1, stateb._2, stateb._3, List())
        val tempc = if (statec._2 == -1) statec else execute(statec._1, statec._2, statec._3, List())
        val tempd = if (stated._2 == -1) stated else execute(stated._1, stated._2, stated._3, List())
        val tempe = if (statee._2 == -1) statee else execute(statee._1, statee._2, statee._3, List())

        if (tempe._2 == -1) {
          finished = true
          result = tempe._3.head
        } else {
          statea = (tempa._1, tempa._2, tempe._3)
          stateb = (tempb._1, tempb._2, tempa._3)
          statec = (tempc._1, tempc._2, tempb._3)
          stated = (tempd._1, tempd._2, tempc._3)
          statee = (tempe._1, tempe._2, tempd._3)
        }
      }
      result
    }

    val phases = (for (a <- 5 to 9; b <- 5 to 9; c <- 5 to 9; d <- 5 to 9; e <- 5 to 9) yield (a, b, c, d, e)).filter(p => phaseFilter(p))
    phases.map(phase => executeInParallel(List(phase._1, 0), List(phase._2), List(phase._3), List(phase._4), List(phase._5))).max
  }

  override val part1Answer: Int = doPart1()
  override val part2Answer: Int = doPart2()
}


