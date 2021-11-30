package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

case class Parcel(length: Int, width: Int, height: Int)

class Day02IWasToldThereWouldBeNoMath(filename: String) extends DailyProblem[Int, Int] {
  private val parcels = FileUtilities.readFile(filename, parse)

  private def parse(line: String): Parcel = {
    val bits = line.split("x")
    Parcel(bits(0).toInt, bits(1).toInt, bits(2).toInt)
  }

  private def surfaceArea(p: Parcel): Int = 2 * p.length * p.width + 2 * p.width * p.height + 2 * p.height * p.length

  private def surfaceAreaOfSmallestSide(p: Parcel): Int = Math.min(p.height * p.width, Math.min(p.height * p.length, p.length * p.width))

  private def lengthToWrap(p: Parcel) = Math.min(2 * p.height + 2 * p.width, Math.min(2 * p.height + 2 * p.length, 2 * p.length + 2 * p.width))

  private def lengthOfBow(p: Parcel) = p.width * p.length * p.height

  override val part1Answer: Int = parcels.foldLeft(0)((a, p) => a + surfaceArea(p) + surfaceAreaOfSmallestSide(p))
  override val part2Answer: Int = parcels.foldLeft(0)((a, p) => a + lengthToWrap(p) + lengthOfBow(p))
}
