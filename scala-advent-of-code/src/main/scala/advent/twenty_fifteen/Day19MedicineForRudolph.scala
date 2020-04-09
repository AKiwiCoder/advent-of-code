package advent.twenty_fifteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day19MedicineForRudolph(filename: String) extends DailyProblem[Int, Int] {
  private val replacement = "([A-Za-z]+) => ([A-Za-z]+)".r

  private val fileContents = FileUtilities.readFile(filename)

  private def parse(line: String): (String, String) =
    line match {
      case replacement(from, to) => (from, to)
      case _ => throw new IllegalArgumentException(s"Cannot parse '$line'")
    }

  private val input = fileContents.drop(fileContents.size - 1)(0)
  private val replacements = fileContents.take(fileContents.size - 2).foldLeft(List[(String, String)]())((acc, line) => parse(line) :: acc)

  private def calculatePartOne(): Int = {
    @tailrec
    def process(replacements: List[(String, String)], acc: Set[String]): Set[String] = {
      @tailrec
      def search(from: String, to: String, index: Int, molecules: Set[String]): Set[String] = {
        val loc = input.indexOf(from, index)
        if (loc < 0) {
          molecules
        } else {
          val molecule: String = input.substring(0, loc) + to + input.substring(loc + from.length)
          search(from, to, loc + 1, molecules + molecule)
        }
      }

      if (replacements.size == 0) {
        acc
      } else {
        process(replacements.tail, acc ++ search(replacements.head._1, replacements.head._2, 0, Set[String]()))
      }
    }

    val molecules = process(replacements, Set())
    molecules.size
  }

  private def calculatePartTwo(): Int = {
    def calculate(replacements: List[(String, String)], input: String): Int = {
      var steps = 0
      var current = input
      var skipElectronReplacements = true
      while (!current.equals("e")) {
        var replacementMade = false
        for (replacement <- replacements) {
          if (!(skipElectronReplacements && replacement._1.equals("e"))) {
            val index = current.lastIndexOf(replacement._2)
            if (index >= 0) {
              current = current.substring(0, index) + replacement._1 + current.substring(index + replacement._2.length)
              steps += 1
              replacementMade = true
            }
          }
        }
        if (skipElectronReplacements && !replacementMade) skipElectronReplacements = false
      }
      steps
    }

    calculate(replacements, input)
  }

  /*
   private int calculatePartTwo(List<Pair<String, String>> replacements, String input) {
        int steps = 0;

        String current = input;
        boolean skipElectronReplacements = true;
        while (!current.equals("e")) {
            boolean replacementMade = false;

            for (Pair<String, String> replacement : replacements) {
                String text = replacement.getFirst();
                String search = replacement.getSecond();

                if (skipElectronReplacements && text.equals("e")) {
                    continue;
                }

                int index = current.lastIndexOf(search);
                if (index >= 0) {
                    current = current.substring(0, index) + text + current.substring(index + search.length());
                    steps++;
                    replacementMade = true;
                }
            }

            if (skipElectronReplacements && !replacementMade) {
                skipElectronReplacements = false;
            }
        }

        return steps;
    }

   */


  override val part1Answer: Int = calculatePartOne()
  override val part2Answer: Int = calculatePartTwo()
}
