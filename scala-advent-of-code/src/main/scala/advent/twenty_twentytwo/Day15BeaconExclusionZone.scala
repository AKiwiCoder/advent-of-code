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

  def search(y: Int): (Int, Int, List[Int]) = {
    (p2minX to p2maxX).foldLeft((Integer.MAX_VALUE, Integer.MIN_VALUE, List[Int]()))((acc, x) => {
      val check = Point(x, y)
      if (input.exists(r => manhatten(check, r.sensor) <= r.distance)) {
        (Math.min(acc._1, x), Math.max(acc._2, x), acc._3)
      } else {
        (acc._1, acc._2, if (acc._3.size > 2) acc._3 else x :: acc._3)
      }
    })
  }

  @tailrec
  private def part2search(y : Int) : Long = {
    if (y > p2maxY) {
      -1
    } else {
      val s = search(y)
      if (s._3.size == 1) {
        s._3.head * 4000000L + y
      } else {
        part2search(y + 1)
      }
    }
  }

  override val part1Answer: Int = count(left - maxDistance, right + maxDistance, 0, part1Row)
  override val part2Answer: Long = part2search(p2minY)
}
