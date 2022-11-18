package advent.twenty_twentyone 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.Array.ofDim
import scala.annotation.tailrec

class Day03BinaryDiagnostic(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private val length = input.head.length

  def part1(): Int= {
    @tailrec
     def walk(index : Int, acc : Int, compare :  (Int, Int) => Boolean ): Int = {
       if (index >= length) {
         acc
       } else {
         val zeros = input.count(p => p(index) == '0')
         val ones = input.length - zeros
         walk(index + 1, if (compare(zeros, ones)) acc * 2 + 1 else acc * 2, compare)
       }
     }
    walk(0, 0, (z, o) => z > o) * walk(0, 0, (z, o) => z < o)
  }

  def part2(): Int = {
    @tailrec
    def walk(current : List[String], index : Int, compare :  (Int, Int, Char) => Boolean ): Int = {
      println(current)
      if (current.size == 1 || index >= length) {
        current.head.foldLeft(0)((acc, c) => if (c == '1') acc * 2 + 1 else acc * 2)
      } else {
        val zeros = current.count(p => p(index) == '0')
        val ones = current.length - zeros
        println(zeros + " " + ones)
        walk(current.filter( p => compare(zeros, ones, p(index))), index + 1, compare)
      }
    }
     walk(input, 0,  (z, o, c) => (o >= z && c == '1') || (z > o && c == '0')) *
      walk(input, 0, (z, o, c) => (z > o && c == '1') || (z <= o && c == '0'))
  }

  override val part1Answer: Int = part1()
  override val part2Answer: Int = part2()
}


