package advent.twenty_nineteen

import advent.common.DailyProblem

class Day07AmplificationCircuit(filename: String) extends DailyProblem[Long, Long] {
  private val program = IntComputer.loadProgram(filename)

  private def phaseFilter(phase: (Long, Long, Long, Long, Long)): Boolean = {
    Set(phase._1, phase._2, phase._3, phase._4, phase._5).size == 5
  }

  private def doPart1(): Long = {
    val phases = (for (a <- 0 to 4; b <- 0 to 4; c <- 0 to 4; d <- 0 to 4; e <- 0 to 4) yield (a.toLong, b.toLong, c.toLong, d.toLong, e.toLong)).filter(p => phaseFilter(p))
    phases.map(phase => {
      val outa = IntComputer.execute(IntComputerState(program, 0, 0, List(phase._1, 0), List())).output.head
      val outb = IntComputer.execute(IntComputerState(program, 0, 0, List(phase._2, outa), List())).output.head
      val outc = IntComputer.execute(IntComputerState(program, 0, 0, List(phase._3, outb), List())).output.head
      val outd = IntComputer.execute(IntComputerState(program, 0, 0, List(phase._4, outc), List())).output.head
      val oute = IntComputer.execute(IntComputerState(program, 0, 0, List(phase._5, outd), List())).output.head
      oute
    }).max
  }

  private def doPart2() = {
    def executeInParallel(intA: List[Long], intB: List[Long], intC: List[Long], intD: List[Long], intE: List[Long]): Long = {
      var result = 0L
      var finished = false

      var stateA = IntComputerState(program, 0L, 0L, intA, List())
      var stateB = IntComputerState(program, 0L, 0L, intB, List())
      var stateC = IntComputerState(program, 0L, 0L, intC, List())
      var stateD = IntComputerState(program, 0L, 0L, intD, List())
      var stateE = IntComputerState(program, 0L, 0L, intE, List())

      while (!finished) {
        val workingA = if (stateA.isFinished()) stateA else IntComputer.execute(stateA)
        val workingB = if (stateB.isFinished()) stateB else IntComputer.execute(stateB)
        val workingC = if (stateC.isFinished()) stateC else IntComputer.execute(stateC)
        val workingD = if (stateD.isFinished()) stateD else IntComputer.execute(stateD)
        val workingE = if (stateE.isFinished()) stateE else IntComputer.execute(stateE)

        if (workingE.isFinished()) {
          finished = true
          result = workingE.output.head
        } else {
          stateA = IntComputerState(workingA.memory, workingA.pc, workingA.relativeBase, workingE.output, List())
          stateB = IntComputerState(workingB.memory, workingB.pc, workingB.relativeBase, workingA.output, List())
          stateC = IntComputerState(workingC.memory, workingC.pc, workingC.relativeBase, workingB.output, List())
          stateD = IntComputerState(workingD.memory, workingD.pc, workingD.relativeBase, workingC.output, List())
          stateE = IntComputerState(workingE.memory, workingE.pc, workingE.relativeBase, workingD.output, List())
        }
      }
      result
    }

    val phases = (for (a <- 5 to 9; b <- 5 to 9; c <- 5 to 9; d <- 5 to 9; e <- 5 to 9) yield (a.toLong, b.toLong, c.toLong, d.toLong, e.toLong)).filter(p => phaseFilter(p))
    phases.map(phase => executeInParallel(List(phase._1, 0), List(phase._2), List(phase._3), List(phase._4), List(phase._5))).max
  }

  override val part1Answer: Long = doPart1()
  override val part2Answer: Long = doPart2()
}


