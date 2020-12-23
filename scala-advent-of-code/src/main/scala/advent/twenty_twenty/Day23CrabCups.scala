package advent.twenty_twenty

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import java.util
import scala.annotation.tailrec

class Day23CrabCups(input: String) extends DailyProblem[String, Long] {
  //  private def run(cupList : List[Int], moveLimit : Int): List[Int] = {
  //    @tailrec
  //    def findNext(num: Int, cups: List[Int]): Int = {
  //        val foundIdx = cups.indexOf(num - 1)
  //        if (foundIdx >= 0) {
  //          foundIdx
  //        } else if (num < 1) {
  //          findNext(10, cups)
  //        } else {
  //          findNext(num - 1, cups)
  //        }
  //    }
  //
  //    @tailrec
  //    def move(num: Int, idx: Int, cups: List[Int]): List[Int] = {
  //      if (num % 10000 == 0) {
  //        println(num)
  //      }
  //      if (num == 0) {
  //        cups
  //      } else {
  //        val firstCup = cups((idx + 1) % 9)
  //        val secondCup = cups((idx + 2) % 9)
  //        val thirdCup = cups((idx + 3) % 9)
  //
  //        val smallerSet = cups.filterNot(cup => cup == firstCup || cup == secondCup || cup == thirdCup)
  //        val insertAt = findNext(cups(idx), smallerSet)
  //        val newCups = smallerSet.take(insertAt+1) ::: (firstCup :: (secondCup :: (thirdCup :: smallerSet.drop(insertAt+1))))
  //        move(num - 1, (newCups.indexOf(cups(idx)) + 1) % 9, newCups)
  //      }
  //    }
  //    move(moveLimit, 0, cupList)
  //  }
  //
  //  def part1() : String = {
  //    val cups = run(input.map(c => (""+c).toInt).toList, 100)
  //    val idxOfOne = cups.indexOf(1)
  //    (1 to 8).foldLeft("")((acc, i) => acc + cups((idxOfOne + i) % 9))
  //  }
  //


  private def run(cupList: List[Int], moveLimit: Int): CircularList = {
    val circle = new CircularList()
    cupList.foreach(cup => circle.insertBefore(cup))

    def findNext(num: Int, v1: Int, v2: Int, v3: Int): Node = {
      var search = num - 1
      var found: Node = null
      while (found == null) {
        if (search <= 0) {
          search = circle.getMaxValue()
        }
        if (search != v1 && search != v2 && search != v3) {
          found = circle.find(search)
        }
        search -= 1
      }
      found
    }

    def move(): Unit = {
      var num = moveLimit
      while (num > 0) {
        val cut = circle.cutThree()
        val insertAt = findNext(circle.getHead().getValue(), cut.value, cut.next.value, cut.next.next.value)
        circle.spliceThree(insertAt, cut)
        circle.moveHeadOne()
        num = num - 1
      }
    }

    move()
    circle
  }

  def part1(): String = {
    val circle = run(input.map(c => ("" + c).toInt).toList, 100)
    val one = circle.find(1)
    val ans = circle.dump(one.next, "")
    ans.substring(0, ans.length - 1)

  }

  def part2(): Long = {
    val initialCups = input.map(c => ("" + c).toInt).toList ::: (10 to 1000000).toList
    val cups = run(initialCups, 10000000)
    val one = cups.find(1)
    1L * one.next.value * one.next.next.value
  }

  override val part1Answer: String = part1()
  override val part2Answer: Long = part2()
}


