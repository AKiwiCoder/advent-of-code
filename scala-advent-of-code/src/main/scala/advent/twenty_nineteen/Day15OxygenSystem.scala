package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities._

import scala.annotation.tailrec

class Day15OxygenSystem(filename: String) extends DailyProblem[Int, Int] {
  private val program = IntComputer.loadProgram(filename)

  private def move(direction: Int, location: Point): Point = {
    direction match {
      case 1 => LocationHelper.step(location, FacingNorth())
      case 2 => LocationHelper.step(location, FacingSouth())
      case 3 => LocationHelper.step(location, FacingWest())
      case 4 => LocationHelper.step(location, FacingEast())
    }
  }

  @tailrec
  private def walk(pending: List[(Int, Point, IntComputerState)], current: Map[Point, Char]): Map[Point, Char] = {
    if (pending.isEmpty) {
      current
    } else {
      val (direction, location, state) = pending.head

      val newState = IntComputer.execute(IntComputerState.copyState(state, List(direction)))

      val newLocation = move(direction, location)

      val result = newState.output.head

      val newCommands = List(1, 2, 3, 4).map(direction => (direction, newLocation, newState)).filter(command => !current.contains(move(direction, location)))

      // Hit wall
      if (result == 0) {
        walk(pending.tail, current + (newLocation -> '#'))
      } else if (result == 2) {
        walk(pending.tail ::: newCommands, current + (newLocation -> '2'))
      } else {
        walk(pending.tail ::: newCommands, current + (newLocation -> '.'))
      }
    }
  }

  @tailrec
  private def floodFill(map: Map[Point, Char], pending: List[(Int, Point)], bestSoFar: Map[Point, Int]): Map[Point, Int] = {
    pending match {
      case Nil => bestSoFar
      case (pBest, pLocation) :: pRest => {
        val currentBest = bestSoFar.getOrElse(pLocation, Integer.MAX_VALUE)
        if (pBest < currentBest) {
          // We are on a shorter path
          val newBestSoFar = if (pBest < currentBest) bestSoFar + (pLocation -> pBest) else bestSoFar
          val newSteps = List(1, 2, 3, 4).map(direction => (pBest + 1, move(direction, pLocation))).filter{ case (_, location) => map.getOrElse(location, '#') != '#'}
          floodFill(map, pRest ::: newSteps, newBestSoFar)
        } else {
          // We had already found the shortest
          floodFill(map, pRest, bestSoFar)
        }
      }
    }
  }

  def dumpMap(map: Map[Point, AnyVal]) = {
    val limits = map.keySet.foldLeft((Int.MaxValue, Int.MaxValue, Int.MinValue, Int.MinValue))((a, p) => (Math.min(a._1, p.y), Math.min(a._2, p.x), Math.max(a._3, p.y), Math.max(a._4, p.x)))
    for (y <- limits._1 to limits._3) {
      for (x <- limits._2 to limits._4) {
        val c = map.getOrElse(Point(y, x), '#')
        print(if (c == '#') '\u2588' else c)
      }
      println();
    }
    println();
  }

  //dumpMap(overallMap)

  private val initialCommands = List(1, 2, 3, 4).map(direction => (direction, Point(0, 0), IntComputerState.newState(program)))
  private val overallMap = walk(initialCommands, Map(Point(0, 0) -> '1'))

  private val oxygenSystemLocation = overallMap.filter(entry => entry._2 == '2').head._1

  private val shortestPath = floodFill(overallMap, List((0, Point(0, 0))), Map())
  private val oxygenFlood = floodFill(overallMap, List((0, oxygenSystemLocation)), Map())

  override val part1Answer: Int = shortestPath.get(oxygenSystemLocation).get
  override val part2Answer: Int = oxygenFlood.values.max
}


