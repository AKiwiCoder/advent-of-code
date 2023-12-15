package advent.twenty_twentythree 

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

class Day15LensLibrary(filename : String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename).head.split(",").toList

  private def process(step : String): Int = {
    step.toCharArray.foldLeft(0)((acc, c) => {
      ((acc + c.toInt) * 17) % 256
    })
  }

  private def partTwo(step : String, boxes : Map[Int, List[(String, Int)]]) : Map[Int, List[(String, Int)]] = {
    println("Step: " + step)
    if (step.contains("=")) {
      val bits = step.split("=")
      val lens = bits(0)
      val focalLength = bits(1).toInt
      val hash = process(lens)

      if (boxes.contains(hash)) {
        val box = boxes(hash)
        if (box.exists(p => p._1.equals(lens))) {
          println("Lens " + lens + " replaced in box " + hash)
          boxes + (hash -> box.map(e => if (e._1.equals(lens)) (lens, focalLength) else e))
        } else {
          println("Lens " + lens + " added into box " + hash)
          boxes + (hash -> (box ::: List((lens, focalLength))))
        }
      } else {
        println("Lens " + lens + " new into new box " + hash)
        boxes + (hash -> List((lens, focalLength)))
      }
    } else {
      val lens = step.substring(0, step.length - 1)
      println(lens)
      val hash = process(lens)
      if (boxes.contains(hash)) {
        val entry = boxes(hash)
        if (entry.exists(p => p._1.equals(lens))) {
          println("Lens " + lens + " in box " + hash + " removing")
          boxes + (hash -> entry.filter(p => !p._1.equals(lens)))
        } else {
          println("Lens " + lens + " not in box " + hash)
          boxes
        }
      } else {
        println("Box " + hash + " does not exist")
        boxes
      }
    }
  }

  private def focusPower(entry: (Int, List[(String, Int)])): Int = {
    entry._2.indices.foldLeft(0)((acc, idx) => {
      val h = (1 + entry._1) * (idx+1) * entry._2(idx)._2
      println(entry._2(idx) + " " + h)
      acc + (1 + entry._1) * (idx+1) * entry._2(idx)._2
    })
  }

  override val part1Answer: Int = input.map(step => process(step)).sum
  override val part2Answer: Int = input.foldLeft(Map[Int, List[ (String, Int)]]())((acc, step) => partTwo(step, acc)).map(e => focusPower(e)).sum
}


