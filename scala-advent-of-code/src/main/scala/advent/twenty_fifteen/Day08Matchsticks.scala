package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day08Matchsticks(filename: String) extends DailyProblem[Int, Int] {

  private val input = FileUtilities.readFile(filename)

  @tailrec
  final def encode(input: String, working: String): String = {
    if (input.isEmpty) {
      "\"" + working + "\""
    } else {
      encode(input.tail, working + (input.head match {
        case '\\' => "\\\\"
        case '"' => "\\\""
        case c => c.toString
      }))
    }
  }

  @tailrec
  final def decode(input: String, working: String): String = {
    if (input.isEmpty) {
      working
    } else if (input.startsWith("\"")) {
      decode(input.tail, working)
    } else if (input.startsWith("\\\\")) {
      decode(input.drop(2), working + "\\")
    } else if (input.startsWith("\\\"")) {
      decode(input.drop(2), working + "\"")
    } else if (input.startsWith("\\x")) {
      val hex = Integer.parseInt(input.slice(2, 4), 16).intValue.toChar
      decode(input.drop(4), working + hex)
    } else {
      decode(input.tail, working + input.head)
    }
  }

  private val inputLength = input.map(line => line.length).sum
  private val encodedLength = input.map(line => encode(line, "").length).sum
  private val decodedLength = input.map(line => decode(line, "").length).sum

  override val part1Answer: Int = inputLength - decodedLength
  override val part2Answer: Int = encodedLength - inputLength
}
