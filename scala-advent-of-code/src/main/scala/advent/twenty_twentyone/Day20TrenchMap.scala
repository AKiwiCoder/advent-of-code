package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.twenty_twentyone.Day20TrenchMap.{bc, bl, br, cc, cl, cr, tc, tl, tr}
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

object Day20TrenchMap {
  private def tl(p: Point2d) = Point2d(p.x - 1, p.y - 1)

  private def tc(p: Point2d) = Point2d(p.x + 0, p.y - 1)

  private def tr(p: Point2d) = Point2d(p.x + 1, p.y - 1)

  private def cl(p: Point2d) = Point2d(p.x - 1, p.y + 0)

  private def cc(p: Point2d) = Point2d(p.x + 0, p.y + 0)

  private def cr(p: Point2d) = Point2d(p.x + 1, p.y + 0)

  private def bl(p: Point2d) = Point2d(p.x - 1, p.y + 1)

  private def bc(p: Point2d) = Point2d(p.x + 0, p.y + 1)

  private def br(p: Point2d) = Point2d(p.x + 1, p.y + 1)
}

class Day20TrenchMap(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  private val algorithm = input(0)

  private val initialImage = input.drop(2).zipWithIndex.flatMap(row => row._1.toList.zipWithIndex.map(col => (Point2d(col._2, row._2) -> col._1))).toMap

  private def processImage(maxCount: Int): Map[Point2d, Char] = {
    val unknownCellsFlip = algorithm(0) == '#'

    @tailrec
    def cycle(count: Int, image: Map[Point2d, Char], minX: Int, maxX: Int, minY: Int, maxY: Int): Map[Point2d, Char] = {
      if (count == maxCount) {
        image
      } else {

        def check(p: Point2d): Int = {
          if (image.contains(p)) {
            if (image(p) == '#') 1 else 0
          } else if (unknownCellsFlip) {
            if (count % 2 == 1) 1 else 0
          } else {
            0
          }
        }

        val newImageLocations = (for (
          y <- minY to maxY;
          x <- minX to maxX
        ) yield Point2d(x, y)).toList

        val newImage = newImageLocations.foldLeft(Map[Point2d, Char]())((acc, p) => {
          val number = check(tl(p)) * 256 + check(tc(p)) * 128 + check(tr(p)) * 64 +
            check(cl(p)) * 32 + check(cc(p)) * 16 + check(cr(p)) * 8 +
            check(bl(p)) * 4 + check(bc(p)) * 2 + check(br(p)) * 1
          acc + (p -> algorithm(number))
        })

        cycle(count + 1, newImage, minX - 1, maxX + 1, minY - 1, maxY + 1)
      }
    }

    cycle(0, initialImage, -1, initialImage.map(e => e._1.x).max + 1, -1, initialImage.map(e => e._1.y).max + 1)
  }

  override val part1Answer: Int = processImage(2).count(e => e._2 == '#')
  override val part2Answer: Int = processImage(50).count(e => e._2 == '#')
}


