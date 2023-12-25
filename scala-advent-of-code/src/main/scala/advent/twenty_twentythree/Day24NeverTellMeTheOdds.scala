package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day24NeverTellMeTheOdds(filename : String, minY : Long, maxY : Long, minX : Long, maxX : Long) extends DailyProblem[Int, Int] {

  case class Hailstone(px : Long, py : Long, pz : Long, dx : Long, dy: Long, dz : Long) {
    def calculateParameters() : (Double,Double) = {
      val x1 = px
      val x2 = px + dx
      val y1 = py
      val y2 = py + dy

      val a = (1.0 * y2-y1) / (1.0 * x2-x1)
      val c = py - (a * px)

      (a,c)
    }
  }

  val pattern = "([0-9]+), +([0-9]+), +([0-9]+) +@ +([-0-9]+), +([-0-9]+), +([-0-9]+)".r

  private val input = FileUtilities.readFile(filename).map {
    case pattern(px, py, pz, dx, dy, dz) => Hailstone(px.toLong, py.toLong, pz.toLong, dx.toLong, dy.toLong, dz.toLong)
  }

  def checkIsInFuture(lhs: Hailstone, y: Double, x: Double): Boolean = {
    val deltaY = y - lhs.py
    val deltaX = x - lhs.px

    val stepsY = deltaY / lhs.dy
    val stepsX = deltaX / lhs.dx

    stepsX > 0 && stepsY > 0
  }

  def intersect(lhs: Hailstone, rhs: Hailstone) : Boolean = {
    val (a,c) = lhs.calculateParameters()
    val (b,d) = rhs.calculateParameters()

    if (a == b) {
      // Parallel
      false
    } else {

      val x = (d - c) / (a - b)
      val y = (a * x + c)

      val hit = x >= minX && x <= maxX && y >= minY && y <= maxY

      if (hit) {
        println(lhs + " *** " + rhs)
        println("  (" + y + ", " + x + ")")

        checkIsInFuture(lhs, y, x)
        checkIsInFuture(rhs, y, x)
      }
      hit && checkIsInFuture(lhs, y, x) && checkIsInFuture(rhs, y, x)
    }
  }

  @tailrec
  private def countPartOneCollisions(tocheck : List[Hailstone], acc : Int): Int = {
    if (tocheck.isEmpty) {
      acc
    } else {
      val me = tocheck.head
      val intersections = tocheck.tail.count(other => intersect(me, other))
      countPartOneCollisions(tocheck.tail, acc + intersections)
    }
  }

  def cross(b1 : Long, b2 : Long, b3: Long, c1 : Long, c2 : Long, c3 : Long) : (Long,Long,Long) = {
    val a1 = b2*c3 - b3*c2
    val a2 = -(b1*c3 - b3*c1)
    val a3 = b1*c2-b2*c1
    (a1,a2,a3)
  }

  def magnitude(x: Long, y: Long, z: Long) : Double = {
    Math.sqrt(x*x + y*y + z*z)
  }

  def calculateTimeToIntersect(l1 : Hailstone, l2 : Hailstone) : Double = {
    val pdX = l2.px - l1.px
    val pdY = l2.py - l1.py
    val pdZ = l2.pz - l1.pz

    val (pv2_x,pv2_y,pv2_z) = cross(pdX,pdY,pdZ, l2.dx, l2.dy, l2.dz)
    val (v12_x, v12_y, v12_z) = cross(l1.dx, l1.dy,l1.dz, l2.dx, l2.dy, l2.dz)

    magnitude(v12_x, v12_y, v12_z) / magnitude(pv2_x, pv2_y, pv2_z)
  }

  override val part1Answer: Int = countPartOneCollisions(input, 0)
  override val part2Answer: Int = 0
}


