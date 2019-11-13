using System;
using System.Linq;
using Common;

namespace TwentySeventeen
{
    public class Day01InverseCaptcha : IDailyProblem<int, int>
    {
        public int Part1Answer { get; }
        public int Part2Answer { get; }

        public Day01InverseCaptcha(string filename)
        {
            var lines = InputFileReader.ReadLines(filename);
            foreach (var line in lines)
            {
                var len = line.Length / 2;
                for (var index = 0; index < line.Length; index++)
                {
                    if (line[index] == line[(index + 1) % line.Length])
                    {
                        Part1Answer += int.Parse(line.Substring(index, 1));
                    }

                    if (line[index] == line[(index + len) % line.Length])
                    {
                        Part2Answer += int.Parse(line.Substring(index, 1));
                    }
                }
            }
        }
    }
}