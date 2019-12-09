package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities._

class Day02TwelveOhTwoProgramAlarm(filename: String, part1 : (Long, Long), part2Target: Option[Long]) extends DailyProblem[Map[Long,Long], Long] {
  private val program = IntComputer.loadProgram(filename)

  private def execute(memory: Map[Long, Long], noun : Long, verb: Long): Map[Long, Long] = {
    IntComputer.execute(memory + (1L -> noun) + (2L -> verb), 0, 0,  List(), List()).memory
  }

  def execute(target : Long): Long = {
    for (noun <- 0 to 99)
      for (verb <- 0 to 99) {
        if (execute(program, noun, verb)(0) == target) {
          return 100  * noun + verb
        }
      }
    0
  }

  override val part1Answer: Map[Long,Long] = execute(program, part1._1, part1._2)
  override val part2Answer: Long = if (part2Target.isDefined) execute(part2Target.get) else 0
}
