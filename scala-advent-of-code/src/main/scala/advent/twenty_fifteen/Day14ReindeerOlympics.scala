package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class Reindeer(name: String, speed: Int, flyTime: Int, restTime: Int)

class Day14ReindeerOlympics(filename: String, target: Int) extends DailyProblem[Int, Int] {
  private val pattern = "([A-Za-z]+) can fly ([0-9]+) km/s for ([0-9]+) seconds, but then must rest for ([0-9]+) seconds.".r

  private def parse(line: String): Reindeer = {
    line match {
      case pattern(name, speed, flyTime, restTime) => Reindeer(name, speed.toInt, flyTime.toInt, restTime.toInt)
    }
  }

  private val reindeer = FileUtilities.readFile(filename, parse)

  private def calculatePositions(time: Int): Map[String, Int] = reindeer.map(r => {
    val wholePeriods: Int = time / (r.flyTime + r.restTime)
    val remainingTime: Int = time % (r.flyTime + r.restTime)
    val flyTime: Int = Math.min(remainingTime, r.flyTime)
    r.name -> (wholePeriods * r.speed * r.flyTime + flyTime * r.speed)
  }).toMap

  private def calculatePoints(): Map[String, Int] = {
    @tailrec
    def iterate(time: Int, acc: Map[String, Int]): Map[String, Int] = {
      if (time >= target) {
        acc
      } else {
        val currentPositions = calculatePositions(time)
        val inFrontPositions = currentPositions.map(e => e._2).max
        val ahead = currentPositions.filter(e => e._2 == inFrontPositions)
        iterate(time + 1, acc.map(entry => if (ahead.contains(entry._1)) entry._1 -> (acc(entry._1) + 1) else entry))
      }
    }

    iterate(1, reindeer.map(r => r.name -> 0).toMap)
  }

  override val part1Answer: Int = calculatePositions(target).map(e => e._2).max
  override val part2Answer: Int = calculatePoints().map(e => e._2).max
}
