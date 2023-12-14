package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.collection.mutable

class Day12HotSprings(filename: String) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename).map(line => {
    val bits = line.split(" ")
    (bits(0).trim, bits(1).trim.split(",").map(_.toInt).toList)
  })

  private val cache = mutable.HashMap[(String, List[Int]), Long]()

  private def countValidPatterns(text: String, springs: List[Int]): Long = {
    val cached = cache.get((text, springs))

    if (cached.isDefined) {
      cached.get
    } else {
      val cleaned = text.dropWhile(_ == '.')
      if (cleaned.isEmpty) {
        if (springs.isEmpty) 1 else 0
      } else if (springs.isEmpty) {
        if (cleaned.exists(_ == '#')) 0 else 1
      } else if (cleaned(0) == '#') {
        if (cleaned.length < springs.head || cleaned.substring(0, springs.head).contains('.')) {
          0
        } else if (cleaned.length == springs.head) {
          if (springs.length == 1) 1 else 0
        } else if (cleaned(springs.head) == '#') {
          0
        } else {
          val result = countValidPatterns(cleaned.substring(springs.head + 1), springs.tail)
          cache((cleaned.substring(springs.head + 1), springs.tail)) = result
          result
        }
      } else {
        val result = countValidPatterns('#' + cleaned.tail, springs) + countValidPatterns(cleaned.tail, springs)
        cache((text, springs)) = result
        result
      }
    }
  }

  private val partTwoInput = input.map(entry => (entry._1 + "?" + entry._1 + "?" + entry._1 + "?" + entry._1 + "?" + entry._1, entry._2 ::: entry._2 ::: entry._2 ::: entry._2 ::: entry._2))

  override val part1Answer: Long = input.map(e => countValidPatterns(e._1, e._2)).sum
  override val part2Answer: Long = partTwoInput.map(e => countValidPatterns(e._1, e._2)).sum
}

