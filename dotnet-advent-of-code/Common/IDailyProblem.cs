using System;

namespace Common
{
    public interface IDailyProblem<T1, T2>
    {
        T1 Part1Answer { get; }
        T2 Part2Answer { get; }
    }
}