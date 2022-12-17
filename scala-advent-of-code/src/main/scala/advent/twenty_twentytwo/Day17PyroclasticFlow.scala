package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day17PyroclasticFlow(filename: String) extends DailyProblem[Int, Long] {

  "@@@@"

  " @ "
  "@@@"
  " @ "

  "  @"
  "  @"
  "@@@"

  "@"
  "@"
  "@"
  "@"

  "@@"
  "@@"

  private val input = FileUtilities.readFile(filename)(0).trim.toCharArray.toList

  def expandBoardIfNeeded(old: List[String]) : List[String] = {
    "       " :: "       " :: "       " :: old.dropWhile(_.isBlank)
  }

  private def addRock(rock : Long, board : List[String]) : List[String] = {
    rock match {
      case 0 => "  @@@@ " :: board
      case 1 => "   @   " :: "  @@@  " :: "   @   " :: board
      case 2 => "    @  " :: "    @  " :: "  @@@  " :: board
      case 3 => "  @    " :: "  @    " :: "  @    " :: "  @    " :: board
      case 4 => "  @@   " :: "  @@   " :: board
    }
  }

  def isOkToMoveInto(c : Char) : Boolean = {
    c == '@' || c == ' '
  }

  def canMoveLeft(value: List[String]):Boolean = {
    !value.exists(l => (l(0) == '@') || (1 until 7).exists(idx => l(idx) == '@' && !isOkToMoveInto(l(idx - 1))))
  }

  def canMoveRight(value: List[String]): Boolean = {
    !value.exists(l => (l(6) == '@') || (0 until 6).exists(idx => l(idx) == '@' && !isOkToMoveInto(l(idx + 1))))
  }

  def canMoveDown(value: List[String]): Boolean = {
    val lastRowToConsider = value.lastIndexWhere(l => l.contains("@"))

    if (lastRowToConsider == value.size - 1)
      false
    else if (!value.exists(_.contains("@")))
      false
    else
    !value.take(lastRowToConsider+1).zip(value.tail).exists(pair => pair._1.indices.exists(idx => pair._1(idx) == '@' && !isOkToMoveInto(pair._2(idx))))
  }

  def moveLeft(value: List[String]): List[String] = {
    value.map(l => (0 until 7).map(idx =>{
      l(idx) match {
        case '#' => '#'
        case ' ' | '@' => if (idx  == 6) ' ' else l(idx + 1) match {
          case '@' => '@'
          case ' ' | '#' => ' '
        }
      }
    }).mkString)
  }

  def moveRight(value: List[String]): List[String] = {
    value.map(l => (0 until 7).map(idx => {
      l(idx) match {
        case '#' => '#'
        case ' ' | '@' => if (idx == 0) ' ' else l(idx - 1) match {
          case '@' => '@'
          case '#'|' ' => ' '
        }
      }
    }).mkString)
  }

  def moveDown(value: List[String]): List[String] = {
    value.indices.map(idx => {
      (0 until 7).map(c => {
        if (idx == 0) {
          value(idx)(c) match {
            case ' '|'@' => ' '
            case '#' => '#'
          }
        } else {
          value(idx)(c) match {
            case '#' => '#'
            case _ => value(idx-1)(c) match {
              case '@' => '@'
              case '#' => value(idx)(c)
              case ' ' => ' '
            }
          }
        }
      } ).mkString
    }).toList
  }

  @tailrec
  private def moveRockDown(commands : List[Char], board : List[String]) : (List[Char], List[String]) = {
    val newCommands = if (commands.isEmpty) input else commands
    val newCommand = newCommands.head

    val newBoard = newCommand match {
      case '<' => if (canMoveLeft(board)) moveLeft(board) else board
      case '>' => if (canMoveRight(board)) moveRight(board) else board
    }

    if (canMoveDown(newBoard)) {
      moveRockDown(newCommands.tail, moveDown(newBoard))
    } else {
      (newCommands.tail, newBoard.map(l => l.replace('@', '#')))
    }
  }


  private final val CUT = 30
  @tailrec
  private def playTetris(count : Long, max : Long, rock : Long, commands : List[Char], board : List[String], add : Int) : Int = {
    if (count == max) {
      board.size + add
    } else {
      val newAdd = if (board.size > CUT) add + board.size - CUT else add
      val trimmedBoard = if (board.size > CUT) board.take(CUT) else board

      val newBoard = expandBoardIfNeeded(trimmedBoard)
      val boardWithRocks = addRock(rock, newBoard)

      val next = moveRockDown(commands, boardWithRocks)
      playTetris(count + 1,max, (count + 1) % 5, next._1, next._2, newAdd)
    }
  }

  @tailrec
  private def playTetris2(count: Long, max: Long, rock: Long, commands: List[Char], board: List[String], add : Long, pattern : Map[(Long,Long), Long]): Long = {
    if (count % (5 * input.size) == 0) {
      println(count + " " + (board.size + add))
    }

    val newAdd = if (board.size > CUT) add + board.size - CUT else add
    val trimmedBoard = if (board.size > CUT) board.take(CUT) else board

    val newBoard = expandBoardIfNeeded(trimmedBoard)
    val boardWithRocks = addRock(rock, newBoard)

    val next = moveRockDown(commands, boardWithRocks)
    playTetris2(count + 1, max, (count + 1) % 5, next._1, next._2, newAdd, pattern)
  }


  override val part1Answer: Int = playTetris(0L, 2022L, 0, input, List(), 0)
  override val part2Answer: Long = 0 //playTetris2(0L, 1000000000000L, 0, input, List(), 0, Map())
}
