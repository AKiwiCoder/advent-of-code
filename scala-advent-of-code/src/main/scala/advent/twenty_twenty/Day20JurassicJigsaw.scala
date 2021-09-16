package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.{FileUtilities, Point2d}

import scala.annotation.tailrec

case class Piece(id: String, top: String, bottom: String, left: String, right: String, data: List[String], lastIdx: Int) {
  def dump(): Unit = {
    println(data.foldLeft("")((acc, line) => acc + line + "\n"))
    println("")
  }

  def flip(): Piece = Piece(id, bottom, top, left.reverse, right.reverse, data.reverse, lastIdx)

  def rotate(): Piece = {
    val newTop = left.reverse
    val newBottom = right.reverse
    val newLeft = bottom
    val newRight = top
    val newData = (lastIdx to 0 by -1).foldLeft(List[String]())((acc, row) =>
      (lastIdx to 0 by -1).foldLeft("")((acc, col) => {
        acc + data(col).charAt(row)
      }) :: acc)
    Piece(id, newTop, newBottom, newLeft, newRight, newData, lastIdx)
  }
}

class Day20JurassicJigsaw(filename: String, size: Int) extends DailyProblem[Long, Long] {

  private val input = FileUtilities.readFile(filename)

  private val header = "Tile ([0-9]+):".r
  private val data = "([.#]+)".r

  private def build(id: String, data: List[String], lastIdx: Int): Piece = {
    val top = data(0)
    val bottom = data(lastIdx)
    val left = data.foldLeft("")((acc, line) => acc + line(0))
    val right = data.foldLeft("")((acc, line) => acc + line(lastIdx))
    Piece(id, top, bottom, left, right, data, lastIdx)
  }

  private val working = input.foldLeft((List[Piece](), "", List[String]()))((acc, line) => line.trim match {
    case header(id) => (acc._1, id, acc._3)
    case data(line) => (acc._1, acc._2, line :: acc._3)
    case "" => (build(acc._2, acc._3.reverse, 9) :: acc._1, "", List())
  })

  private val pieces = build(working._2, working._3.reverse, 9) :: working._1

  def canFit(lhs: Piece, rhs: Piece): Boolean = {
    def aSideAligns(lhs: Piece, rhs: Piece): Boolean = {
      lhs.top.equals(rhs.bottom) || lhs.bottom.equals(rhs.top) || lhs.right.equals(rhs.left) || lhs.left.equals(rhs.right)
    }

    val lefts = List(lhs, lhs.flip())
    val rights = List(rhs, rhs.flip())

    val combos = for (l <- lefts; r <- rights) yield (l, r)

    combos.exists(combo => {
      val l0 = combo._1
      val l1 = l0.rotate()
      val l2 = l1.rotate()
      val l3 = l2.rotate()

      val r0 = combo._2
      val r1 = r0.rotate()
      val r2 = r1.rotate()
      val r3 = r2.rotate()

      val combos = for (l <- List(l0, l1, l2, l3); r <- List(r0, r1, r2, r3)) yield (l, r)
      combos.exists(attempt => aSideAligns(attempt._1, attempt._2))
    })
  }

  private val fits = pieces.foldLeft(Map[String, List[String]]())((acc, piece) => acc + (piece.id -> pieces.filterNot(_.id == piece.id).filter(other => canFit(piece, other)).map(_.id)))

  private def part1(): Long = {
    fits.filter(_._2.size == 2).map(_._1.toLong).product
  }

  private def part2(): Long = {
    case class Match(location: Point2d, piece: Piece)

    def findConnect(existing: Match, newPiece: Piece): Option[Match] = {
      if (existing.piece.top.equals(newPiece.bottom)) {
        Option.apply(Match(Point2d(existing.location.x, existing.location.y + 1), newPiece))
      } else if (existing.piece.bottom.equals(newPiece.top)) {
        Option.apply(Match(Point2d(existing.location.x, existing.location.y - 1), newPiece))
      } else if (existing.piece.left.equals(newPiece.right)) {
        Option.apply(Match(Point2d(existing.location.x - 1, existing.location.y), newPiece))
      } else if (existing.piece.right.equals(newPiece.left)) {
        Option.apply(Match(Point2d(existing.location.x + 1, existing.location.y), newPiece))
      } else {
        Option.empty
      }
    }

    def connect(existing: Match, newPiece: Piece): Match = {
      val r0 = newPiece
      val r1 = r0.rotate()
      val r2 = r1.rotate()
      val r3 = r2.rotate()
      val fr0 = newPiece.flip()
      val fr1 = fr0.rotate()
      val fr2 = fr1.rotate()
      val fr3 = fr2.rotate()

      val placements = List(r0, r1, r2, r3, fr0, fr1, fr2, fr3).map(attempt => findConnect(existing, attempt)).filterNot(_.isEmpty)
      placements.head.get
    }

    @tailrec
    def assemble(toUse: List[String], matched: List[Match]): List[Match] = {
      if (toUse.isEmpty) {
        matched
      } else {
        val newPieceId = toUse.filter(id => fits(id).exists(matchesWith => matched.exists(_.piece.id.equals(matchesWith)))).head
        val existingMatchId = fits(newPieceId).filter(fitId => matched.exists(_.piece.id.equals(fitId))).head
        val matche = matched.find(_.piece.id.equals(existingMatchId)).get
        val placement = connect(matche, pieces.find(_.id.equals(newPieceId)).get)
        assemble(toUse.filterNot(_.equals(newPieceId)), placement :: matched)
      }
    }

    val startCorner = fits.filter(_._2.size == 2).head._1
    val cornerPiece = pieces.filter(_.id.equals(startCorner)).head
    val remainingPieces = pieces.filterNot(_.id.equals(startCorner)).map(_.id)
    val done = assemble(remainingPieces, List(Match(Point2d(0, 0), cornerPiece)))

    val minX = done.map(_.location.x).min
    val minY = done.map(_.location.y).min
    val maxX = done.map(_.location.x).max
    val maxY = done.map(_.location.y).max

    println(minX + " " + minY + " " + maxX + " " + maxY)

    def buildRow(tileY: Int) : List[String] = {
      (8 to 1 by -1).foldLeft(List[String]())((acc, dataRow) =>
        (maxX to minX by -1).foldLeft("")((acc, tileX) => {
          val tile = done.find(_.location.equals(Point2d(tileX, tileY))).get
          val data = tile.piece.data(dataRow)
          data.substring(1, 9) + acc
        }).reverse :: acc)
    }

    val txt = (minY to maxY).foldLeft(List[String]())((acc, tileY) => buildRow(tileY) ::: acc)

    def isSeaSerpent(x: Int, y: Int, txt: List[String]): Boolean = {
      val row0 = txt(y).substring(x, x + 20)
      if (row0.charAt(18) == '#') {
        val row1 = txt(y + 1).substring(x, x + 20)
        if (row1.charAt(0) == '#' &&
          row1.charAt(5) == '#' &&
          row1.charAt(6) == '#' &&
          row1.charAt(11) == '#' &&
          row1.charAt(12) == '#' &&
          row1.charAt(17) == '#' &&
          row1.charAt(18) == '#' &&
          row1.charAt(19) == '#') {
          val row2 = txt(y + 2).substring(x, x + 20)
          if (row2.charAt(1) == '#' &&
            row2.charAt(4) == '#' &&
            row2.charAt(7) == '#' &&
            row2.charAt(10) == '#' &&
            row2.charAt(13) == '#' &&
            row2.charAt(16) == '#') {
            true
          } else {
            false
          }
        } else {
          false
        }
      } else {
        false
      }
    }

    val image = build("0", txt, txt.size - 1)

    val r0 = image
    val r1 = r0.rotate()
    val r2 = r1.rotate()
    val r3 = r2.rotate()

    val fr0 = image.flip()
    val fr1 = fr0.rotate()
    val fr2 = fr1.rotate()
    val fr3 = fr2.rotate()

    val width = txt.head.length - 20
    val height = txt.length - 3

    fr1.dump()

    val serpents = List(r0, r1, r2, r3, fr0, fr1, fr2, fr3).map(check =>
    (0 until width).map(x => (0 until height).map(y => if (isSeaSerpent(x, y, check.data)) 1 else 0).sum).sum)

    val count = List(fr1).map(image => image.data.map(_.count(_ == '#')).sum)

    count.head - (serpents.max * 15)
  }


  override val part1Answer: Long = part1()
  override val part2Answer: Long = part2()
}


