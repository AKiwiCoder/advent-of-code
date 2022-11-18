package advent.twenty_twentyone 

import org.scalatest.{FlatSpec, Matchers}

class Day18SnailfishTest extends FlatSpec with Matchers {
  "2020 Day 18 - Input" should "calculate answers" in {
    val dp = new Day18Snailfish("/twenty_twentyone/Day18-Snailfish-input.txt")

    dp.part1Answer should be(4145)
    dp.part2Answer should be(4855)
  }

  "2020 Day 18 - Example #1" should "calculate answers" in {
    val dp = new Day18Snailfish("/twenty_twentyone/Day18-Snailfish-example#1.txt")

    dp.sumString should be("[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]")

    dp.part1Answer should be(4140)
    dp.part2Answer should be(3993)
  }

  "2020 Day 18 - Example #2" should "calculate answers" in {
    val dp = new Day18Snailfish("/twenty_twentyone/Day18-Snailfish-example#2.txt")

    dp.sumString should be ("[[[[1,1],[2,2]],[3,3]],[4,4]]")
  }

  "2020 Day 18 - Example #3" should "calculate answers" in {
    val dp = new Day18Snailfish("/twenty_twentyone/Day18-Snailfish-example#3.txt")

    dp.sumString should be ("[[[[3,0],[5,3]],[4,4]],[5,5]]")
  }

  "2020 Day 18 - Example #4" should "calculate answers" in {
    val dp = new Day18Snailfish("/twenty_twentyone/Day18-Snailfish-example#4.txt")

    dp.sumString should be ("[[[[5,0],[7,4]],[5,5]],[6,6]]")

    dp.part1Answer should be(1137)
    dp.part2Answer should be(150)
  }

  "2020 Day 18 - Example #5" should "calculate answers" in {
    val dp = new Day18Snailfish("/twenty_twentyone/Day18-Snailfish-example#5.txt")

    dp.sumString should be ("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")

    dp.part1Answer should be(3488)
    dp.part2Answer should be(3946)
  }

  "2020 Day 18 - Example #5 - Breakdown" should "calculate answers" in {
    val l01 = Day18Snailfish.parseTree("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]")
    val l02 = Day18Snailfish.parseTree("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]")
    val l03 = Day18Snailfish.parseTree("[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]")
    val l04 = Day18Snailfish.parseTree("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]")
    val l05 = Day18Snailfish.parseTree("[7,[5,[[3,8],[1,4]]]]")
    val l06 = Day18Snailfish.parseTree("[[2,[2,2]],[8,[8,1]]]")
    val l07 = Day18Snailfish.parseTree( "[2,9]")
    val l08 = Day18Snailfish.parseTree("[1,[[[9,3],9],[[9,0],[0,7]]]]")
    val l09 = Day18Snailfish.parseTree("[[[5,[7,4]],7],1]")
    val l10 = Day18Snailfish.parseTree("[[[[4,2],2],6],[8,7]]")

    val s1 = Day18Snailfish.add(l01, l02)
    s1.toString should be("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]")

    val s2 = Day18Snailfish.add(s1, l03)
    s2.toString should be("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]")

    val s3 = Day18Snailfish.add(s2, l04)
    s3.toString should be("[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]")

    val s4 = Day18Snailfish.add(s3, l05)
    s4.toString should be("[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]")

    val s5 = Day18Snailfish.add(s4, l06)
    s5.toString should be("[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]")

    val s6 = Day18Snailfish.add(s5, l07)
    s6.toString should be("[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]")

    val s7 = Day18Snailfish.add(s6, l08)
    s7.toString should be("[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]")

    val s8 = Day18Snailfish.add(s7, l09)
    s8.toString should be("[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]")

    val s9 = Day18Snailfish.add(s8, l10)
    s9.toString should be("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")
  }


  "2020 Day 18 - Add Simple" should "add snailnumbers correctly" in {
    val lsh = Day18Snailfish.parseTree("[2,2]")
    val rhs = Day18Snailfish.parseTree("[1,1]")

    val result = Day18Snailfish.add(lsh, rhs).toString

    result should be("[[2,2],[1,1]]")
  }

  "2020 Day 18 - Explode Test" should "add explode correctly" in {
    Day18Snailfish.reduce(Day18Snailfish.parseTree("[[[[[9,8],1],2],3],4])")).toString should be("[[[[0,9],2],3],4]")
    Day18Snailfish.reduce(Day18Snailfish.parseTree("[7,[6,[5,[4,[3,2]]]]]")).toString should be("[7,[6,[5,[7,0]]]]")
    Day18Snailfish.reduce(Day18Snailfish.parseTree("[[6,[5,[4,[3,2]]]],1]")).toString should be("[[6,[5,[7,0]]],3]")
    Day18Snailfish.reduce(Day18Snailfish.parseTree("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")).toString should be("[[3,[2,[8,0]]],[9,[5,[7,0]]]]")
  }

  "2020 Day 18 - Add With Explode" should "add  correctly" in {
    val lsh = Day18Snailfish.parseTree("[[[[4,3],4],4],[7,[[8,4],9]]]")
    val rhs = Day18Snailfish.parseTree("[1,1]")

    val result = Day18Snailfish.add(lsh, rhs).toString
    println(result)

    result should be("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")
  }

  "2020 Day 18 - Magnitude" should "should" in {
    Day18Snailfish.parseTree("[[1,2],[[3,4],5]]").magnitude() should be(143)
    Day18Snailfish.parseTree("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]").magnitude() should be(1384)
    Day18Snailfish.parseTree("[[[[1,1],[2,2]],[3,3]],[4,4]]").magnitude() should be(445)
    Day18Snailfish.parseTree("[[[[3,0],[5,3]],[4,4]],[5,5]]").magnitude() should be(791)
    Day18Snailfish.parseTree("[[[[5,0],[7,4]],[5,5]],[6,6]]").magnitude() should be(1137)
    Day18Snailfish.parseTree("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]").magnitude() should be(3488)
  }

}


