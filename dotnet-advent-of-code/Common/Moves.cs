using System;

namespace Common
{
    public class Moves
    {
        public static Facing TurnLeft(Facing current)
        {
            switch (current)
            {
                case Facing.North:
                    return Facing.West;
                case Facing.South:
                    return Facing.East;
                case Facing.West:
                    return Facing.South;
                case Facing.East:
                    return Facing.North;
                default:
                    throw new ArgumentOutOfRangeException(nameof(current), current, $"Unknown Facing {current}");
            }
        }

        public static Facing TurnRight(Facing current)
        {
            switch (current)
            {
                case Facing.North:
                    return Facing.East;
                case Facing.South:
                    return Facing.West;
                case Facing.West:
                    return Facing.North;
                case Facing.East:
                    return Facing.South;
                default:
                    throw new ArgumentOutOfRangeException(nameof(current), current, $"Unknown Facing {current}");
            }
        }

        public static Position Move(Position current, Facing facing)
        {
            switch (facing)
            {
                case Facing.North:
                    return new Position(current.X, current.Y + 1);
                case Facing.South:
                    return new Position(current.X, current.Y - 1);
                case Facing.West:
                    return new Position(current.X - 1, current.Y);
                case Facing.East:
                    return new Position(current.X + 1, current.Y);
                default:
                    throw new ArgumentOutOfRangeException(nameof(facing), facing, $"Unknown Facing {facing}");
            }
        }

        public static int ManhattenDistance(Position start, Position end)
        {
            return Math.Abs(start.X - end.X) + Math.Abs(start.Y - end.Y);
        }
    }
}