package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

class Day09ExplosivesInCyberspace(filename: String) extends DailyProblem[Long, Long] {

  private val lines = FileUtilities.readFile(filename)

  private def decompress(line : String, expandSubstring : Boolean) : Long = {
    if (line.isEmpty) {
      0
    } else {
      val markerStart = line.indexOf('(')
      if (markerStart < 0) {
        line.length
      } else if (markerStart == 0) {
        val xpos = line.indexOf('x')
        val length = line.substring(1, xpos).toInt
        val markerClose = line.indexOf(')')
        val count = line.substring(xpos+1, markerClose).toInt

        val substring = line.drop(markerClose + 1).take(length)
        if (expandSubstring && substring.contains("(")) {
          decompress(substring, expandSubstring) * count + decompress(line.drop(markerClose + 1 + length), expandSubstring)
        } else {
          length * count + decompress(line.drop(markerClose + 1 + length), expandSubstring)
        }
      } else {
        markerStart + decompress(line.drop(markerStart), expandSubstring)
      }
    }
  }

  override val part1Answer: Long = lines.map(line => decompress(line, false)).sum
  override val part2Answer: Long = lines.map(line => decompress(line, true)).sum
}
