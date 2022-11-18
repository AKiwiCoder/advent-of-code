package advent.twenty_twentyone 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day10SyntaxScoring(filename : String) extends DailyProblem[Int, Long] {

  private val input = FileUtilities.readFile(filename)

  def validate(line : String) : (Int, List[Char]) = {
    @tailrec
    def process(characters : List[Char], stack : List[Char], error : Option[Char]) : (Int, List[Char]) = {
      if (error.isDefined) {
        error.get match  {
          case ')' => (3, stack)
          case ']' => (57, stack)
          case '}' => (1197, stack)
          case '>' => (25137, stack)
        }
      } else if (characters.isEmpty) {
        (0, stack)
      } else {
        val (newStack, error) = characters.head match {
          case ')' => if (stack.head == '(') (stack.tail, None) else (List(), Some(characters.head))
          case ']' => if (stack.head == '[') (stack.tail, None) else (List(), Some(characters.head))
          case '}' => if (stack.head == '{') (stack.tail, None) else (List(), Some(characters.head))
          case '>' => if (stack.head == '<') (stack.tail, None) else (List(), Some(characters.head))
          case '(' => ('(' :: stack, None)
          case '[' => ('[' :: stack, None)
          case '{' => ('{' :: stack, None)
          case '<' => ('<' :: stack, None)
        }
        process(characters.tail, newStack, error)
      }
    }

    process(line.toCharArray.toList, List(), None)
  }

  def scoreMissing(stack : List[Char]): Long = {
    stack.foldLeft(0L)((acc, c) => {
      c match {
        case '(' => acc * 5 + 1
        case '[' => acc * 5 + 2
        case '{' => acc * 5 + 3
        case '<' => acc * 5 + 4
      }
    })
  }

  private val processed = input.map(validate)

  private val partTwo = processed.filter(_._1 == 0).map(e => scoreMissing(e._2)).sorted

  println(partTwo)

  override val part1Answer: Int = processed.filter(_._1 > 0).map(_._1).sum
  override val part2Answer: Long = partTwo(partTwo.length / 2)
}


