package advent.twenty_twenty

import advent.common.DailyProblem

class Day23CrabCups(input: String) extends DailyProblem[String, Long] {
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


