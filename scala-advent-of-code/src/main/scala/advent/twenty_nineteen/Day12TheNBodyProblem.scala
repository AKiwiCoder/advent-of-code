package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

case class Body(x: Int, y: Int, z: Int)

case class Velocity(x: Int, y: Int, z: Int)

class Day12TheNBodyProblem(filename: String, steps: Int) extends DailyProblem[Int, Long] {

  private val linePattern = "<x=([-0-9]+), y=([-0-9]+), z=([-0-9]+)>".r

  private def parser(line: String): (Body, Velocity) = {
    line match {
      case linePattern(a, b, c) => (Body(a.toInt, b.toInt, c.toInt), Velocity(0, 0, 0))
    }
  }

  private val moons = FileUtilities.readFile(filename, parser).zipWithIndex.map(a => a._2 -> a._1).toMap

  def ifladder(m: Int, t: Int): Int = {
    if (m > t) {
      -1
    } else if (m < t) {
      1
    } else {
      0
    }
  }

  def calculate(velocity: Velocity, me: Body, them: Body): Velocity = {
    val x = velocity.x + ifladder(me.x, them.x)
    val y = velocity.y + ifladder(me.y, them.y)
    val z = velocity.z + ifladder(me.z, them.z)
    Velocity(x, y, z)
  }

  private def doStep(input: Map[Int, (Body, Velocity)]): Map[Int, (Body, Velocity)] = {
    var bodies = input
    val pairs = bodies.keySet.toList.combinations(2).toList
    for (b <- pairs) {

      val b1 = b(0)
      val b2 = b(1)

      val b1p = bodies(b1)._1
      val b2p = bodies(b2)._1
      val b1v = bodies(b1)._2
      val b2v = bodies(b2)._2

      val nb1v = calculate(b1v, b1p, b2p)
      val nb2v = calculate(b2v, b2p, b1p)

      bodies = bodies + (b1 -> (bodies(b1)._1, nb1v))
      bodies = bodies + (b2 -> (bodies(b2)._1, nb2v))
    }

    bodies.map(a => (a._1, (Body(a._2._1.x + a._2._2.x, a._2._1.y + a._2._2.y, a._2._1.z + a._2._2.z), a._2._2)))
  }

  private def calculateEnergy(input: Map[Int, (Body, Velocity)]): Int = {
    var working = input
    for (step <- 0 until steps) {
      working = doStep(working)
    }
    val energy = working.values.map(a => (Math.abs(a._1.x) + Math.abs(a._1.y) + Math.abs(a._1.z)) * ((Math.abs(a._2.x) + Math.abs(a._2.y) + Math.abs(a._2.z)))).sum
    energy
  }

  private def gen(i: Int, bodies: List[(Body, Velocity)]): (Int, Int, Int, Int) = {
    i match {
      case 0 => (bodies(0)._1.x, bodies(1)._1.x, bodies(2)._1.x, bodies(3)._1.x)
      case 1 => (bodies(0)._1.y, bodies(1)._1.y, bodies(2)._1.y, bodies(3)._1.y)
      case 2 => (bodies(0)._1.z, bodies(1)._1.z, bodies(2)._1.z, bodies(3)._1.z)
      case 3 => (bodies(0)._2.x, bodies(1)._2.x, bodies(2)._2.x, bodies(3)._2.x)
      case 4 => (bodies(0)._2.y, bodies(1)._2.y, bodies(2)._2.y, bodies(3)._2.y)
      case 5 => (bodies(0)._2.z, bodies(1)._2.z, bodies(2)._2.z, bodies(3)._2.z)
    }
  }

  private def lcm(list: Seq[Long]): Long = list.foldLeft(1: Long) {
    (a, b) => b * a / Stream.iterate((a, b)) { case (x, y) => (y, x % y) }.dropWhile(_._2 != 0).head._1.abs
  }
  
  private def generateHashCode(i: Int, working: List[(Body, Velocity)]): ((Int, Int, Int, Int), (Int, Int, Int, Int)) = {
    (gen(i, working), gen(i + 3, working))
  }

  private def calculateCycles(input: Map[Int, (Body, Velocity)]): Long = {
    var cycles = Map[Int, Int]()
    var seen = Map[Int, Set[((Int, Int, Int, Int), (Int, Int, Int, Int))]]().withDefaultValue(Set())
    var cycle = 0
    var working = input
    while (cycles.size != 3) {
      val h0 = generateHashCode(0, working.values.toList)
      val h1 = generateHashCode(1, working.values.toList)
      val h2 = generateHashCode(2, working.values.toList)

      if (seen(0).contains(h0) && !cycles.contains(0))
        cycles = cycles + (0 -> cycle)
      seen = seen + (0 -> (seen(0) + h0))

      if (seen(1).contains(h1) && !cycles.contains(1))
        cycles = cycles + (1 -> cycle)
      seen = seen + (1 -> (seen(1) + h1))

      if (seen(2).contains(h2) && !cycles.contains(2))
        cycles = cycles + (2 -> cycle)
      seen = seen + (2 -> (seen(2) + h2))

      cycle += 1
      working = doStep(working)
    }
    lcm(cycles.values.map(i => i.toLong).toList)
  }

  override val part1Answer: Int = calculateEnergy(moons)
  override val part2Answer: Long = calculateCycles(moons)
}


