package advent.twenty_nineteen 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day06UniversalOrbitMap(filename : String) extends DailyProblem[Int, Int] {
  private def parser(line : String) : (String,String) = {
    val arr = line.split("\\)")
    (arr(0), arr(1))
  }

  private val orbitBodyMap = FileUtilities.readFile(filename, parser).map(e => e._2 -> e._1).toMap

  @tailrec
  private def generatePath(length : Int, current : String, acc : List[String]): List[String] = {
    if (current == "COM") {
      acc
    } else {
      generatePath(length + 1, orbitBodyMap(current), current :: acc)
    }
  }

  private val paths = orbitBodyMap.keySet.map(p => p->generatePath(0, p, List())).toMap

  private val youPath = paths("YOU")
  private val sanPath = paths("SAN")
  private val commonPath = youPath.zip(sanPath).count(node => node._1 == node._2)

  override val part1Answer: Int = paths.map(path => path._2.length).sum
  override val part2Answer: Int = youPath.length + sanPath.length - 2 * commonPath - 2
}


