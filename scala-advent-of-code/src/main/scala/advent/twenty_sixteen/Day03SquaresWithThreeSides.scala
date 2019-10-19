package advent.twenty_sixteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

class Day03SquaresWithThreeSides(filename: String) extends DailyProblem[Int, Int] {

  case class Triangle(a: Int, b: Int, c: Int) {
    def isValid(): Boolean =
      if (a > b && a > c) a < b + c //
      else if (b > a && b > c) b < a + c //
      else c < a + b
  }

  private val lines = FileUtilities.readFile(filename)

  private val linePattern = "\\s*([0-9]+)\\s*([0-9]+)\\s*([0-9]+)".r

  private val triangleData = lines.map(line => line match {
    case linePattern(a, b, c) => (a.toInt, b.toInt, c.toInt)
  })

  private val possibleTrianglesPart1 = triangleData.map(row => Triangle(row._1, row._2, row._3))

  private val possibleTrianglesPart2 = for {i <- 0.to(triangleData.size - 1).by(3)
                                            row1 = triangleData(i)
                                            row2 = triangleData(i + 1)
                                            row3 = triangleData(i + 2)}
    yield List(Triangle(row1._1, row2._1, row3._1), Triangle(row1._2, row2._2, row3._2), Triangle(row1._3, row2._3, row3._3))

  override val part1Answer: Int = possibleTrianglesPart1.filter(t => t.isValid()).size
  override val part2Answer: Int = possibleTrianglesPart2.flatten.filter(t => t.isValid()).size
}
