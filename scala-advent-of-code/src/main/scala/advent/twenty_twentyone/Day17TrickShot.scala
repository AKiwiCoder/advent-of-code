package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day17TrickShot(filename: String) extends DailyProblem[Int, Int] {

  private val pattern = "target area: x=([-]*[0-9]+)..([-]*[0-9]+), y=([-]*[0-9]+)..([-]*[0-9]+)".r

  private val input = FileUtilities.readFile(filename)(0)

  private val (targetMinX, targetMaxX, targetMinY, targetMaxY) = input match {
    case pattern(minX, maxX, minY, maxY) => (minX.toInt, maxX.toInt, minY.toInt, maxY.toInt)
  }

  @tailrec
  private def fireOne(x: Int, y: Int, dx: Int, dy: Int, maxHeight: Int): Int = {
    if (y <= targetMaxY && y >= targetMinY && x <= targetMaxX && x >= targetMinX) {
      maxHeight
    } else if (y < targetMinY) {
      Int.MinValue
    } else {
      val newMaxHeight = if (y > maxHeight) y else maxHeight
      fireOne(x + dx, y + dy, if (dx > 0) dx - 1 else dx, dy - 1, newMaxHeight)
    }
  }

  private def determineBestShots(): Set[(Int, Int, Int)] = {
    (-500 to 500).foldLeft(Set[(Int, Int, Int)]())((hits, dx) => (-500 to 500).foldLeft(hits)((acc, dy) => {
      val maxHeight = fireOne(0, 0, dx, dy, Int.MinValue)
      if (maxHeight > Int.MinValue) acc + ((dx, dy, maxHeight)) else acc
    }))
  }

  private val bestHits = determineBestShots()

  override val part1Answer: Int = bestHits.map(_._3).max
  override val part2Answer: Int = bestHits.size
}


