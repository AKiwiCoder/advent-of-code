package advent.twenty_twentyone 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day24ArithmeticLogicUnit extends DailyProblem[Long, Long] {
  // add x
  private val CONST_1 = List(14, 13, 13, 12, -12, 12, -2, -11, 13, 14, 0, -12, -13, -6)
  // div z
  private val CONST_2 = List(1, 1, 1, 1, 26, 1, 26, 26, 1, 1, 26, 26, 26, 26)
  // add y (every 3rd line)
  private val CONST_3 = List( 8, 8, 3, 10, 8, 8, 8, 5, 9, 3, 4, 9, 2, 7)

  def block(inputPos : Int, z : Int, w : Int) : Int = {
    val x = CONST_1(inputPos) + (z % 26)
    val z1 = z / CONST_2(inputPos)
    if (x != w) {
      (z1 * 26) + w + CONST_3(inputPos)
    } else {
      z1
    }
  }

  private var tick = 0

  def isValid(number : Array[Int]) : Boolean = {
    tick = tick + 1
    if (tick % 1000000 == 0) {
      println("Tick: " + number.foldLeft("")((acc, num) => acc + num))
    }
    number.zipWithIndex.foldLeft(0)((z, input) => block(input._2, z, input._1)) == 0
  }

//  val digits : Array[Int] = Array(9, 8, 7, 6 ,5, 4, 3, 2 , 1)
//
//    for (
//      a <- 2 to 8;
//      b <- 0 to 8;
//      c <- 0 to 8;
//      d <- 0 to 8;
//      e <- 2 to 8;
//      f <- 6 to 8;
//      g <- 0 to 8;
//      h <- 0 to 8;
//      i <- 0 to 8;
//      j <- 0 to 8;
//      k <- 0 to 8;
//      l <- 0 to 8;
//      m <- 0 to 8;
//      n <- 0 to 8) {
//      val number = Array(digits(a),digits(b),digits(c),digits(d),digits(e),digits(f),digits(g),digits(h),digits(i),digits(j),digits(k),digits(l),digits(m),digits(n))
//      if (isValid(number))
//          println("ANSWER: " + number.foldLeft("")((acc, num) => acc + num))
//    }

//    val digits : Array[Int] = Array(1, 2, 3, 4 ,5, 6, 7, 8 , 9)
//
//      for (
//        a <- 0 to 8;
//        b <- 5 to 8;
//        c <- 8 to 8;
//        d <- 2 to 8;
//        e <- 0 to 8;
//        f <- 0 to 8;
//        g <- 6 to 8;
//        h <- 0 to 8;
//        i <- 0 to 8;
//        j <- 0 to 8;
//        k <- 0 to 8;
//        l <- 0 to 8;
//        m <- 0 to 8;
//        n <- 0 to 8) {
//        val number = Array(digits(a),digits(b),digits(c),digits(d),digits(e),digits(f),digits(g),digits(h),digits(i),digits(j),digits(k),digits(l),digits(m),digits(n))
//        if (isValid(number))
//            println("ANSWER: " + number.foldLeft("")((acc, num) => acc + num))
//      }

  println(isValid(Array(7,9,9,9,7,3,9,1,9,6,9,6,4,9)))
  println(isValid(Array(1,6,9,3,1,1,7,1,4,1,4,1,1,3)))

  override val part1Answer: Long = 79997391969649L
  override val part2Answer: Long = 16931171414113L
}


