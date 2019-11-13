using NUnit.Framework;
using TwentyEighteen;

namespace TestTwentyEighteen
{
    public class Day01ChronalCalibrationTest
    {
        [Test]
        public void CheckReal()
        {
            Day01ChronalCalibration cc =
                new Day01ChronalCalibration("Aoc2018.Day01-ChronalCalibration-input.txt");

            Assert.AreEqual(430, cc.Part1Answer);
            Assert.AreEqual(462, cc.Part2Answer);
        }

        [Test]
        public void CheckExample1()
        {
            Day01ChronalCalibration cc =
                new Day01ChronalCalibration("Aoc2018.Day01-ChronalCalibration-example#1.txt");

            Assert.AreEqual(3, cc.Part1Answer);
            Assert.AreEqual(2, cc.Part2Answer);
        }

        [Test]
        public void CheckExample2()
        {
            Day01ChronalCalibration cc =
                new Day01ChronalCalibration("Aoc2018.Day01-ChronalCalibration-example#2.txt");

            Assert.AreEqual(0, cc.Part1Answer);
            Assert.AreEqual(0, cc.Part2Answer);
        }

        [Test]
        public void CheckExample3()
        {
            Day01ChronalCalibration cc =
                new Day01ChronalCalibration("Aoc2018.Day01-ChronalCalibration-example#3.txt");

            Assert.AreEqual(4, cc.Part1Answer);
            Assert.AreEqual(10, cc.Part2Answer);
        }

        [Test]
        public void CheckExample4()
        {
            Day01ChronalCalibration cc =
                new Day01ChronalCalibration("Aoc2018.Day01-ChronalCalibration-example#4.txt");

            Assert.AreEqual(4, cc.Part1Answer);
            Assert.AreEqual(5, cc.Part2Answer);
        }

        [Test]
        public void CheckExample5()
        {
            Day01ChronalCalibration cc =
                new Day01ChronalCalibration("Aoc2018.Day01-ChronalCalibration-example#5.txt");

            Assert.AreEqual(1, cc.Part1Answer);
            Assert.AreEqual(14, cc.Part2Answer);
        }
    }
}