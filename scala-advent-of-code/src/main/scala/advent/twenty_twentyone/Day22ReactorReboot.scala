package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.{FileUtilities}

case class Point3d(x: Long, y: Long, z: Long)

object CubeHelper {
  def intersects(lhs: Cube, rhs: Cube): Boolean = {
    val x1 = lhs.to.x >= rhs.from.x
    val x2 = lhs.from.x <= rhs.to.x
    val y1 = lhs.to.y >= rhs.from.y
    val y2 = lhs.from.y <= rhs.to.y
    val z1 = lhs.to.z >= rhs.from.z
    val z2 = lhs.from.z <= rhs.to.z
    x1 && x2 && y1 && y2 && z1 && z2
  }

  def contains(lhs: Cube, rhs: Cube): Boolean = {
    val x1 = lhs.from.x <= rhs.from.x
    val x2 = lhs.to.x >= rhs.to.x
    val y1 = lhs.from.y <= rhs.from.y
    val y2 = lhs.to.y >= rhs.to.y
    val z1 = lhs.from.z <= rhs.from.z
    val z2 = lhs.to.z >= rhs.to.z
    x1 && x2 && y1 && y2 && z1 && z2
  }

}

case class Cube(from: Point3d, to: Point3d) {
  def remove(other: Cube): List[Cube] = {
    if (CubeHelper.intersects(this, other)) {
      // Limit changes to within this cube
      val lx1 = Math.max(other.from.x, from.x)
      val lx2 = Math.min(other.to.x, to.x)
      val ly1 = Math.max(other.from.y, from.y)
      val ly2 = Math.min(other.to.y, to.y)
      val lz1 = Math.max(other.from.z, from.z)
      val lz2 = Math.min(other.to.z, to.z)

      // Calculate the start/ends for the resulting fragmented cube
      val x1 = (from.x, lx1 - 1)
      val x2 = (lx1, lx2)
      val x3 = (lx2 + 1, to.x)
      val y1 = (from.y, ly1 - 1)
      val y2 = (ly1, ly2)
      val y3 = (ly2 + 1, to.y)
      val z1 = (from.z, lz1 - 1)
      val z2 = (lz1, lz2)
      val z3 = (lz2 + 1, to.z)

      val xS = List(x1, x2, x3)
      val yS = List(y1, y2, y3)
      val zS = List(z1, z2, z3)

      val cubes = (for (x <- 0 to 2; y <- 0 to 2; z <- 0 to 2) yield Cube(Point3d(xS(x)._1, yS(y)._1, zS(z)._1), Point3d(xS(x)._2, yS(y)._2, zS(z)._2)))

      // The middle cube is the on that we don't want to include
      def isMiddleCube(c: Cube): Boolean = {
        c.from.x == x2._1 && c.to.x == x2._2 && c.from.y == y2._1 && c.to.y == y2._2 && c.from.z == z2._1 && c.to.z == z2._2
      }

      // If the cube we are removing touches on of our edges we get an cube with -ve dimension(s)
      cubes.filter(cube => cube.valid() && !isMiddleCube(cube)).toList
    } else {
      List(this)
    }
  }

  def volume(): Long = {
    (to.x - from.x + 1) * (to.y - from.y + 1) * (to.z - from.z + 1)
  }

  def valid(): Boolean = {
    from.x <= to.x && from.y <= to.y && from.z <= to.z
  }
}

class Day22ReactorReboot(filename: String) extends DailyProblem[Int, Long] {

  case class Command(on: Boolean, xMin: Int, xMax: Int, yMin: Int, yMax: Int, zMin: Int, zMax: Int)

  private val PATTERN = "(on|off) x=([-]*[0-9]+)..([-]*[0-9]+),y=([-]*[0-9]+)..([-]*[0-9]+),z=([-]*[0-9]+)..([-]*[0-9]+)".r

  private val commands = FileUtilities.readFile(filename).map {
    case PATTERN(cmd, xMin, xMax, yMin, yMax, zMin, zMax) => Command(cmd.equals("on"), xMin.toInt, xMax.toInt, yMin.toInt, yMax.toInt, zMin.toInt, zMax.toInt)
  }

  override val part1Answer: Int = commands.foldLeft(Set[Point3d]())((acc, command) => {
    if (command.xMin >= -50 && command.xMax <= 50 && command.yMin >= -50 && command.yMax <= 50 && command.zMin >= -50 && command.zMax <= 50) {
      val points = for (x <- command.xMin to command.xMax;
                        y <- command.yMin to command.yMax;
                        z <- command.zMin to command.zMax) yield Point3d(x, y, z)

      if (command.on) {
        acc.union(points.toSet)
      } else {
        acc.diff(points.toSet)
      }
    } else {
      acc
    }
  }).size

  private val part2 = commands.foldLeft(List[Cube]())((acc, command) => {
    val commandCube = Cube(Point3d(command.xMin, command.yMin, command.zMin), Point3d(command.xMax, command.yMax, command.zMax))
    val afterRemoveCommandsCube = acc.flatMap(cube => cube.remove(commandCube))

    val result = if (command.on) {
      commandCube :: afterRemoveCommandsCube
    } else {
      afterRemoveCommandsCube
    }
    result
  })

  override val part2Answer: Long = part2.map(_.volume()).sum
}


