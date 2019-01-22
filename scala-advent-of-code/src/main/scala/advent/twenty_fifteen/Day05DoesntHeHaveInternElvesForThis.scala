package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day05DoesntHeHaveInternElvesForThis(filename: String) extends DailyProblem[Int, Int] {
  private val lines = FileUtilities.readFile(filename);

  private val vowelSet = Set('a', 'e', 'i', 'o', 'u')
  private val naughty = Set(('a', 'b'), ('c', 'd'), ('p', 'q'), ('x', 'y'))

  private def nice1(line: String): Boolean = {
    val charList = line.toCharArray
    val pairs = charList.take(charList.size - 1).zip(charList.tail)

    val niceVowels = charList.filter(c => vowelSet.contains(c)).size >= 3
    val nicePairs = pairs.exists(p => p._1 == p._2)
    val naughtyPairs = pairs.exists(p => naughty.contains(p))

    niceVowels && nicePairs && !naughtyPairs
  }

  private def nice2(line: String): Boolean = {
    @tailrec
    def hasPairOfPair(working : String) : Boolean = {
      if (working.size <= 3) {
        return false
      }
      if (working.indexOf(working.take(2), 2) >= 0) {
        return true
      }
      hasPairOfPair(working.tail)
    }

    @tailrec
    def hasTriple(working : String) : Boolean = {
      if (working.size <= 2) {
        return false
      }
      if (working.charAt(0) == working.charAt(2) && working.charAt(0) != working.charAt(1)) {
        return true
      }
      hasTriple(working.tail)
    }

    hasPairOfPair(line) && hasTriple(line)
  }

  private val counts = lines.foldLeft((0, 0))((acc, line) => (if (nice1(line)) acc._1 + 1 else acc._1, if (nice2(line)) acc._2 + 1 else acc._2))

  override val part1Answer = counts._1
  override val part2Answer = counts._2
}