package advent.twenty_nineteen

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

case class Body(x: Int, y: Int, z: Int) {
  def calculateEnergy(): Int = {
    Math.abs(x) + Math.abs(y) + Math.abs(z)
  }
}

case class Velocity(x: Int, y: Int, z: Int) {
  def calculateEnergy(): Int = {
    Math.abs(x) + Math.abs(y) + Math.abs(z)
  }
}

class Day12TheNBodyProblem(filename: String, steps: Int) extends DailyProblem[Int, Long] {

  private val linePattern = "<x=([-0-9]+), y=([-0-9]+), z=([-0-9]+)>".r

  private def parser(line: String): (Body, Velocity) = {
    line match {
      case linePattern(a, b, c) => (Body(a.toInt, b.toInt, c.toInt), Velocity(0, 0, 0))
    }
  }

  private val moons = FileUtilities.readFile(filename, parser).zipWithIndex.map(a => a._2 -> a._1).toMap

  def applyGravity(m: Int, t: Int): Int = if (m > t) -1 else if (m < t) 1 else 0

  def calculateGravity(velocity: Velocity, me: Body, them: Body): Velocity = {
    val x = velocity.x + applyGravity(me.x, them.x)
    val y = velocity.y + applyGravity(me.y, them.y)
    val z = velocity.z + applyGravity(me.z, them.z)
    Velocity(x, y, z)
  }

  def calculateGravity(me: (Body, Velocity), them: (Body, Velocity)): Velocity = {
    calculateGravity(me._2, me._1, them._1)
  }

  private def doStep(input: Map[Int, (Body, Velocity)]): Map[Int, (Body, Velocity)] = {
    var bodies = input
    val idPairList = bodies.keySet.toList.combinations(2).toList

    for (idPair <- idPairList) {
      val id0 = idPair(0)
      val id1 = idPair(1)

      val newVelocity0 = calculateGravity(bodies(id0), bodies(id1))
      val newVelocity1 = calculateGravity(bodies(id1), bodies(id0))

      bodies = bodies + (id0 -> (bodies(id0)._1, newVelocity0))
      bodies = bodies + (id1 -> (bodies(id1)._1, newVelocity1))
    }

    bodies.map(a => (a._1, (Body(a._2._1.x + a._2._2.x, a._2._1.y + a._2._2.y, a._2._1.z + a._2._2.z), a._2._2)))
  }

  private def calculateEnergy(input: Map[Int, (Body, Velocity)]): Int = {
    @tailrec
    def execute(step: Int, working: Map[Int, (Body, Velocity)]): Map[Int, (Body, Velocity)] = {
      if (step == 0) {
        working
      } else {
        execute(step - 1, doStep(working))
      }
    }

    execute(steps, input).values.map(a => a._1.calculateEnergy() * a._2.calculateEnergy()).sum
  }

  private def generateKey(i: Int, bodies: List[(Body, Velocity)]): String = {
    bodies.map(body => i match {
      case 0 => body._1.x
      case 1 => body._1.y
      case 2 => body._1.z
      case 3 => body._2.x
      case 4 => body._2.y
      case 5 => body._2.z
    }).mkString("|")
  }

  private def leastCommonMultiple(list: Seq[Long]): Long =
    list.foldLeft(1: Long) {
      (a, b) => b * a / Stream.iterate((a, b)) { case (x, y) => (y, x % y) }.dropWhile(_._2 != 0).head._1.abs
    }

  private def generateHashCode(i: Int, working: List[(Body, Velocity)]): String = {
    generateKey(i, working) + "|" + generateKey(i + 3, working)
  }

  private def calculateCycles(input: Map[Int, (Body, Velocity)]): Long = {
    var cycles = Map[Int, Int]()
    var seen = Map[Int, Set[String]]().withDefaultValue(Set())
    var cycle = 0
    var working = input

    while (cycles.size != 3) {
      for (i <- 0 until 3) {
        val hashcode = generateHashCode(i, working.values.toList)
        if (seen(i).contains(hashcode) && !cycles.contains(i))
          cycles = cycles + (i -> cycle)
        seen = seen + (i -> (seen(i) + hashcode))
      }

      cycle += 1
      working = doStep(working)
    }

    leastCommonMultiple(cycles.values.map(i => i.toLong).toList)
  }

  override val part1Answer: Int = calculateEnergy(moons)
  override val part2Answer: Long = calculateCycles(moons)
}


