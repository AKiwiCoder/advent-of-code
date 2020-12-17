package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day17ConwayCubes(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).map(line => line.zipWithIndex).zipWithIndex.flatMap(x => x._1.map(y => ((x._2, y._2) -> y._1))).filterNot(_._2 == '.')

  private val part1Input = input.map(e => (e._1._1, e._1._2, 0))
  private val part2Input = input.map(e => (e._1._1, e._1._2, 0, 0))

  private def count(current: List[(Int, Int, Int)], x: Int, y: Int, z: Int): Int = {
    (x - 1 to x + 1).flatMap(cX =>
      (y - 1 to y + 1).map(cY =>
        (z - 1 to z + 1).count(cZ => if (cX == x && cY == y && cZ == z) false else current.contains((cX, cY, cZ))))).sum
  }

  private def count(current: List[(Int, Int, Int, Int)], x: Int, y: Int, z: Int, w : Int): Int = {
    (x - 1 to x + 1).flatMap(cX =>
      (y - 1 to y + 1).flatMap(cY =>
        (z - 1 to z + 1).map(cZ => (w - 1 to w + 1).count(cW =>
          if (cX == x && cY == y && cZ == z && cW == w) false else current.contains((cX, cY, cZ, cW)))))).sum
  }

  private def part1(): Int = {
    def run(step: Int, current: List[(Int, Int, Int)]): List[(Int, Int, Int)] = {
      println(step + " " + current.length)
      if (step == 0) {
        current
      } else {
        val minX = current.minBy(_._1)._1 - 1
        val maxX = current.maxBy(_._1)._1 + 1
        val minY = current.minBy(_._2)._2 - 1
        val maxY = current.maxBy(_._2)._2 + 1
        val minZ = current.minBy(_._3)._3 - 1
        val maxZ = current.maxBy(_._3)._3 + 1

        val newList = (minX to maxX).foldLeft(List[(Int, Int, Int)]())((accX, cX) => (minY to maxY).foldLeft(accX)((accY, cY) =>
          (minZ to maxZ).foldLeft(accY)((accZ, cZ) => {
            val activeCount = count(current, cX, cY, cZ)
            if (current.contains((cX, cY, cZ))) {
              if (activeCount == 2 || activeCount == 3) (cX, cY, cZ) :: accZ else accZ
            } else {
              if (activeCount == 3) (cX, cY, cZ) :: accZ else accZ
            }
          }
          )))
        run(step - 1, newList)
      }
    }

    run(6, part1Input).size
  }

  private def part2(): Int = {
    def run(step: Int, current: List[(Int, Int, Int, Int)]): List[(Int, Int, Int, Int)] = {
      if (step == 0) {
        current
      } else {
        val minX = current.minBy(_._1)._1 - 1
        val maxX = current.maxBy(_._1)._1 + 1
        val minY = current.minBy(_._2)._2 - 1
        val maxY = current.maxBy(_._2)._2 + 1
        val minZ = current.minBy(_._3)._3 - 1
        val maxZ = current.maxBy(_._3)._3 + 1
        val minW = current.minBy(_._4)._4 - 1
        val maxW = current.maxBy(_._4)._4 + 1

        println(step + " " + current.length + " " + (minX, maxX) + " "+ (minY, maxY) + " "+ (minZ, maxZ) + " "+ (minW, maxW) + " ")

        val newList = (minX to maxX).foldLeft(List[(Int, Int, Int, Int)]())((accX, cX) => (minY to maxY).foldLeft(accX)((accY, cY) =>
          (minZ to maxZ).foldLeft(accY)((accZ, cZ) => (minW to maxW).foldLeft(accZ)((accW, cW) => {
            val activeCount = count(current, cX, cY, cZ, cW)
            if (current.contains((cX, cY, cZ, cW))) {
              if (activeCount == 2 || activeCount == 3) (cX, cY, cZ, cW) :: accW else accW
            } else {
              if (activeCount == 3) (cX, cY, cZ, cW) :: accW else accW
            }
          }
          ))))
        run(step - 1, newList)
      }
    }

    run(6, part2Input).size
  }


  override val part1Answer: Int = part1()
  override val part2Answer: Int = part2()
}


