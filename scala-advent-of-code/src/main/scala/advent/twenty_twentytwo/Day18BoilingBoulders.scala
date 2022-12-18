package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day18BoilingBoulders(filename: String) extends DailyProblem[Int, Int] {
  case class Cube(x: Int, y: Int, z: Int)

  private val input = FileUtilities.readFile(filename).map(l => l.split(",")).map(a => Cube(a(0).toInt, a(1).toInt, a(2).toInt))

  def count(filled : List[Cube]) : Int = {
    input.map(a => {
      val above = filled.exists(p => p.x + 1 == a.x && p.y == a.y && p.z == a.z)
      val below = filled.exists(p => p.x - 1 == a.x && p.y == a.y && p.z == a.z)
      val left = filled.exists(p => p.x == a.x && p.y + 1 == a.y && p.z == a.z)
      val right = filled.exists(p => p.x == a.x && p.y - 1 == a.y && p.z == a.z)
      val top = filled.exists(p => p.x == a.x && p.y == a.y && p.z + 1 == a.z)
      val bottom = filled.exists(p => p.x == a.x && p.y == a.y && p.z - 1 == a.z)
      List(above, below, left, right, top, bottom).count(!_)
    }).sum
  }


  private val xs = input.map(_.x)
  private val ys = input.map(_.y)
  private val zs = input.map(_.z)
  private val xmin = xs.min -1
  private val xmax = xs.max +1
  private val ymin = ys.min -1
  private val ymax = ys.max+1
  private val zmin = zs.min -1
  private val zmax = zs.max+1

  @tailrec
  private def fill(next : Set[Cube], water : Set[Cube]) : Set[Cube] = {
    if (next.isEmpty) {
      water
    } else {
      val n = next.head
      if (n.x < xmin || n.x > xmax  || n.y < ymin||n.y > ymax ||n.z < zmin || n.z > zmax ) {
        fill(next.tail, water)
      } else  if (input.contains(n)) {
        // This is a block, so cannot fill
        fill(next.tail, water)
      } else if (water.contains(n)) {
        // Already water, so cannot fill
        fill(next.tail, water)
      } else {
        val newNext = next + Cube(n.x-1, n.y, n.z)+ Cube(n.x+1, n.y, n.z)+ Cube(n.x, n.y-1, n.z)+ Cube(n.x, n.y+1, n.z)+ Cube(n.x, n.y, n.z-1)+ Cube(n.x, n.y, n.z+1)
        fill(newNext, water + n)
      }
    }
  }

  private val water = fill(Set(Cube(0,0,0)), Set())
  private val filled = input ++ water

  override val part1Answer: Int = count(input)
  override val part2Answer: Int = part1Answer - count(filled)
}
