package advent.twenty_twenty 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day05BinaryBoarding(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private def lower( tuple : (Int,Int,Int)) : (Int,Int,Int) = {
    val (min, max, step) = tuple
    (min, max - step, step / 2)
  }

  private def upper( tuple : (Int,Int,Int)) : (Int,Int,Int) = {
    val (min, max, step) = tuple
    (min + step, max, step / 2)
  }

  private def rowId(pass : String) : Int = {
    val rowSteps = pass.substring(0, 7)
    val colSteps = pass.substring(7, 10)

    val row = rowSteps.foldLeft((0, 127, 64))((acc, step) =>  if (step == 'F')  lower(acc) else upper(acc))._1
    val col = colSteps.foldLeft((0, 8, 4))((acc, step) => if (step == 'L')  lower(acc) else upper(acc))._1

   row * 8 + col
  }

  private val rowIds = input.map(pass => rowId(pass))

  private def findFirstMissing(): Int = {
    val seats = input.map(pass => rowId(pass))
    val min = seats.min
    val max = seats.max
    (min to max).filter(id => !seats.contains(id)).head
  }

  override val part1Answer: Int = rowIds.max
  override val part2Answer: Int = findFirstMissing()
}


