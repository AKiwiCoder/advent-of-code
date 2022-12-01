#!/bin/bash

NUM=$1
NAME=$2

MAIN="src/main/scala/advent/twenty_twentytwo/Day${NUM}${NAME}.scala"
TEST="src/test/scala/advent/twenty_twentytwo/Day${NUM}${NAME}Test.scala"

DATA1="src/test/resources/twenty_twentytwo/Day${NUM}-${NAME}-input.txt"
DATA2="src/test/resources/twenty_twentytwo/Day${NUM}-${NAME}-example#1.txt"

echo $MAIN
echo $TEST
echo $DATA1
echo $DATA2

echo -e "package advent.twenty_twentytwo \n\
\n\
import advent.common.DailyProblem\n\
import advent.utilities.FileUtilities\n\
\n\
import scala.annotation.tailrec\n\
\n\
class Day${NUM}${NAME}(filename : String) extends DailyProblem[Int, Int] {\n\
\n\
  private val input = FileUtilities.readFile(filename)\n\
\n\
  override val part1Answer: Int = 0\n\
  override val part2Answer: Int = 0\n\
}\n\
\n" > $MAIN

echo -e "package advent.twenty_twentytwo \n\
\n\
import org.scalatest.{FlatSpec, Matchers}\n\
\n\
class Day${NUM}${NAME}Test extends FlatSpec with Matchers {\n\
  \"2020 Day ${NUM} - Input\" should \"calculate answers\" in {\n\
    val dp = new Day${NUM}${NAME}(\"/twenty_twentytwo/Day${NUM}-${NAME}-input.txt\")\n\
\n\
    dp.part1Answer should be(0)\n\
    dp.part2Answer should be(0)\n\
  }\n\
\n\
  \"2020 Day ${NUM} - Example #1\" should \"calculate answers\" in {\n\
    val dp = new Day${NUM}${NAME}(\"/twenty_twentytwo/Day${NUM}-${NAME}-example#1.txt\")\n\
\n\
    dp.part1Answer should be(0)\n\
    dp.part2Answer should be(0)\n\
  }\n\
}\n\
\n" > $TEST

touch $DATA1
touch $DATA2

echo ":copyright: https://adventofcode.com/" > ../problems/twenty_twentytwo/Day${NUM}-${NAME}.md
