package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day08SevenSegmentSearch(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  /*
   0:      1:      2:      3:      4:
 aaaa    ....    aaaa    aaaa    ....
b    c  .    c  .    c  .    c  b    c
b    c  .    c  .    c  .    c  b    c
 ....    ....    dddd    dddd    dddd
e    f  .    f  e    .  .    f  .    f
e    f  .    f  e    .  .    f  .    f
 gggg    ....    gggg    gggg    ....

  5:      6:      7:      8:      9:
 aaaa    aaaa    aaaa    aaaa    aaaa
b    .  b    .  .    c  b    c  b    c
b    .  b    .  .    c  b    c  b    c
 dddd    dddd    ....    dddd    dddd
.    f  e    f  .    f  e    f  .    f
.    f  e    f  .    f  e    f  .    f
 gggg    gggg    ....    gggg    gggg

   */
  def determineMapping(inputs: Array[String]): Map[Set[Char], Int] = {
    val oneDigit = inputs.find(_.length == 2).get.toCharArray.toSet
    val sevenDigit = inputs.find(_.length == 3).get.toCharArray.toSet

    val characterCounts = inputs.flatMap(_.toCharArray).toList.groupBy(identity).view.mapValues(_.size).toMap

    val outputA = sevenDigit.diff(oneDigit).head
    val outputB = characterCounts.filter(_._2 == 6).keys.head
    val outputE = characterCounts.filter(_._2 == 4).keys.head
    val outputC = characterCounts.filter(entry => entry._2 == 8 && entry._1 != outputA).head._1
    val outputF = sevenDigit.diff(Set(outputA, outputC)).head

    val zeroDigit = inputs.filter(_.length == 6).toList.filter(_.contains(outputE)).filter(_.contains(outputC))
    val outputG = zeroDigit.head.toCharArray.toSet.diff(Set(outputA, outputB, outputC, outputE, outputF)).head

    val outputD = characterCounts.filter(_._2 == 7).filter(_._1 != outputG).toList.head._1

    Map((Set(outputA, outputB, outputC, outputE, outputF, outputG) -> 0),
      (Set(outputC, outputF) -> 1),
      (Set(outputA, outputC, outputD, outputE, outputG) -> 2),
      (Set(outputA, outputC, outputD, outputF, outputG) -> 3),
      (Set(outputB, outputC, outputD, outputF) -> 4),
      (Set(outputA, outputB, outputD, outputF, outputG) -> 5),
      (Set(outputA, outputB, outputD, outputE, outputF, outputG) -> 6),
      (Set(outputA, outputC, outputF) -> 7),
      (Set(outputA, outputB, outputC, outputD, outputE, outputF, outputG) -> 8),
      (Set(outputA, outputB, outputC, outputD, outputF, outputG) -> 9))
  }

  def part2(): Int = {
    def determineDigits(line: String): Int = {
      val bits = line.split('|')
      val output = bits(1).trim.split(' ').map(_.trim)
      val inputs = bits(0).trim.split(' ').map(_.trim)

      val result: Map[Set[Char], Int] = determineMapping(inputs)

      output.foldLeft(0)((number, digit) => number * 10 + result(digit.toCharArray.toSet))
    }

    input.map(line => determineDigits(line)).sum
  }

  override val part1Answer: Int = input.foldLeft(0)((counter, line) => counter + line.split('|')(1).split(' ').map(_.trim).count(chunk => chunk.length == 2 || chunk.length == 4 || chunk.length == 3 || chunk.size == 7))
  override val part2Answer: Int = part2()
}


