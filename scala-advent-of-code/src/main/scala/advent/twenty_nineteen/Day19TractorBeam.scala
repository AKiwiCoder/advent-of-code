package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.Point

class Day19TractorBeam(filename: String) extends DailyProblem[Int, Int] {
  private val program = IntComputer.loadProgram(filename)

  var count = 0
  for (y <- 0 until 50) {
    for (x <- 0 until 50) {
      val state = IntComputer.execute(IntComputerState(program, 0, 0, List(x, y), List()))
      if (state.output.head == 1) {
        count = count + 1
      }
    }
  }

  val maxX = 2000
  val maxY = 2000

//  var map: Set[Point] = Set()
//  println("Building Map")
//  for (y <- 0 until maxY) {
//    for (x <- 0 until maxX) {
//      val state = IntComputer.execute(IntComputerState(program, 0, 0, List(x, y), List()))
//      if (state.output.head == 1) {
//        map = map + Point(y, x)
//      }
//    }
//    println(y)
//  }

  println("Scanning Map")
  for (y <- 1080 until maxY) {
    var skip = false
    for (x <- 0 until maxX) {
      if (!skip) {
        val state1 = IntComputer.execute(IntComputerState(program, 0, 0, List(x, y), List()))
        if (state1.output.head == 1) {
          val state2 = IntComputer.execute(IntComputerState(program, 0, 0, List(x + 99, y), List()))
          if (state2.output.head == 1) {
            val state3 = IntComputer.execute(IntComputerState(program, 0, 0, List(x, y + 99), List()))
            if (state3.output.head == 1) {
              val state4 = IntComputer.execute(IntComputerState(program, 0, 0, List(x + 99, y + 99), List()))
              if (state4.output.head == 1) {
                println("Found : " + y + " " + x + " " + (10000 * x + y))
              }
            }
          } else {
            skip = true
          }
        }
      }
    }
    println(y)
  }

  override val part1Answer: Int = count
  override val part2Answer: Int = 0
}


