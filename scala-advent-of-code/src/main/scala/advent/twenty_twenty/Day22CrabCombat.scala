package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day22CrabCombat(filename: String, runPart1 : Boolean = true) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private val playerOne = input.takeWhile(_.nonEmpty).drop(1).map(_.toInt)
  private val playerTwo = input.dropWhile(_.nonEmpty).drop(2).map(_.toInt)

  private def part1(): Int = {
    @tailrec
    def run(d1: List[Int], d2: List[Int]): List[Int] = {
      if (d1.isEmpty) {
        d2
      } else if (d2.isEmpty) {
        d1
      } else {
        val h1 = d1.head
        val h2 = d2.head
        val new1 = if (h1 > h2) d1.tail :+ h1 :+ h2 else d1.tail
        val new2 = if (h2 > h1) d2.tail :+ h2 :+ h1 else d2.tail
        run(new1, new2)
      }
    }
    run(playerOne, playerTwo).reverse.zipWithIndex.map(e => e._1 * (e._2 + 1)).sum
  }

  private def part2(): Int = {
    def run(d1: List[Int], d2: List[Int], d1mem: Set[List[Int]], d2mem : Set[List[Int]]): (Boolean, List[Int]) = {
      if (d1.isEmpty) {
        (false, d2)
      } else if (d2.isEmpty) {
        (true, d1)
      } else if (d1mem.contains(d1) || d2mem.contains(d2)) {
        (true, d1)
      } else {
        val h1 = d1.head
        val h2 = d2.head

        if (h1 <= d1.length - 1 && h2 <= d2.length -1) {
          val newd1 = d1.tail.take(h1)
          val newd2 = d2.tail.take(h2)
          val subgame = run(newd1, newd2, Set(), Set())

          val new1 = if (subgame._1) d1.tail :+ h1 :+ h2 else d1.tail
          val new2 = if (!subgame._1) d2.tail :+ h2 :+ h1 else d2.tail

          run(new1, new2, d1mem+ d1, d2mem + d2)
        } else {
          val new1 = if (h1 > h2) d1.tail :+ h1 :+ h2 else d1.tail
          val new2 = if (h2 > h1) d2.tail :+ h2 :+ h1 else d2.tail
          run(new1, new2,  d1mem+ d1, d2mem + d2)
        }
      }
    }
   run(playerOne, playerTwo, Set(), Set())._2.reverse.zipWithIndex.map(e => e._1 * (e._2 + 1)).sum
  }

  override val part1Answer: Int = if (runPart1) part1() else -1
  override val part2Answer: Int = part2()
}


