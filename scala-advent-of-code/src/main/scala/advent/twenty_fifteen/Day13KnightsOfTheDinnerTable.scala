package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day13KnightsOfTheDinnerTable(filename: String) extends DailyProblem[Int, Int] {
  private def parse(line: String): (String, String, Int) = {
    val pattern = "([A-Za-z]+) would ([A-Za-z]+) ([0-9]+) happiness units by sitting next to ([A-Za-z]+).".r

    line match {
      case pattern(source, "gain", happiness, destination) => (source, destination, happiness.toInt)
      case pattern(source, "lose", happiness, destination) => (source, destination, -happiness.toInt)
    }
  }

  val happinessMatrix = FileUtilities.readFile(filename, parse).map((e) => (e._1, e._2) -> e._3).toMap

  val people = happinessMatrix.keySet.map(e => e._1).toList

  @tailrec
  private def calculateHappinessChanges(matrix: Map[(String, String), Int], first: String, prev: String, remaining: List[String], acc: Int): Int = {
    if (remaining.isEmpty) {
      acc
    } else {
      val next = if (remaining.tail.isEmpty) first else remaining.tail.head
      val current = remaining.head
      calculateHappinessChanges(matrix, first, current, remaining.tail, matrix((current, prev)) + matrix((current, next)) + acc)
    }
  }

  override val part1Answer: Int = people.permutations.map(list => calculateHappinessChanges(happinessMatrix, list.head, list.last, list, 0)).max

  val peopleWithMe = "Me" :: people
  val happinessMatrixWithMe = people.foldLeft(Map[(String, String), Int]())((acc, other) => acc + (("Me", other) -> 0) + ((other, "Me") -> 0)) ++ happinessMatrix

  override val part2Answer: Int = peopleWithMe.permutations.map(list => calculateHappinessChanges(happinessMatrixWithMe, list.head, list.last, list, 0)).max
}
