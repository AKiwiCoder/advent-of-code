package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point}

class Day10MonitoringStation(filename: String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename)

  val locs = input.zipWithIndex.map(entry => entry._1.toList.zipWithIndex.filter(d => d._1 == '#').map(e => (e._2, entry._2, e._1))).flatten.map(a => Point(a._2, a._1))

  private def distance(a: Point, b: Point): Double = {
    Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))
  }

  private def isBlocked(base: Point, asteroid: Point, check: Point): Boolean = {
    Math.abs(distance(base, check) + distance(asteroid, check) - distance(base, asteroid)) < 0.000001
  }

  private def doPart1(): Map[Point, Int] = {
    var counts = Map[Point, Int]()

    for (baseAsteroid <- locs) {
      var count = 0
      for (visibleAsteroid <- locs) {
        if (visibleAsteroid != baseAsteroid) {
          var blocked = false
          for (blockingAsteroid <- locs) {
            if (blockingAsteroid != visibleAsteroid && blockingAsteroid != baseAsteroid) {
              blocked = blocked || isBlocked(baseAsteroid, visibleAsteroid, blockingAsteroid)
            }
          }
          if (!blocked)
            count += 1
        }
      }
      counts = counts + (baseAsteroid -> count)
    }
    counts
  }

  def calculateAngle(origin: Point, vertical: Point, target: Point): Double = {
    val deg = Math.toDegrees(Math.atan2(target.y - origin.y, target.x - origin.x) - Math.atan2(vertical.y - origin.y, vertical.x - origin.x))
    if (deg < 0) deg + 360 else deg
  }

  def doPart2(base: Point): Point = {
    val asteroids = locs.filter(a => a != base)
    val up = Point(0, base.x)
    val angles = asteroids.map(loc => (loc, calculateAngle(base, up, loc))).sortWith((a, b) => a._2 < b._2).map(a => a._2 -> a._1).toMap
    val keys = angles.keySet.toList.sorted

    var result = Point(0, 0)
    var count = 0
    for (key <- keys) {
      count += 1
      if (count == 200) {
        result = angles(key)
      }
    }

    result
  }

  private val p1 = doPart1()
  private val p2 = doPart2(p1.filter(p => p._2 == p1.values.max).head._1)

  override val part1Answer: Int = doPart1().values.max
  override val part2Answer: Int = p2.x * 100 + p2.y
}


