package day6

import java.io.File
import kotlin.math.*

class Day6  (fileName : String) {
    private val data = File(fileName).readLines()

    private val races : MutableList<Pair<Long, Long>> = mutableListOf()

    init {
        var times = data[0].split(":")[1].strip().split(" ").map { it.strip() }.filter { it.length > 0 }.map { it.toLong() }
        var distances = data[1].split(":")[1].strip().split(" ").map { it.strip() }.filter { it.length > 0 }.map { it.toLong() }

        for (i in times.indices) {
            races.add(Pair(times[i], distances[i]))
        }

        //println(races)
    }

    fun quadraticEqSolver(a : Double, b : Double, c : Double) : Pair<Double, Double> {
        var discriminant = b*b - 4*a*c

        if (discriminant < 0) return Pair(0.0,0.0)
        else if (discriminant == 0.0) {
            var solution = (-1*b + sqrt(discriminant)) / 2 * a
            return Pair(solution, 0.0)
        }
        else {
            var s1 = (-1*b + sqrt(discriminant)) / 2 * a
            var s2 = (-1*b - sqrt(discriminant)) / 2 * a

            return Pair(min(s1, s2), max(s1, s2))
        }
    }

    fun solveRacce(race : Pair<Long, Long>) : Long {
        var solutions = quadraticEqSolver(1.0, -1.0 * race.first, race.second.toDouble())

        var lower = ceil(solutions.first)
        var upper = floor(solutions.second)

        if (lower == solutions.first) lower = ceil(solutions.first + 0.01)
        if (upper == solutions.second) upper = floor(solutions.second - 0.01)

        var interval = (1 + upper - lower).toLong()

        return interval
    }

    fun partOne() {
        var result : Long = 1

        for (race in races) {
            var interval = solveRacce(race)

            result *= interval
        }

        println(result)
    }

    fun partTwo() {
        var time = data[0].split(":")[1].filter { it != ' '}.toLong()
        val distance = data[1].split(":")[1].filter { it != ' '}.toLong()

        println(solveRacce(Pair(time, distance)))
    }
}

fun main() {
    val d = Day6("/home/duri/intellij-projects/AdventOfCode2023/data/day6.txt")
    d.partTwo()
}