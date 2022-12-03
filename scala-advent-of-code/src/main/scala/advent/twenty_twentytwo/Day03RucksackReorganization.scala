package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day03RucksackReorganization(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private val priorities = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

  @tailrec
  private def inBoth( a : Set[Char], b: Set[Char], result : Set[Char]) : Set[Char] = {
    if (a.isEmpty) {
      result
    } else {
      if (b.contains(a.head)) {
        inBoth(a.tail, b, result + a.head)
      } else {
        inBoth(a.tail, b, result)
      }
    }
  }

  private def breakIntoThrees(a : List[Set[Char]], result : List[(Set[Char], Set[Char], Set[Char])]) : List[(Set[Char], Set[Char], Set[Char])] = {
    if (a.isEmpty) {
      result
    } else {
      val b = a.take(3)
      breakIntoThrees(a.drop(3), (b(0), b(1), b(2))::result)
    }
  }

  override val part1Answer: Int = input.map(a => (a.substring(0, a.length / 2), a.substring(a.length / 2))).map(p => inBoth(p._1.toSet, p._2.toSet, Set())).map(s => 1 + priorities.indexOf(s.head)).sum
  override val part2Answer: Int = breakIntoThrees(input.map(a => a.toSet), List()).map(s => inBoth(s._1, inBoth(s._2, s._3, Set()), Set())).map(s => 1 + priorities.indexOf(s.head)).sum
}

