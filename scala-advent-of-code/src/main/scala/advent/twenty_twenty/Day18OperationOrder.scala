package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec
import scala.util.matching.Regex

class Day18OperationOrder(filename: String) extends DailyProblem[Long, Long] {
  abstract class Token
  case class Number(num: Long) extends Token
  case class Operator(op: Char) extends Token
  case class LeftBracket() extends Token
  case class RightBracket() extends Token

  val digit: Regex = "[0-9]".r
  val operator: Regex = "([+-/*])".r

  def tokenize(line: List[Char], working: String, acc: List[Token]): List[Token] = {
    if (line.isEmpty) {
      if (working.isEmpty) {
        acc
      }
      else {
        Number(working.toInt) :: acc
      }
    } else {
      line.head match {
        case digit() => tokenize(line.tail, working + line.head, acc)
        case '(' => tokenize(line.tail, "", LeftBracket()  :: acc)
        case ')' => tokenize(line.tail, "", if (working.isEmpty) RightBracket() :: acc else RightBracket()  :: Number(working.toInt) :: acc)
        case operator(op) => tokenize(line.tail, "", Operator(op) :: acc)
        case ' ' => if (working.isEmpty) tokenize(line.tail, "", acc) else tokenize(line.tail, "", Number(working.toInt) :: acc)
      }
    }
  }

  def parse(line : String) : List[Token] = tokenize(line.toList, "", List()).reverse

  def evaluate(rpn : List[Token], stack : List[Number]) : Long = {
    if (rpn.isEmpty) {
      stack.head.num
    } else {
      val token = rpn.head
      token match {
        case Number(n) => evaluate(rpn.tail, Number(n) :: stack)
        case Operator(o) => o match {
          case '+' => evaluate(rpn.tail, Number(stack.head.num + stack(1).num) :: stack.drop(2))
          case '-' =>evaluate(rpn.tail, Number(stack.head.num - stack(1).num) :: stack.drop(2))
          case '/' =>evaluate(rpn.tail, Number(stack.head.num / stack(1).num) :: stack.drop(2))
          case '*' =>evaluate(rpn.tail, Number(stack.head.num * stack(1).num) :: stack.drop(2))
        }
      }
    }
  }

  def equalsPrecedence(target: Token, current : Token): Boolean = true

  def mixedPrecedence(target: Token, current : Token): Boolean = {
    val cOp = current.asInstanceOf[Operator]
    val tOp = target.asInstanceOf[Operator]
    if ((tOp.op == '+' || tOp.op == '-') && (cOp.op == '+' || cOp.op == '-')) {
      true
    } else if ((tOp.op == '*' || tOp.op == '/') && (cOp.op == '*' || cOp.op == '/')) {
      true
    } else if ((tOp.op == '+' || tOp.op == '-') && (cOp.op == '*' || cOp.op == '/')) {
      false
    } else {
      true
    }
  }

  @tailrec
   private def rpn(tokens: List[Token], output: List[Token], operators: List[Token], precedence: (Token,Token) => Boolean): List[Token] = {
    if (tokens.isEmpty) {
      if (operators.isEmpty) {
        (output).reverse
      } else {
        (operators.reverse ::: output).reverse
      }
    } else {
      val token = tokens.head
      token match {
        case Number(_) => rpn(tokens.tail, token :: output, operators, precedence)
        case Operator(_) => {
          val toPop = operators.takeWhile(operator => !operator.isInstanceOf[LeftBracket] && precedence(token, operator)).reverse
          val newOperators = operators.drop(toPop.size)
          rpn(tokens.tail, toPop ::: output, token :: newOperators, precedence)
        }
        case LeftBracket() => rpn(tokens.tail, output, token :: operators, precedence)
        case RightBracket() => {
          val inside = operators.takeWhile(operator => !operator.isInstanceOf[LeftBracket]).reverse
          val newOperators = operators.drop(inside.size + 1)
          rpn(tokens.tail, inside ::: output, newOperators, precedence)
        }
      }
    }
  }

  private val input = FileUtilities.readFile(filename, parse)

  println("Input: " + input)

  override val part1Answer: Long = input.map(tokens => evaluate(rpn(tokens, List(), List(), equalsPrecedence), List())).sum
  override val part2Answer: Long = input.map(tokens => evaluate(rpn(tokens, List(), List(), mixedPrecedence), List())).sum
}


