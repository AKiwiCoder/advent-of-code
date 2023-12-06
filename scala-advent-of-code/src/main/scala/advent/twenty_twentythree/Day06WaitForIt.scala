package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day06WaitForIt(filename : String) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename)

  private val times = input(0).split(":")(1).trim.split(" +").map(_.toLong).toList
  private val recordDistances = input(1).split(":")(1).trim.split(" +").map(_.toLong).toList

  private val raceParameters = times.zip(recordDistances)

  private def countRecordBeatingOptions(parameters : (Long, Long)) : Long = {
    val (raceTime, recordDistance) = parameters

    @tailrec
    def iterate(buttonHeldTime : Long, acc : Long) : Long = {
       if (buttonHeldTime >= raceTime) {
         acc
       } else {
         if ((raceTime - buttonHeldTime) * buttonHeldTime > recordDistance) {
           iterate(buttonHeldTime + 1, acc + 1)
         } else {
           iterate(buttonHeldTime + 1, acc)
         }
       }
    }

    iterate(1, 0)
  }

  private val racePartTwo = times.map(_.toString).mkString.toLong
  private val distancePartTwo = recordDistances.map(_.toString).mkString.toLong

  override val part1Answer: Long = raceParameters.map(p => countRecordBeatingOptions(p)).product
  override val part2Answer: Long = countRecordBeatingOptions((racePartTwo, distancePartTwo))
}


