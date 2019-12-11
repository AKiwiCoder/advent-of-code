package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point}

import scala.annotation.tailrec

class Day10MonitoringStation(filename: String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename)

  private val asteroidLocations = input.zipWithIndex.flatMap(entry => entry._1.toList.zipWithIndex.filter(d => d._1 == '#').map(e => (e._2, entry._2, e._1))).map(a => Point(a._2, a._1))

  private def getVisibleAsteroids(base: Point, asteroids: List[Point]): List[Point] = {
    val remaining = asteroids.filterNot(a => a == base)
    remaining.filterNot(isVisible => remaining.filter(a => a != isVisible).exists(blockedBy => Point.isBlocking(base, isVisible, blockedBy)))
  }

  @tailrec
  private def getAsteroidLayers(base : Point, remaining: List[Point], acc: List[List[Point]]): List[List[Point]] = {
    if (remaining.isEmpty) {
      acc
    } else {
      val layer = getVisibleAsteroids(base, remaining)
      getAsteroidLayers(base, remaining.filterNot(p => layer.contains(p)), acc ::: layer :: Nil)
    }
  }

  @tailrec
  private def destroy(base : Point, left: Int, layers: List[List[Point]]): Point = {
    if (layers.isEmpty) {
      Point(0, 0)
    } else {
      val layer = layers.head
      if (layer.size > left) {
        layer.map(asteroid => (asteroid, Point.calculateAngle(base, Point(0, base.x), asteroid))).sortWith((a, b) => a._2 < b._2)(left - 1)._1
      } else {
        destroy(base, left - layer.size, layers.tail)
      }
    }
  }

  private def doPart1(locations: List[Point]): Map[Point, Int] = {
    locations.map(baseAsteroid => baseAsteroid -> getVisibleAsteroids(baseAsteroid, locations).size).toMap
  }

  private def doPart2(base: Point): Point = {
    val layers = getAsteroidLayers(base, asteroidLocations.filter(a => a != base), List())
    destroy(base, 200, layers)
  }

  private val visibleAsteroidsMap = doPart1(asteroidLocations)
  private val lastDestroyedAsteroid = doPart2(visibleAsteroidsMap.filter(p => p._2 == visibleAsteroidsMap.values.max).head._1)

  override val part1Answer: Int = visibleAsteroidsMap.values.max
  override val part2Answer: Int = lastDestroyedAsteroid.x * 100 + lastDestroyedAsteroid.y
}


