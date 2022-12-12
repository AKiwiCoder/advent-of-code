package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec


class Day12HillClimbingAlgorithm(filename: String) extends DailyProblem[Int, Int] {
  case class Point(x: Int, y: Int) {
    def up(): Point = Point(x, y -1)
    def down(): Point = Point(x, y+ 1)
    def left(): Point = Point(x-1, y)
    def right(): Point = Point(x+1,y)
  }

  private val input = FileUtilities.readFile(filename).map(_.toCharArray.toList)

  private val startY = input.indexWhere(l => l.contains('S'))
  private val startX = input(startY).indexOf('S')
  private val start = Point(startX, startY)

  private val endY = input.indexWhere(l => l.contains('E'))
  private val endX = input(endY).indexOf('E')
  private val end = Point(endX, endY)

  private val left = 0
  private val right = input.head.size
  private val top = 0
  private val bottom = input.size

  private def canStep(from: Point, to: Point): Boolean = {
     if (to.x >= left && to.x < right && to.y >= top && to.y < bottom) {
       val fromHeight = if (from == start) 'a' else if (from == end) 'z' else input(from.y)(from.x)
       val toHeight =   if (to   == start) 'a' else if (to   == end) 'z' else input(to.y)(to.x)
       fromHeight - toHeight >= -1
     } else {
       false
     }
  }

  @tailrec
  private def findShortestPath(remain : Set[Point], done : Map[Point, Int]) : Int = {
    if (remain.isEmpty) {
      done(end)
    } else {
      val here = remain.head
      val shortest = done(here)

      val (new_remain, new_done) = List(here.up(), here.down(), here.left(), here.right()).foldLeft((remain.tail, done))((acc, new_point) => {
        if (canStep(here, new_point)) {
          val old_dist = acc._2.getOrElse(new_point, Integer.MAX_VALUE)
          val new_dist = shortest + 1

          if (new_dist < old_dist) {
            (acc._1 + new_point, acc._2 + (new_point -> new_dist))
          } else {
            acc
          }
        } else {
          acc
        }
      })

      findShortestPath(new_remain, new_done)
    }
  }

  private val part2starting = input.indices.foldLeft(List[Point]())((acc, y) => input.head.indices.foldLeft(acc)((a, x) => if (input(y)(x) == 'a') Point(x,y) :: a else a)).toSet
  private val part2done = part2starting.map(p => (p -> 0)).toMap

  override val part1Answer: Int = findShortestPath(Set(start), Map(start -> 0))
  override val part2Answer: Int = findShortestPath(part2starting,part2done)
}
