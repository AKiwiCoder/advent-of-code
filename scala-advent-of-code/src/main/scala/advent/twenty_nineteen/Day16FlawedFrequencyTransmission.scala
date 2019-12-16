package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day16FlawedFrequencyTransmission(filename: String, steps: Int) extends DailyProblem[String, String] {
  private val signal = FileUtilities.readFile(filename)(0).toList.map(t => Integer.parseInt("" + t))

  private def generatePattern(index: Int, length: Int): List[Int] = {
    val a = List.fill(index + 1)(List(0)).flatten
    val b = List.fill(index + 1)(List(1)).flatten
    val c = List.fill(index + 1)(List(0)).flatten
    val d = List.fill(index + 1)(List(-1)).flatten
    val group = a ::: b ::: c ::: d
    val pattern = List.fill((length / group.size) + 1)(group).flatten
    pattern.tail
  }

  @tailrec
  private def fftStep(index: Int, input: List[Int], output: List[Int], patterns: Map[Int, List[Int]]): List[Int] = {
    if (input.isEmpty) {
      output
    } else {
      val pattern = patterns(index)
      val out = Math.abs(input.zip(pattern).map(e => e._1 * e._2).sum % 10)
      fftStep(index + 1, input.tail, output ::: out :: Nil, patterns)
    }
  }

  private def part2Faster(previous: Array[Int], output: Array[Int]): Array[Int] = {
    output(previous.size - 1) = previous(previous.size - 1)
    for (idx <- previous.size - 2 to 0 by -1) {
      output(idx) = (previous(idx) + output(idx + 1)) % 10
    }
    output
  }

  def doPart1(initialValue: List[Int]): String = {
    val p1Patterns = (0 to initialValue.size).map(index => (index -> generatePattern(index, initialValue.size).drop(index))).toMap
    var working = initialValue
    for (step <- 0 until steps) {
      working = fftStep(0, working, List(), p1Patterns)
    }
    working.mkString.substring(0, 8)
  }


  def doPart2(initialValue: List[Int]): String = {
    val offset = initialValue.take(7).mkString.toInt
    val part2Working = List.fill(10000)(initialValue).flatten.drop(if (offset > (initialValue.size * 10000)) 0 else offset)

    var input = part2Working.toArray
    var output = Array.ofDim[Int](input.size)
    for (step <- 0 until steps) {
      output = part2Faster(input, output)
      input = output
      output = input
    }
    output.mkString.substring(0, 8)
  }

  override val part1Answer: String = doPart1(signal)
  override val part2Answer: String = doPart2(signal)
}


