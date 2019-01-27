package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day10ElvesLookElvesSay(input: String) extends DailyProblem[Int, Int] {
  @tailrec
  private def walkString(current : Char, count : Int, remaining : List[Char], accumulator : List[(Int,Char)]) : List[(Int, Char)] = {
    if (remaining.isEmpty) {
      (count, current) :: accumulator
    } else if (remaining.head == current) {
        walkString(current, count + 1, remaining.tail, accumulator)
    } else {
      walkString(remaining.head, 1, remaining.tail, (count, current) :: accumulator)
    }
  }

  @tailrec
  private def lookSay(current : List[Char], count : Int, target : Int, accumulator: Map[Int,Int]) : Map[Int,Int] = {
    if (count > target) {
      accumulator
    } else {
      val next = walkString(current.head, 1, current.tail, List()).foldLeft(List[Char]())((a, p) => p._1.toString.charAt(0) :: p._2.toChar :: a)
      lookSay(next, count + 1, target, accumulator + (count -> current.length))
    }
  }

  private val lengths = lookSay(input.toCharArray.toList, 0, 50, Map())

  override val part1Answer: Int = lengths(40)
  override val part2Answer: Int = lengths(50)
}
