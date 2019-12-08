package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day08SpaceImageFormat(filename: String, width: Int, height: Int) extends DailyProblem[Int, String] {

  private val input = FileUtilities.readFile(filename)(0)

  private val layers: List[List[String]] = input.grouped(width * height).toList.map(layer => layer.grouped(width).toList)

  def calculate(lines: List[String]): (Int, Int) = {
    val zeroCount = lines.map(line => line.count(c => c == '0')).sum
    val oneCount = lines.map(line => line.count(c => c == '1')).sum
    val twoCount = lines.map(line => line.count(c => c == '2')).sum
    (zeroCount, oneCount * twoCount)
  }

  val checksum = layers.map(entry => calculate(entry)).sorted

  def calculatePart2(): String = {
    var result = ""
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        var c = '.'
        for (l <- 0 until layers.length) {
          if (layers(l)(y)(x) == '0' && c == '.') {
            c = '#'
          }
          if (layers(l)(y)(x) == '1' && c == '.') {
            c = '+'
          }
        }
        result += c
      }
      result += "\n"
    }
    result
  }
  
  override val part1Answer: Int = checksum.head._2
  override val part2Answer: String = calculatePart2()
}


