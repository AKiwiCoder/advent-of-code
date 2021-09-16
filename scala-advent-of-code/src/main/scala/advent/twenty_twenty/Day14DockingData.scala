package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day14DockingData(filename: String, skipPart2 : Boolean = false) extends DailyProblem[Long, Long] {

  private val maskPattern = "mask = ([X10]+)".r
  private val memPattern = "mem\\[([0-9]+)\\] = ([0-9]+)".r

  private val input = FileUtilities.readFile(filename)

  private def part1(): Long = {
    def applyMask(mask: List[(Char, Int)], value: Long): Long = {
      mask.foldLeft(0l)((acc, bit) => bit._1 match {
        case 'X' => acc + (value & (1l << bit._2))
        case '1' => acc + (1l << bit._2)
        case '0' => acc
      })
    }

    @tailrec
    def execute(pc: Int, mask: List[(Char, Int)], memory: Map[Long, Long]): Map[Long, Long] = {
      if (pc >= input.length) {
        memory
      } else {
        val line = input(pc)
        line match {
          case maskPattern(m) => execute(pc + 1, m.reverse.zipWithIndex.toList, memory)
          case memPattern(index, value) => execute(pc + 1, mask, memory + (index.toLong -> applyMask(mask, value.toLong)))
        }
      }
    }
    val memory = execute(0, List(), Map())
    memory.values.sum
  }

  private def calculateAddresses(mask: List[(Char, Int)], addr: Long): List[Long] = {

    def applyMask(bit : (Char, Int)) : (Char, Int) = {
      (bit._1 match {
        case '0' => if ((addr & (1l << bit._2)) == 0) '0' else '1'
        case '1' => '1'
        case 'X' => 'X'
      }, bit._2)
    }

    @tailrec
    def enumerate(remaining: List[(Char, Int)], working: List[Long]): List[Long] = {
      if (remaining == Nil) {
        working
      } else {
        val bit = remaining.head
        val newWorking = if (bit._1 == 'X') {
          val a = working.map(work => work + (1l << bit._2))
          a ++ working
        } else if (bit._1 == '1') {
          working.map(work => work + (1l << bit._2))
        } else {
          working
        }
        enumerate(remaining.tail, newWorking)
      }
    }

    val ans = enumerate(mask.map(applyMask(_)), List(0))
    ans
  }

  private def part2(): Long = {
    @tailrec
    def execute(pc: Int, mask: List[(Char, Int)], memory: Map[Long, Long]): Map[Long, Long] = {
      if (pc >= input.length) {
        memory
      } else {
        val line = input(pc)
        line match {
          case maskPattern(m) => execute(pc + 1, m.reverse.zipWithIndex.toList, memory)
          case memPattern(index, value) => execute(pc + 1, mask, calculateAddresses(mask, index.toLong).foldLeft(memory)((mem, addr) => mem + (addr -> value.toLong)))
        }
      }
    }
    val memory = execute(0, List(), Map())
    memory.values.sum
  }

  override val part1Answer: Long = part1()
  override val part2Answer: Long = if (skipPart2) 0 else part2()
}


