using System;
using Common;

namespace TwentyFifteen
{
    public class Day01NotQuiteLisp : IDailyProblem<int, int>
    {
        public int Part1Answer { get; }
        public int Part2Answer { get; }

        public Day01NotQuiteLisp(string filename)
        {
            var line = InputFileReader.ReadLines(filename)[0];

            Part2Answer = -1;
            
            var floor = 0;
            var basementFound = false;
            for (int index = 0; index < line.Length; index++)
            {
                var c = line[index];
                floor += (c == '(') ? 1 : -1;

                if (floor == -1 && !basementFound)
                {
                    Part2Answer = index + 1;
                    basementFound = true;
                }
            }

            Part1Answer = floor;
        }
    }
}