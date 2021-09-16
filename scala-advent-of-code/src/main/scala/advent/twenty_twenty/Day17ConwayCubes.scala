package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec
import advent.utilities.Point2d
import advent.utilities.Point3d
import advent.utilities.Point4d

class Day17ConwayCubes(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).map(line => line.zipWithIndex).zipWithIndex.flatMap(x => x._1.map(y => (Point2d(x._2, y._2) -> y._1))).filterNot(_._2 == '.')

  private val part1Input = input.map(e => Point3d(e._1.x, e._1.y, 0)).toSet
  private val part2Input = input.map(e => Point4d(e._1.x, e._1.y, 0, 0)).toSet

  private def count(current: Set[Point3d], point: Point3d): Int = {
    (-1 to +1).flatMap(dX =>
      (-1 to +1).map(dY =>
        (-1 to +1).count(dZ =>
          if (dX == 0 && dY == 0 && dZ == 0) false else current.contains(Point3d(point.x + dX, point.y + dY, point.z + dZ))))).sum
  }

  private def count(current: Set[Point4d], point: Point4d): Int = {
    (-1 to +1).flatMap(dX =>
      (-1 to +1).flatMap(dY =>
        (-1 to +1).map(dZ =>
          (-1 to +1).count(dW =>
          if (dX == 0 && dY == 0 && dZ == 0 && dW == 0) false else current.contains(Point4d(point.x + dX, point.y + dY, point.z + dZ, point.w + dW)))))).sum
  }

  private def generate3d(point: Point3d): Set[Point3d] = {
    (-1 to 1).foldLeft(Set[Point3d]())((a, dX) =>
      (-1 to 1).foldLeft(a)((b, dY) =>
        (-1 to 1).foldLeft(b)((c, dZ) =>
          c + Point3d(point.x + dX, point.y + dY, point.z + dZ))))
  }

  private def generate4d(point: Point4d): Set[Point4d] = {
    (-1 to 1).foldLeft(Set[Point4d]())((a, dX) =>
      (-1 to 1).foldLeft(a)((b, dY) =>
        (-1 to 1).foldLeft(b)((c, dZ) =>
          (-1 to 1).foldLeft(c)((d, dW) =>
            d + Point4d(point.x + dX, point.y + dY, point.z + dZ, point.w + dW)))))
  }

  private def part1(): Int = {
    @tailrec
    def run(step: Int, current: Set[Point3d]): Set[Point3d] = {
      if (step == 0) {
        current
      } else {
        val pointsToCheck = current.foldLeft(Set[Point3d]())((acc, point) => generate3d(point) ++ acc)
        val newList = pointsToCheck.filter(point => {
          val activeCount = count(current, point)
          if (current.contains(point)) {
            if (activeCount == 2 || activeCount == 3) true else false
          } else {
            if (activeCount == 3) true else false
          }
        })
        run(step - 1, newList)
      }
    }
    run(6, part1Input).size
  }

  private def part2(): Int = {
    @tailrec
    def run(step: Int, current: Set[Point4d]): Set[Point4d] = {
      if (step == 0) {
        current
      } else {
        val pointsToCheck = current.foldLeft(Set[Point4d]())((acc, point) => generate4d(point) ++ acc)
        val newList = pointsToCheck.filter(point => {
          val activeCount = count(current, point)
          if (current.contains(point)) {
            if (activeCount == 2 || activeCount == 3) true else false
          } else {
            if (activeCount == 3) true else false
          }
        })
        run(step - 1, newList)
      }
    }
    run(6, part2Input).size
  }

  override val part1Answer: Int = part1()
  override val part2Answer: Int = part2()
}


