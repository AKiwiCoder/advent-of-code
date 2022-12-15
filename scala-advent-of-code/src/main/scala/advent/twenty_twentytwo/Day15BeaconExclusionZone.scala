package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day15BeaconExclusionZone(filename: String, part1Row: Int, p2minX:Int, p2maxX: Int, p2minY:Int, p2maxY: Int) extends DailyProblem[Int, Long] {

  private def manhatten(l: Point, r: Point): Int = {
    Math.abs(l.x - r.x) + Math.abs(l.y - r.y)
  }

  case class Point(x: Int, y: Int)

  case class Rule(sensor: Point, beacon: Point, distance: Int)

  val rule = "Sensor at x=([-]*[0-9]+), y=([-]*[0-9]+): closest beacon is at x=([-]*[0-9]+), y=([-]*[0-9]+)".r

  private val input = FileUtilities.readFile(filename).map {
    case rule(sX, sY, bX, bY) => Rule(Point(sX.toInt, sY.toInt), Point(bX.toInt, bY.toInt), manhatten(Point(sX.toInt, sY.toInt), Point(bX.toInt, bY.toInt)))
  }


  val maxDistance = input.map(_.distance).max

  val left = input.map(r => Math.min(r.sensor.x, r.beacon.x)).min
  val right = input.map(r => Math.max(r.sensor.x, r.beacon.x)).max

  val top = input.map(r => Math.min(r.sensor.y, r.beacon.y)).min
  val bottom = input.map(r => Math.max(r.sensor.y, r.beacon.y)).max

  @tailrec
  private def count(current: Int, max: Int, acc: Int, limit : Int): Int = {
    if (current > max) {
      acc
    } else {
      val check = Point(current, limit)

      val withinDistance = input.exists(p => manhatten(p.sensor, check) <= p.distance)
      val notABeacon = !input.exists(p => p.beacon == check)
      val notASensor = !input.exists(p => p.sensor == check)

      if (withinDistance && notABeacon && notASensor) {
        count(current + 1, max, acc + 1, limit)
      } else {
        count(current + 1, max, acc, limit)
      }
    }
  }

  @tailrec
  private def search(y: Int, x : Int, left : Int, right : Int, acc : List[Int]): List[Int] = {
    if (x > p2maxX) {
      acc
    } else {
      val check = Point(x, y)
      val s = input.find(r => manhatten(check, r.sensor) <= r.distance)
      if (s.isEmpty) {
        search(y, x + 1, left, right, if (acc.size > 2) acc else x :: acc)
      } else {
        val step = Math.max(1, s.get.sensor.x - x)
        search(y, x + step, Math.min(left, x), Math.max(right, x), acc)
      }
    }
  }

  @tailrec
  private def part2search(y : Int) : Long = {
    if (y % 1000 == 0) {
      println(y)
    }
    if (y > p2maxY) {
      -1
    } else {
      val s = search(y, p2minX, Integer.MAX_VALUE, Integer.MIN_VALUE, List())
      if (s.size == 1) {
        s.head * 4000000L + y
      } else {
        part2search(y + 1)
      }
    }
  }

  override val part1Answer: Int = count(left - maxDistance, right + maxDistance, 0, part1Row)
  override val part2Answer: Long = part2search(p2minY)
}
