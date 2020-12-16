package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day15RambunctiousRecitation(filename: String) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename)(0).split(",").zipWithIndex
  private val turnMap = input.map(entry => entry._1.toLong -> ((entry._2 + 1l).toLong, -1l)).toMap

  private def part1(limit : Long): Long = {
    @tailrec
    def talk(turn: Long, lastSpoken: Long, memory: Map[Long, (Long, Long)]): Long = {
      if (turn > limit) {
        lastSpoken
      } else {
        val lastSpokenMemory = memory.getOrElse(lastSpoken, (-1l, -1l))
        val nowSaying = if (lastSpokenMemory._2 == -1) 0 else lastSpokenMemory._1 - lastSpokenMemory._2
        val nowSayingMemory = memory.getOrElse(nowSaying, (-1l, -1l))
        talk(turn + 1, nowSaying, memory + (nowSaying -> (turn, nowSayingMemory._1)))
      }
    }

    talk(input.size+1, input.last._1.toLong, turnMap)
  }

  override val part1Answer: Long = part1(2020)
  override val part2Answer: Long = part1(30000000)
}


