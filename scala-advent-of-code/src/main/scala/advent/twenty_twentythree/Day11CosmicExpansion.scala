package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day11CosmicExpansion(filename : String, expansionConstant : Int) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename)

  def expandRows(todo : List[String], acc : List[String]) : List[String] = {
    if (todo.isEmpty) {
      acc.reverse
    } else {
      val current = todo.head
      val newAcc = current :: (if (current.count(c => c == '.') == current.length) ("." * current.length) :: acc else acc)
      expandRows(todo.tail, newAcc)
    }
  }

  def colNeedsToExpand(map: List[String], x: Int) : Boolean = {
    map.indices.count(y => map(y)(x) == '.') == map.length
  }

  def expandCols(map : List[String], x : Int) : List[String] = {
    if (x >= map.head.length) {
      map
    } else {
      if (colNeedsToExpand(map, x)) {
        val newMap = map.map(line => line.take(x) + "." + line.drop(x))
        expandCols(newMap, x + 2)
      } else {
        expandCols(map, x + 1)
      }
    }
  }

  private val galaxyList = expandCols(expandRows(input, List()), 0)

  private val galaxy = galaxyList.indices.flatMap(y => galaxyList.head.indices.map(x => (y, x) -> galaxyList(y)(x)).toMap).toMap

  private val stars = galaxy.filter(e => e._2 == '#').keys.map(e => (e._1.toLong, e._2.toLong)).toList

  private val pairs = stars.indices.flatMap(lhs => stars.indices.map(rhs => (stars(lhs), stars(rhs)))).toList

  private def manhatten(to : (Long,Long), from : (Long,Long)): Long = {
     Math.abs(to._1 - from._1) + Math.abs(to._2 - from._2)
  }

  @tailrec
  private def calculate(pairs : List[((Long,Long),(Long,Long))], paths : Map[((Long,Long),(Long,Long)), Long]) : Map[((Long,Long),(Long,Long)), Long] = {
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





  private val map_galaxy = input.indices.flatMap(y => input.head.indices.map(x => (y.toLong, x.toLong) -> input(y)(x)).toMap).toMap
  private val map_rowsToExpand = input.indices.filter(y => input(y).count(c => c == '.') == input(y).length)
  private val map_colsToExpand = input.head.indices.filter(x => colNeedsToExpand(input, x))

  private val map_stars = map_galaxy.filter(e => e._2 == '#').keys.toList

  private val map_galaxy_expanded = map_stars.map(e => {
    val rowsToExpand = map_rowsToExpand.count(r => r <= e._1)
    val colsToExpand = map_colsToExpand.count(r => r <= e._2)
    val np = (e._1 + rowsToExpand * (expansionConstant-1), e._2 + colsToExpand * (expansionConstant - 1))
    println(e + " " + np)
    np
  })

  private val map_pairs = map_galaxy_expanded.indices.flatMap(lhs => map_galaxy_expanded.indices.map(rhs => (map_galaxy_expanded(lhs), map_galaxy_expanded(rhs)))).toList

  override val part1Answer: Long = calculate(pairs, Map()).values.sum
  override val part2Answer: Long = calculate(map_pairs, Map()).values.sum
}


