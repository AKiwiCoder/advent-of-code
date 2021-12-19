package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.twenty_twentyone.Day19BeaconScanner.calculateRotations
import advent.utilities.{FileUtilities, Point3d}

import scala.annotation.tailrec

object Day19BeaconScanner {
  def rotateOrigin(p: Point3d, num: Int): Point3d = {
    num match {
      case 0 => p
      case 1 => Point3d(p.y, -p.x, p.z)
      case 2 => Point3d(-p.y, p.x, p.z)
      case 3 => Point3d(-p.x, -p.y, p.z)
    }
  }

  def rotateDirection(p: Point3d, num: Int): Point3d = {
    num match {
      case 0 => p
      case 1 => Point3d(-p.y, -p.z, p.x)
      case 2 => Point3d(p.x, -p.y, -p.z)
      case 3 => Point3d(-p.x, -p.z, -p.y)
      case 4 => Point3d(p.y, -p.z, -p.x)
      case 5 => Point3d(p.x, -p.z, p.y)
    }
  }

  private val ROTATIONS = (for (
    i <- 0 to 3;
    j <- 0 to 5
  ) yield (i, j)).toList

  def calculateRotations(points: List[Point3d]): List[List[Point3d]] = {
    ROTATIONS.map(rotation => points.foldLeft(List[Point3d]())((acc, p) => rotateOrigin(rotateDirection(p, rotation._2), rotation._1) :: acc))
  }
}

class Day19BeaconScanner(filename: String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename)

  private val PATTERN = "([-]*[0-9]+),([-]*[0-9]+),([-]*[0-9]+)".r

  private def parse(line: String): Point3d = {
    line match {
      case PATTERN(x, y, z) => Point3d(x.toInt, y.toInt, z.toInt)
    }
  }

  private val headerIndexes = input.indices.filter(idx => input(idx).contains("---")).toList ::: List(input.size + 1)
  private val scannersBlocks = headerIndexes.zip(headerIndexes.tail.map(_ - 1))
  private val scannerDetections = scannersBlocks.foldLeft(List[List[String]]())((acc, idx) => acc :+ (input.slice(idx._1 + 1, idx._2)))
  private val scannerData = scannerDetections.map(data => data.map(parse))

  private def performMatch(knownBeacons: Set[Point3d], myBeacons: List[Point3d]): (Point3d, Set[Point3d]) = {
    val allMyRotations = calculateRotations(myBeacons)

    val matches = allMyRotations.map(rotated => {
      val myPossibleScannerLocation = rotated.foldLeft(Set[Point3d]())((acc, r) => knownBeacons.foldLeft(acc)((a, k) => a + Point3d(k.x - r.x, k.y - r.y, k.z - r.z)))
      val myTranslatedBeaconSet = myPossibleScannerLocation.map(possibleScannerLoc => (possibleScannerLoc, rotated.map(myLoc => Point3d(myLoc.x + possibleScannerLoc.x, myLoc.y + possibleScannerLoc.y, myLoc.z + possibleScannerLoc.z))))
      myTranslatedBeaconSet.map(m => (m._1, m._2.toSet)).filter(_._2.intersect(knownBeacons).size >= 12)
    }).filter(_.nonEmpty)

    if (matches.isEmpty) {
      (null, Set())
    } else {
      matches.head.head
    }
  }

  @tailrec
  private def matchScanners(pending: List[List[Point3d]], scanners: List[Point3d], beacons: Set[Point3d]): (Set[Point3d], List[Point3d]) = {
    if (pending.isEmpty) {
      (beacons, scanners)
    } else {
      val me = pending.head

      val (scanner, newBeacons) = performMatch(beacons, me)
      if (scanner != null) {
        matchScanners(pending.tail, scanner :: scanners, beacons ++ newBeacons)
      } else {
        matchScanners(pending.tail :+ me, scanners, beacons)
      }
    }
  }

  private val (beacons, scanners) = matchScanners(scannerData.tail, List(Point3d(0, 0, 0)), scannerData.head.toSet)

  def manhattenDistance(a : Point3d, b : Point3d) : Int = {
    Math.abs(a.x - b.x) + Math.abs(a.y -b.y) + Math.abs(a.z - b.z)
  }

  override val part1Answer: Int = beacons.size
  override val part2Answer: Int =scanners.map(s1 => scanners.map(s2 => manhattenDistance(s1, s2)).max).max
}


