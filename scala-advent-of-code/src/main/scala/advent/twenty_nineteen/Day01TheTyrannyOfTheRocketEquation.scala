package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities._

import scala.annotation.tailrec

class Day01TheTyrannyOfTheRocketEquation(filename: String) extends DailyProblem[Int, Int] {
  private val masses = FileUtilities.readFile(filename, line => line.toInt)

  private def calculateFuel(mass: Int): Int = (Math.round(Math.floor(mass / 3)) - 2).toInt

  @tailrec
  private def calculateFuel(soFar: Int, mass: Int): Int = {
    val fuel = calculateFuel(mass)
    if (fuel < 0) {
      soFar
    } else {
      calculateFuel(soFar + fuel, fuel)
    }
  }

  override val part1Answer: Int = masses.map(mass => calculateFuel(mass)).sum
  override val part2Answer: Int = masses.map(mass => calculateFuel(0, mass)).sum
}
