package advent.twenty_twenty 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day13ShuttleSearch(filename : String, part2Start : Long) extends DailyProblem[Int, Long] {

  private val input = FileUtilities.readFile(filename)

  private val startingTime = input(0).toInt
  private val busIds = input(1).split(",")

  private def part1() : Int = {
    val validIds = busIds.filter(a => !a.equals("x")).map(_.toInt)
    val nextBus = validIds.map(id => (id, (id * (1 + startingTime/id)) - startingTime)).minBy(_._2)
    nextBus._1 * nextBus._2
  }

  /*
  * https://en.wikipedia.org/wiki/Chinese_remainder_theorem
  */
  private def part2() : Long = {
    @tailrec
    def calc(ids : List[(String, Int)], timestamp : Long, step : Long) : Long = {
      if (ids == Nil) {
        timestamp
      } else {
        val id = ids.head
          val busId = id._1.toLong
          val busIndex = id._2
          if ((timestamp + busIndex) % busId == 0) {
            calc(ids.tail, timestamp, step * busId)
          } else {
            calc(ids, timestamp + step, step)
          }
      }
    }
    calc(busIds.zipWithIndex.filter(a => !a._1.equals("x")).toList, part2Start, 1)
  }

  override val part1Answer: Int = part1()
  override val part2Answer: Long = part2()
}


