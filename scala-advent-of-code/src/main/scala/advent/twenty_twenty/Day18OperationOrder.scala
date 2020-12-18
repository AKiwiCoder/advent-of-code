package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day18OperationOrder(filename: String) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename)

  abstract class Token
  case class Number(num: Long) extends Token
  case class Operator(op: Char) extends Token
  case class LeftBracket() extends Token
  case class RightBracket() extends Token

  val digit = "[0-9]".r
  val operator = "([+-/*])".r

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

  def evaluate(rpn : List[Token], stack : List[Number]) : Long = {
    if (rpn.isEmpty) {
      stack.head.num
    } else {
      val token = rpn.head
      token match {
        case Number(n) => evaluate(rpn.tail, Number(n) :: stack)
        case Operator(o) => o match {
          case '+' => evaluate(rpn.tail, Number(stack(0).num + stack(1).num) :: stack.drop(2))
          case '-' =>evaluate(rpn.tail, Number(stack(0).num - stack(1).num) :: stack.drop(2))
          case '/' =>evaluate(rpn.tail, Number(stack(0).num / stack(1).num) :: stack.drop(2))
          case '*' =>evaluate(rpn.tail, Number(stack(0).num * stack(1).num) :: stack.drop(2))
        }
      }
    }
  }

  def process(line: String): Long = {
    @tailrec
    def rpn(tokens: List[Token], output: List[Token], operators: List[Token]): List[Token] = {
      if (tokens.isEmpty) {
        if (operators.isEmpty) {
          output
        } else {
          operators ::: output
        }
      } else {
        val token = tokens.head
        token match {
          case Number(_) => rpn(tokens.tail, token :: output, operators)
          case Operator(_) => {
            val toPop = operators.takeWhile(operator => !operator.isInstanceOf[LeftBracket])
            val newOperators = operators.drop(toPop.size)
            rpn(tokens.tail, toPop ::: output, token :: newOperators)
          }
          case LeftBracket() => rpn(tokens.tail, output, token :: operators)
          case RightBracket() => {
            val inside = operators.takeWhile(operator => !operator.isInstanceOf[LeftBracket])
            val newOperators = operators.drop(inside.size + 1)
            rpn(tokens.tail, inside ::: output, newOperators)
          }
        }
      }
    }

    val tokens = tokenize(line.toList, "", List()).reverse
    val rpnExpr = rpn(tokens, List(), List()).reverse
    evaluate(rpnExpr, List())
  }


  def process2(line: String): Long = {
    def shouldPopToken(target: Token, current : Token): Boolean = {
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
    def rpn(tokens: List[Token], output: List[Token], operators: List[Token]): List[Token] = {
      if (tokens.isEmpty) {
        if (operators.isEmpty) {
          output
        } else {
          operators.reverse ::: output
        }
      } else {
        val token = tokens.head
        token match {
          case Number(_) => rpn(tokens.tail, token :: output, operators)
          case Operator(_) => {
            val toPop = operators.takeWhile(operator => !operator.isInstanceOf[LeftBracket] && shouldPopToken(token, operator)).reverse
            val newOperators = operators.drop(toPop.size)
            rpn(tokens.tail, toPop ::: output, token :: newOperators)
          }
          case LeftBracket() => rpn(tokens.tail, output, token :: operators)
          case RightBracket() => {
            val inside = operators.takeWhile(operator => !operator.isInstanceOf[LeftBracket]).reverse
            val newOperators = operators.drop(inside.size + 1)
            rpn(tokens.tail, inside ::: output, newOperators)
          }
        }
      }
    }

    val tokens = tokenize(line.toList, "", List()).reverse
    val rpnExpr = rpn(tokens, List(), List()).reverse
    evaluate(rpnExpr, List())
  }

  private def part1(): Long = {
    input.map(line => process(line)).sum
  }

  private def part2(): Long = {
    input.map(line => process2(line)).sum
  }

  override val part1Answer: Long = part1()
  override val part2Answer: Long = part2()
}


