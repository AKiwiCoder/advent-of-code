using System;
using System.Collections.Generic;
using System.Reflection.Metadata;
using Common;

namespace TwentySixteen
{
    public class Day01NoTimeForATaxicab : IDailyProblem<int, int>
    {
        public int Part1Answer { get; }
        public int Part2Answer { get; }

        public Day01NoTimeForATaxicab(string filename)
        {
            Part1Answer = int.MinValue;
            Part2Answer = int.MinValue;

            var line = InputFileReader.ReadLines(filename)[0];
            var commands = line.Split(",");

            var steps = new List<Position>();
            var facing = Facing.North;
            var position = new Position(0, 0);
            foreach (var rawCommand in commands)
            {
                var command = rawCommand.Trim();
                if (command[0] == 'L')
                {
                    facing = Moves.TurnLeft(facing);
                }
                else if (command[0] == 'R')
                {
                    facing = Moves.TurnRight(facing);
                }
                else
                {
                    throw new ArgumentOutOfRangeException($"Unknown Command {command}");
                }

                var count = int.Parse(command.Substring(1));
                for (int c = 0; c < count; c++)
                {
                    steps.Add(position);
                    position = Moves.Move(position, facing);
                    if (steps.Contains(position) && Part2Answer == int.MinValue)
                    {
                        Part2Answer = Moves.ManhattenDistance(new Position(0, 0), position);
                    }
                }
            }

            Part1Answer = Moves.ManhattenDistance(new Position(0, 0), position);
        }
    }
}