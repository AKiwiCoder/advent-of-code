package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

class Day09AllInASingleNight(filename: String) extends DailyProblem[Int, Int] {
  private val routePattern = "([A-Za-z0-9]+) to ([A-Za-z0-9]+) = ([0-9]+)".r

  private def parse(line: String): Map[(String, String), Int] = {
    line match {
      case routePattern(to, from, distance) => Map((to, from) -> distance.toInt, (from, to) -> distance.toInt)
      case _ => throw new IllegalArgumentException(s"Cannot parse '$line'")
    }
  }

  def calculate(entry: List[String]): Int = {
    val steps = entry.take(entry.size - 1).zip(entry.tail)
    steps.foldLeft(0)((acc, step) => acc + routeMap(step._1, step._2))
  }

  private val routeMap = FileUtilities.readFile(filename, parse).foldLeft(Map[(String, String), Int]())((acc, map) => acc ++ map)

  private val routes = routeMap.keySet.map(key => key._1).toList.permutations.toList

  private val distances = routes.map(entry => calculate(entry))

  override val part1Answer: Int = distances.min
  override val part2Answer: Int = distances.max
}
