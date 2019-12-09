package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

class Day07AmplificationCircuit(filename: String) extends DailyProblem[Long, Long] {
  private val program = IntComputer.loadProgram(filename)

  private def phaseFilter(phase: (Long, Long, Long, Long, Long)): Boolean = {
    Set(phase._1, phase._2, phase._3, phase._4, phase._5).size == 5
  }

  private def doPart1(): Long = {
    val phases = (for (a <- 0 to 4; b <- 0 to 4; c <- 0 to 4; d <- 0 to 4; e <- 0 to 4) yield (a.toLong, b.toLong, c.toLong, d.toLong, e.toLong)).filter(p => phaseFilter(p))
    phases.map(phase => {
      val outa = IntComputer.execute(program, 0, 0,List(phase._1, 0), List())._3.head
      val outb = IntComputer.execute(program, 0, 0,List(phase._2, outa), List())._3.head
      val outc = IntComputer.execute(program, 0, 0,List(phase._3, outb), List())._3.head
      val outd = IntComputer.execute(program, 0, 0,List(phase._4, outc), List())._3.head
      val oute = IntComputer.execute(program, 0, 0,List(phase._5, outd), List())._3.head
      oute
    }).max
  }

  private def doPart2() = {
    def executeInParallel(intA: List[Long], intB: List[Long], intC: List[Long], intD: List[Long], intE: List[Long]): Long = {
      var result = 0L
      var finished = false

      var statea = (program, 0L, intA)
      var stateb = (program, 0L, intB)
      var statec = (program, 0L, intC)
      var stated = (program, 0L, intD)
      var statee = (program, 0L, intE)

      while (!finished) {
        val tempa = if (statea._2 == -1) statea else IntComputer.execute(statea._1, statea._2, 0,statea._3, List())
        val tempb = if (stateb._2 == -1) stateb else IntComputer.execute(stateb._1, stateb._2, 0,stateb._3, List())
        val tempc = if (statec._2 == -1) statec else IntComputer.execute(statec._1, statec._2, 0,statec._3, List())
        val tempd = if (stated._2 == -1) stated else IntComputer.execute(stated._1, stated._2, 0,stated._3, List())
        val tempe = if (statee._2 == -1) statee else IntComputer.execute(statee._1, statee._2, 0,statee._3, List())

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

    val phases = (for (a <- 5 to 9; b <- 5 to 9; c <- 5 to 9; d <- 5 to 9; e <- 5 to 9) yield (a.toLong, b.toLong, c.toLong, d.toLong, e.toLong)).filter(p => phaseFilter(p))
    phases.map(phase => executeInParallel(List(phase._1, 0), List(phase._2), List(phase._3), List(phase._4), List(phase._5))).max
  }

  override val part1Answer: Long = doPart1()
  override val part2Answer: Long = doPart2()
}


