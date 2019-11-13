using Common;
using NUnit.Framework;
using TwentyFifteen;

namespace TestTwentyFifteen
{
    public class Day01NotQuiteLispTests
    {
        [Test]
        public void CheckReal()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-input.txt");

            Assert.AreEqual(74, dp.Part1Answer);
            Assert.AreEqual(1795, dp.Part2Answer);
        }

        [Test]
        public void CheckExample1()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-example#1.txt");

            Assert.AreEqual(0, dp.Part1Answer);
            Assert.AreEqual(-1, dp.Part2Answer);
        }

        [Test]
        public void CheckExample2()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-example#2.txt");

            Assert.AreEqual(0, dp.Part1Answer);
            Assert.AreEqual(-1, dp.Part2Answer);
        }

        [Test]
        public void CheckExample3()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-example#3.txt");

            Assert.AreEqual(3, dp.Part1Answer);
            Assert.AreEqual(-1, dp.Part2Answer);
        }

        [Test]
        public void CheckExample4()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-example#4.txt");

            Assert.AreEqual(3, dp.Part1Answer);
            Assert.AreEqual(-1, dp.Part2Answer);
        }

        [Test]
        public void CheckExample5()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-example#5.txt");

            Assert.AreEqual(3, dp.Part1Answer);
            Assert.AreEqual(1, dp.Part2Answer);
        }

        [Test]
        public void CheckExample6()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-example#6.txt");

            Assert.AreEqual(-1, dp.Part1Answer);
            Assert.AreEqual(3, dp.Part2Answer);
        }

        [Test]
        public void CheckExample7()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-example#7.txt");

            Assert.AreEqual(-1, dp.Part1Answer);
            Assert.AreEqual(1, dp.Part2Answer);
        }

        [Test]
        public void CheckExample8()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-example#8.txt");

            Assert.AreEqual(-3, dp.Part1Answer);
            Assert.AreEqual(1, dp.Part2Answer);
        }

        [Test]
        public void CheckExample9()
        {
            IDailyProblem<int, int> dp = new Day01NotQuiteLisp("Aoc2015.Day01-NotQuiteLisp-example#9.txt");

            Assert.AreEqual(-3, dp.Part1Answer);
            Assert.AreEqual(1, dp.Part2Answer);
        }
    }
}