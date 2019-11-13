using System;
using System.Collections.Generic;
using System.Linq;
using Common;

namespace TwentyEighteen
{
    public class Day01ChronalCalibration : IDailyProblem<int, int>
    {
        public int Part1Answer { get; }
        public int Part2Answer { get; }

        public Day01ChronalCalibration(String filename)
        {
            List<int> frequencyChanges = InputFileReader.ReadLines(filename, Convertors.StringToInt);

            Part1Answer = frequencyChanges.Sum();

            var seen = new HashSet<int>();
            var frequency = 0;
            var index = 0;
            while (!seen.Contains(frequency))
            {
                if (index >= frequencyChanges.Count)
                {
                    index = 0;
                }

                seen.Add(frequency);
                frequency = frequency + frequencyChanges[index];
                index++;
            }

            Part2Answer = frequency;
        }
    }
}