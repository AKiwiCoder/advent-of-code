package advent.twenty_nineteen

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File

import advent.utilities.Point
import javax.imageio.ImageIO

import scala.annotation.tailrec
import scala.sys.process._

class Day13CarePackageGenerateImages(filename: String) {
  private val program = IntComputer.loadProgram(filename)

  /*
   * Run the main and then run "ffmpeg -i /tmp/breakout-%d.png -r 25 -filter:v "setpts=0.25*PTS" output.gif"
   * to produce the output.gif file
   */

  var step = 0

  def dumpGrid(grid: Map[Point, Int]) = {
    val image = new BufferedImage(440, 260, BufferedImage.TYPE_3BYTE_BGR)
    val g = image.createGraphics()

    for (y <- 0 to 22) {
      for (x <- 0 to 42) {
        grid(Point(y, x)) match {
          case 0 => // Blank
          case 1 => { // Wall
            g.setColor(Color.white)
            g.fillRect(x * 10, y * 10, 10, 10)
          }
          case 2 => { // Block
            g.setColor(Color.red)
            g.fillRect(x * 10 + 1, y * 10 + 1, 8, 8)
          }
          case 3 => { // Bat
            g.setColor(Color.blue)
            g.fillRect(x * 10, y * 10 + 5, 10, 5)
          }
          case 4 => { // Ball
            g.setColor(Color.green)
            g.fillArc(x * 10, y * 10, 10, 10, 0, 359)
          }
        }
      }
    }

    val score = grid.getOrElse(Point(0, -1), 0)
    g.setColor(Color.white)
    g.drawString("Score: " + score, 20, 240)

    ImageIO.write(image, "PNG", new File("/tmp/breakout-" + step + ".png"))
    step = step + 1
  }

  def doPart2: Int = {
    @tailrec
    def playBreakout(state: IntComputerState, grid: Map[Point, Int]): Int = {
      if (state.isFinished())
        grid(Point(0, -1))
      else {
        val newState = IntComputer.execute(state)
        val commands = newState.output.grouped(3)
        val newGrid = commands.foldLeft(grid)((acc, entry) => (acc + (Point(entry(1).toInt, entry(0).toInt) -> entry(2).toInt)))

        val ballPos = newGrid.filter(entry => entry._2 == 4)
        val batPos = newGrid.filter(entry => entry._2 == 3)

        val ballX = if (ballPos.nonEmpty) ballPos.head._1.x else 0
        val batX = if (batPos.nonEmpty) batPos.head._1.x else 0

        dumpGrid(newGrid)

        playBreakout(IntComputerState.copyState(newState, List(Integer.compare(ballX, batX))), newGrid)
      }
    }

    playBreakout(IntComputerState.newState(program + (0L -> 2L)), Map())
  }
}

object Day13CarePackageGenerateImages {
  def main(args: Array[String]): Unit = {
    val cp = new Day13CarePackageGenerateImages("/twenty_nineteen/Day13-CarePackage-input.txt")
    cp.doPart2
    println(Process("ffmpeg", Seq("-i", "/tmp/breakout-%d.png", "-r", "25", "-filter:v", "setpts=0.25*PTS", "breakout.gif")).run())
  }
}


