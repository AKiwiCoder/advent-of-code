package advent.twenty_twenty 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day06CustomCustoms(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  @tailrec
  private def parse(remaining : List[String], group : List[Set[Char]], acc : List[List[Set[Char]]]) : List[List[Set[Char]]] = {
    if (remaining == Nil) {
      group :: acc
    } else {
      val line = remaining.head
      if (line.isEmpty) {
        parse(remaining.tail,List(), group :: acc)
      } else {
        parse(remaining.tail, line.toSet :: group, acc)
      }
    }
  }

  private val answers = parse(input, List(), List())

  private val allQuestions = ('a' to 'z').toSet

  override val part1Answer: Int = answers.map(group => group.foldLeft(Set[Char]())((a, acc) => acc ++ a)).map(group => group.size).sum
  override val part2Answer: Int = answers.map(group => group.foldLeft(allQuestions)((a, acc) => acc.intersect(a))).map(group => group.size).sum
}


