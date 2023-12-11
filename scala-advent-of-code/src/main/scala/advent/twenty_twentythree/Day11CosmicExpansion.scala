package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day11CosmicExpansion(filename: String, expansionConstant: Int) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename)

  private def colNeedsToExpand(map: List[String], x: Int): Boolean = {
    map.indices.count(y => map(y)(x) == '.') == map.length
  }

  private def rowNeedsToExpand(y: Int): Boolean = {
    input(y).count(c => c == '.') == input(y).length
  }

  private def manhatten(to: (Long, Long), from: (Long, Long)): Long = {
    Math.abs(to._1 - from._1) + Math.abs(to._2 - from._2)
  }

  @tailrec
  private def calculate(pairs: List[((Long, Long), (Long, Long))], paths: Map[((Long, Long), (Long, Long)), Long]): Map[((Long, Long), (Long, Long)), Long] = {
    if (pairs.isEmpty) {
      paths
    } else {
      val (from, to) = pairs.head
      val newPaths = if (paths.contains((from, to)) || paths.contains((to, from))) {
        paths
      } else {
        paths + ((from, to) -> manhatten(from, to))
      }
      calculate(pairs.tail, newPaths)
    }
  }


  private def build_map(expansion: Int): List[((Long, Long), (Long, Long))] = {
    val map_galaxy = input.indices.flatMap(y => input.head.indices.map(x => (y.toLong, x.toLong) -> input(y)(x)).toMap).toMap
    val map_rowsToExpand = input.indices.filter(y => rowNeedsToExpand(y))
    val map_colsToExpand = input.head.indices.filter(x => colNeedsToExpand(input, x))

    val map_stars = map_galaxy.filter(e => e._2 == '#').keys.toList

    val map_galaxy_expanded = map_stars.map(e => {
      val numRowsToExpand = map_rowsToExpand.count(r => r <= e._1)
      val numColsToExpand = map_colsToExpand.count(r => r <= e._2)
      val np = (e._1 + numRowsToExpand * (expansion - 1), e._2 + numColsToExpand * (expansion - 1))
      println(e + " " + np)
      np
    })

    map_galaxy_expanded.indices.flatMap(lhs => map_galaxy_expanded.indices.map(rhs => (map_galaxy_expanded(lhs), map_galaxy_expanded(rhs)))).toList
  }

  override val part1Answer: Long = calculate(build_map(2), Map()).values.sum
  override val part2Answer: Long = calculate(build_map(expansionConstant), Map()).values.sum
}


