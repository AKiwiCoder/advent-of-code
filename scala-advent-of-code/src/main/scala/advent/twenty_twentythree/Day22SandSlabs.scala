package advent.twenty_twentythree

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point3d}

import scala.annotation.tailrec

class Day22SandSlabs(filename: String) extends DailyProblem[Int, Int] {
  case class Brick(id : Int, start: Point3d, end: Point3d)

  private val pattern = "([0-9]+),([0-9]+),([0-9]+)~([0-9]+),([0-9]+),([0-9]+)".r

  def parse(input : (Int, String)): Brick = {
    input._2 match {
      case pattern(x1, y1, z1, x2, y2, z2) => Brick(input._1, Point3d(x1.toInt, y1.toInt, z1.toInt), Point3d(x2.toInt, y2.toInt, z2.toInt))
    }
  }

  private val input = FileUtilities.readFile(filename).zipWithIndex.map(parse)

  private def canDrop(brick : Brick, bricks : List[Brick]) : Boolean = {
    val brickZ = Math.min(brick.start.z, brick.end.z)

    if (brickZ == 1) {
      false
    } else {
      !(brick.start.x to brick.end.x).exists(x => {
        (brick.start.y to brick.end.y).exists(y => {
          bricks.exists(b => {
            val xOverlap = b.start.x <= x && b.end.x >= x
            val yOverlap = b.start.y <= y && b.end.y >= y
            val zBelow = b.start.z == brickZ - 1 || b.end.z == brickZ - 1
            xOverlap && yOverlap & zBelow
          })
        })
      })
    }
  }

  @tailrec
  private def dropBricks(bricks : List[Brick], dropped : Set[Int]) : (List[Brick], Set[Int]) = {
    val droppableBrick = bricks.find(b => canDrop(b, bricks))
    if (droppableBrick.isEmpty) {
      (bricks, dropped)
    } else {
      val brick = droppableBrick.get
      val otherBricks = bricks.filterNot(b => b == brick)
      dropBricks(Brick(brick.id, Point3d(brick.start.x, brick.start.y, brick.start.z -1), Point3d(brick.end.x, brick.end.y, brick.end.z -1)) :: otherBricks, dropped + brick.id)
    }
  }

  private val asLowAsTheyCanGo = dropBricks(input, Set())._1

  private def canDisintegrateXBricks(bricks : List[Brick]) : Int = {
    bricks.count(deleteMe => {
      val otherBricks = bricks.filterNot(b => b == deleteMe)
      otherBricks.count(b => canDrop(b, otherBricks)) == 0
    })
  }

  private def countChainReaction(bricks : List[Brick]) : Int = {
    val b = bricks.map(deleteMe => {
      val otherBricks = bricks.filterNot(b => b == deleteMe)
      val (_, count) = dropBricks(otherBricks, Set())
      count
    })
    println(b)
    b.map(_.size).sum
  }

  override val part1Answer: Int = canDisintegrateXBricks(asLowAsTheyCanGo)
  override val part2Answer: Int = countChainReaction(asLowAsTheyCanGo)
}


