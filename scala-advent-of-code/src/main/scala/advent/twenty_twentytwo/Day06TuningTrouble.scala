package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day06TuningTrouble(filename: String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename).head.toList

  private val search = input.zip(input.tail.zip(input.tail.tail.zip(input.tail.tail.tail))).map(a => List(a._1, a._2._1, a._2._2._1, a._2._2._2))

  @tailrec
  private def search(remain : List[Char], acc : List[Char], unique : Int, index : Int) : Int = {
    if (remain.isEmpty) {
      -1
    } else {
      val h = remain.head
      if (acc.toSet.size == unique) {
        index
      } else {
        if (acc.size == unique) {
          val newAcc = acc.tail ::: h :: Nil
          search(remain.tail, newAcc, unique, index + 1)
        } else {
          val newAcc = acc ::: h :: Nil
          search(remain.tail, newAcc, unique, index + 1)
        }
      }
    }
  }

  override val part1Answer: Int = search(input, List(), 4, 0)
  override val part2Answer: Int = search(input, List(), 14, 0)

}

