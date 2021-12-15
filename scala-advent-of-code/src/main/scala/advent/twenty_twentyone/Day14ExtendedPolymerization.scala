package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day14ExtendedPolymerization(filename: String) extends DailyProblem[Long, Long] {

  private val PAIR_PATTERN = "([A-Z][A-Z]) -> ([A-Z])".r

  private val input = FileUtilities.readFile(filename)

  private val polymerTemplate = input.head
  private val pairInsertionRules = input.drop(2).map { case PAIR_PATTERN(f, s) => f -> s }.toMap

  def convertToPairs(input: String): Map[String, Long] = {
    input.tail.foldLeft((Map[String, Long](), input.head))((acc, thisChar) => {
      val (pairMap, lastChar) = acc
      val pair = "" + lastChar + thisChar
      val number = pairMap.getOrElse(pair, 0L) + 1
      (pairMap + (pair -> number), thisChar)
    })._1
  }

  def performInsertions(startingString: String, iterations: Int): Long = {
    @tailrec
    def execute(count: Int, pairMap: Map[String, Long]): Map[String, Long] = {
      if (count == 0) {
        pairMap
      } else {
        val output = pairMap.foldLeft(Map[String, Long]())((acc, entry) => pairInsertionRules.get(entry._1) match {
          case Some(addition) => {
            val pairOne: String = entry._1(0) + addition
            val pairTwo: String = addition + entry._1(1)

            if (pairOne.equals(pairTwo)) {
              val pairOneCount: Long = acc.getOrElse(pairOne, 0L) + entry._2 * 2
              acc + (pairOne -> (pairOneCount))
            } else {
              val pairOneCount: Long = acc.getOrElse(pairOne, 0L) + entry._2
              val pairTwoCount: Long = acc.getOrElse(pairTwo, 0L) + entry._2
              acc + (pairOne -> pairOneCount) + (pairTwo -> pairTwoCount)
            }
          }
          case None => {
            acc + entry
          }
        })
        execute(count - 1, output)
      }
    }

    val result = execute(iterations, convertToPairs(startingString))

    val counts = result.foldLeft(Map[Char, Long](polymerTemplate(0) -> 1, polymerTemplate.last -> 1))((counts, entry) => {
      val charOne = entry._1(0)
      val charTwo = entry._1(1)

      if (charOne.equals(charTwo)) {
        val charOneCount = counts.getOrElse(charOne, 0L) + entry._2 * 2
        counts + (charOne -> charOneCount)
      } else {
        val charOneCount = counts.getOrElse(charOne, 0L) + entry._2
        val charTwoCount = counts.getOrElse(charTwo, 0L) + entry._2
        counts + (charOne -> charOneCount) + (charTwo -> charTwoCount)
      }
    }).map(e => (e._1 -> e._2 / 2))

    val mostCommon = counts.values.max
    val leastCommon = counts.values.min
    mostCommon - leastCommon
  }

  override val part1Answer: Long = performInsertions(polymerTemplate, 10)
  override val part2Answer: Long = performInsertions(polymerTemplate, 40)
}


