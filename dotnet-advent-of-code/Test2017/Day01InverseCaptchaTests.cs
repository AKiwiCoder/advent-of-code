using Common;
using NUnit.Framework;
using TwentySeventeen;

namespace TestTwentySeventeen
{
    public class Day01InverseCaptchaTests
    {
        [Test]
        public void CheckReal() {
            IDailyProblem<int, int> dp = new Day01InverseCaptcha("Aoc2017.Day01-InverseCaptcha-input.txt");

            Assert.AreEqual(1253, dp.Part1Answer);
            Assert.AreEqual(1278, dp.Part2Answer);
        }

        [Test]
        public void CheckExample1() {
            IDailyProblem<int, int> dp = new Day01InverseCaptcha("Aoc2017.Day01-InverseCaptcha-example#1.txt");

            Assert.AreEqual(16, dp.Part1Answer);
            Assert.AreEqual(10, dp.Part2Answer);
        }

        [Test]
        public void CheckExample2() {
            IDailyProblem<int, int> dp = new Day01InverseCaptcha("Aoc2017.Day01-InverseCaptcha-example#2.txt");

            Assert.AreEqual(3, dp.Part1Answer);
            Assert.AreEqual(26, dp.Part2Answer);
        }

    }
}