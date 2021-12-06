package advent.twenty_twentyone 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day06Lanternfish(filename : String) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename).head.split(',').map(_.toLong).groupBy(identity).view.mapValues(_.length.toLong).toMap

  private def breed(days : Long) : Long = {
    @tailrec
    def cycle(day : Long, population : Map[Long, Long]) : Map[Long,Long] = {
      if (day == 0) {
         population
      } else {
        val newPop = population.keys.foldLeft(Map[Long,Long]())((acc, key) => {
          val newKey = key - 1
          if (newKey == -1) {
            (acc + (6L -> (population(key) + acc.getOrElse(6, 0L)))) + (8L -> (population(key) + acc.getOrElse(8, 0L)))
          } else {
            acc + (newKey -> (population(key) + acc.getOrElse(newKey, 0L)))
          }
        })
        cycle(day - 1, newPop)
      }
    }

    cycle(days, input).values.sum
  }

  override val part1Answer: Long = breed(80)
  override val part2Answer: Long = breed(256)
}


