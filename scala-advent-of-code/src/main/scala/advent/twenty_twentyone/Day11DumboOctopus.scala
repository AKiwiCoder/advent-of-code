package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point, Point2d}

import scala.annotation.tailrec

class Day11DumboOctopus(filename: String, partOneIterations: Int) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).map(_.toCharArray.map(c => ("" + c).toInt).toList)
  private val area = input.indices.foldLeft(Map[Point2d, Int]())((map, row) => input(row).indices.foldLeft(map)((map, col) => map + (Point2d(row, col) -> input(row)(col))))

  private def nearby(point: Point2d, flashedPoint: Point2d): Boolean = {
    flashedPoint == Point2d(point.x - 1, point.y - 1) ||
      flashedPoint == Point2d(point.x - 1, point.y) ||
      flashedPoint == Point2d(point.x - 1, point.y + 1) ||
      flashedPoint == Point2d(point.x, point.y - 1) ||
      flashedPoint == Point2d(point.x, point.y + 1) ||
      flashedPoint == Point2d(point.x + 1, point.y - 1) ||
      flashedPoint == Point2d(point.x + 1, point.y) ||
      flashedPoint == Point2d(point.x + 1, point.y + 1)
  }

  private def partOne(): Int = {
    def iterate(count: Int, current: Map[Point2d, Int], totalFlashes: List[Point2d]): List[Point2d] = {
      if (count == 0) {
        totalFlashes
      } else {
        val (flashed, resultMap) = updateMap(current)
        iterate(count - 1, resultMap, flashed ::: totalFlashes)
      }
    }
    iterate(partOneIterations, area, List()).length
  }

  private def partTwo(number : Int): Int = {
    def iterate(count : Int, current: Map[Point2d, Int], flashedLastRound: List[Point2d]): Int = {
      if (flashedLastRound.length == number) {
        count
      } else {
        val (flashed, resultMap) = updateMap(current)
        iterate(count + 1, resultMap, flashed)
      }
    }
    iterate(0, area, List())
  }


  @tailrec
  private def flashUntilAllHaveFinished(currentMap: Map[Point2d, Int], allFlashes: List[Point2d]): (Map[Point2d, Int], List[Point2d]) = {
    val needsToFlash = currentMap.filter(e => e._2 > 9)

    if (needsToFlash.isEmpty) {
      (currentMap, allFlashes)
    } else {
      val remaining = currentMap.filter(e => e._2 <= 9)
      val done = needsToFlash.foldLeft(remaining)((updated, flashedPoint) => updated.map(e => if (nearby(e._1, flashedPoint._1)) e._1 -> (e._2 + 1) else e))
      flashUntilAllHaveFinished(done, needsToFlash.keys.toList ::: allFlashes)
    }
  }

  private def updateMap(current: Map[Point2d, Int]) : (List[Point2d], Map[Point2d, Int]) = {
    val increaseEnergy = current.map(entry => entry._1 -> (entry._2 + 1))
    val (afterAllHaveFlashed, whichSquidFlashed) = flashUntilAllHaveFinished(increaseEnergy, List())
    val resetFlashedToZeroEnergy = (afterAllHaveFlashed.toList ::: whichSquidFlashed.map(point => point -> 0)).toMap
    (whichSquidFlashed, resetFlashedToZeroEnergy)
  }

  override val part1Answer: Int = partOne()
  override val part2Answer: Int = partTwo(area.size)
}


