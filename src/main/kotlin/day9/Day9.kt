package day9

import java.io.File

class Day9  (fileName : String) {
    private val data = File(fileName).readLines().map { it.split(" ") }.map { it.filter { it.length > 0 } }.
        map { it.map { it.toLong() } }


    private fun isAllZeroes(sequence : List<Long>) : Boolean {
        return (sequence.filter { it == 0.toLong() }.size == sequence.size)
    }

    private fun getNextSequence(sequence: List<Long>) : List<Long> {
        var result : MutableList<Long> = mutableListOf()

        for (i in 1 .. sequence.lastIndex) {
            result.add(sequence[i] - sequence[i - 1])
        }

        return result
    }

    private fun extrapolate(sequence: List<Long>) : Long {
        if (isAllZeroes(sequence)) return 0

        return extrapolate(getNextSequence(sequence)) + sequence[sequence.lastIndex]
    }

    fun partOne() {
        var result = 0.toLong()

        for (sequence in data) {
            result += extrapolate(sequence)
        }

        println(result)
    }

    fun partTwo() {
        var dataReversed = data.map { it.reversed() }

        var result = 0.toLong()

        for (sequence in dataReversed) {
            result += extrapolate(sequence)

            //println(extrapolate(sequence))
        }

        println(result)
    }
}

fun main() {
    val d = Day9("/home/duri/intellij-projects/AdventOfCode2023/data/day9.txt")
    d.partTwo()
}