package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day15RambunctiousRecitation(filename: String) extends DailyProblem[Long, Long] {
  private val input = FileUtilities.readFile(filename)(0).split(",").map(_.toLong).zipWithIndex

  private def part1(limit : Long): Long = {
    @tailrec
    def talk(turn: Long, lastSpoken: Long, memory: Map[Long, Long]): Long = {
      if (turn == limit) {
        lastSpoken
      } else {
        val lastSpokenMemory = memory.get(lastSpoken)
        val nowSaying = lastSpokenMemory match {
          case Some(last) => turn - last
          case None => 0
        }
        talk(turn + 1, nowSaying, memory + (lastSpoken -> turn))
      }
    }

    val lastSpoken = input.last._1;
    val newInput = input.dropRight(1).map(entry => entry._1 -> (entry._2 + 1l)).toMap
    talk(input.length, lastSpoken, newInput)
  }

  override val part1Answer: Long = part1(2020)
  override val part2Answer: Long = part1(30000000)
}


