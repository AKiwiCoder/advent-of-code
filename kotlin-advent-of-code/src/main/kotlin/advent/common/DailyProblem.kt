package advent.common

interface DailyProblem<T1, T2> {
    fun getPart1Answer() : T1
    fun getPart2Answer() : T2
}