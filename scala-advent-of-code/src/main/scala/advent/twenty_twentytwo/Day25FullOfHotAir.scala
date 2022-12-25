  package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec


class Day25FullOfHotAir(filename: String) extends DailyProblem[String, String] {
  private val input = FileUtilities.readFile(filename)

  private def toDecimal(snafu : String) : Long = {
    snafu.toCharArray.foldRight((0L, 1L))((c, acc) => c match {
      case '2' => (acc._1 + 2 * acc._2, acc._2 * 5)
      case '1' =>(acc._1 + 1 * acc._2, acc._2 * 5)
      case '0' =>(acc._1 + 0 * acc._2, acc._2 * 5)
      case '-' =>(acc._1 + -1 * acc._2, acc._2 * 5)
      case '=' =>(acc._1 + -2 * acc._2, acc._2 * 5)
    })._1
  }

  @tailrec
  private def toSnafu(dec: Long, acc : String): String = {
    if (dec == 0) {
      acc
    } else {
      val newDec = dec / 5
      (dec % 5) match {
        case 0 => toSnafu(newDec, "0" + acc)
        case 1 => toSnafu(newDec, "1" + acc)
        case 2 => toSnafu(newDec, "2" + acc)
        case 3 => toSnafu(newDec + 1, "=" + acc)
        case 4 => toSnafu(newDec + 1, "-" + acc)
      }
    }
  }

  private val decimals = input.map(toDecimal)
  private val sum = decimals.sum

  override val part1Answer: String = toSnafu(sum, "")
  override val part2Answer: String = ""
}
