package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day08HauntedWasteland(filename : String, doPart1 : Boolean, doPart2 : Boolean) extends DailyProblem[Int, Long] {

  private val input = FileUtilities.readFile(filename)

  private val steps = input.head.toList

  def parse(line : String) : (String, String, String) = {
    val equalsSplit = line.split("=")
    val name = equalsSplit(0).trim
    val clean = equalsSplit(1).trim
    val nextSplit = clean.substring(1,clean.length-1).split(",")
    val left = nextSplit(0).trim
    val right = nextSplit(1).trim
    (name, left, right)
  }

  private val paths = input.drop(2).map(parse).map(t => t._1 -> (t._2, t._3)).toMap

  @tailrec
  private def count(path : List[Char], current : String, acc : Int): Int = {
    if (current.endsWith("Z")) {
      acc
    } else if (path.isEmpty) {
      count(steps, current, acc)
    } else {
      val newCurrent = if (path.head == 'L') {
        paths(current)._1
      } else {
        paths(current)._2
      }
      count(path.tail, newCurrent, acc + 1)
    }
  }

  private val ghostStarts = paths.keys.filter(name => name.endsWith("A")).toList

  private def leastCommonMultiple(list: Seq[Long]): Long =
    list.foldLeft(1: Long) {
      (a, b) => b * a / Stream.iterate((a, b)) { case (x, y) => (y, x % y) }.dropWhile(_._2 != 0).head._1.abs
    }

  def ghosts() : Long = {
    val ghostCounts = ghostStarts.map(start => count(steps, start, 0).toLong)
    leastCommonMultiple(ghostCounts)
  }

  override val part1Answer: Int = if (doPart1) count(steps, "AAA", 0) else 0
  override val part2Answer: Long = if (doPart2) ghosts() else 0
}


