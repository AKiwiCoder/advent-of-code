using Common;
using NUnit.Framework;
using TwentySixteen;

namespace TestTwentySixteen
{
    public class Day01NoTimeForATaxicabTests
    {
        [Test]
        public void CheckReal()
        {
            IDailyProblem<int, int> dp = new Day01NoTimeForATaxicab("Aoc2016.Day01-NoTimeForATaxicab-input.txt");

            Assert.AreEqual(301, dp.Part1Answer);
            Assert.AreEqual(130, dp.Part2Answer);
        }

        [Test]
        public void CheckExample1()
        {
            IDailyProblem<int, int> dp = new Day01NoTimeForATaxicab("Aoc2016.Day01-NoTimeForATaxicab-example#1.txt");

            Assert.AreEqual(8, dp.Part1Answer);
            Assert.AreEqual(4, dp.Part2Answer);
        }
    }
}