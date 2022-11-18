package advent.twenty_twentyone 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day12PassagePathing(filename : String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename).map(line => {
    val bits = line.split("-")
    (bits(0).trim, bits(1).trim)
  })

  private val paths = input.foldLeft(Map[String, Set[String]]())((acc, pair) => (acc + (pair._1 -> (acc.getOrElse(pair._1, Set()) + pair._2)) + (pair._2 -> (acc.getOrElse(pair._2, Set()) + pair._1))))

  private val patternSmall = "([a-z]+)".r

  private val smallNodes = paths.keys.filter(_ match {
    case patternSmall(_) => true
    case _ => false
  }).toSet

  private def calculatePathParameters(path : List[String]) : (Int, Int, Int, Int) = {
    val starts = path.count(_ == "start")
    val ends = path.count(_ == "end")
    val visitedSmallCount = path.filter(smallNodes.contains).groupBy(identity).view.mapValues(_.size)

    val (visitedMoreThanOnce, visitedMoreThanTwice) = visitedSmallCount.foldLeft((0,0))((acc, entry) =>
      (if (entry._2 > 1) acc._1 + 1 else acc._1,
       if (entry._2 > 2) acc._2 + 1 else acc._2))

    (starts, ends, visitedMoreThanOnce, visitedMoreThanTwice)
  }

  private def isValidPartOne(path : List[String]) : Boolean = {
    val (starts, ends, visitedMoreThanOnce, _) = calculatePathParameters(path)
    starts == 1 && ends <= 1 && visitedMoreThanOnce == 0
  }

  private def isValidPartTwo(path : List[String]) : Boolean = {
    val (starts, ends, visitedMoreThanOnce, visitedMoreThanTwice) = calculatePathParameters(path)
    starts == 1 && ends <= 1 && visitedMoreThanOnce <= 1 && visitedMoreThanTwice == 0
  }

  private def countNumberOfPaths(isValid : List[String] => Boolean): Set[List[String]] = {
    @tailrec
    def walk(considering : List[List[String]], finished : Set[List[String]]) : Set[List[String]] = {
      if (considering.isEmpty) {
        finished
      } else {
        val current = considering.head
        val (newConsidering, newFinished) = if (current.head == "end") {
          (considering.tail, finished + current)
        } else {
          val newConsidering = paths(current.head).map(next => next :: current).filter(isValid).diff(finished).toList ::: considering.tail
          (newConsidering, finished)
        }
        walk(newConsidering, newFinished)
      }
    }

    walk(List(List("start")), Set())
  }

  override val part1Answer: Int = countNumberOfPaths(isValidPartOne).size
  override val part2Answer: Int = countNumberOfPaths(isValidPartTwo).size
}


