package day1

import java.io.File

class Day1 (fileName : String) {
    private val data = File(fileName).readLines()

    fun partOne() {
        var dataProcessed : Long = data.map { it.filter { it.isDigit() } }.map { "" + it.first() + it.last() }.map { it.toLong() }.sum()

        println(dataProcessed)
    }

    private fun digitize(str : String) : String {
        var result = str

        result = result.replace("one", "o1e")
        result = result.replace("two", "t2o")
        result = result.replace("three", "th3ree")
        result = result.replace("four", "fo4ur")
        result = result.replace("five", "fi5ve")
        result = result.replace("six", "s6x")
        result = result.replace("seven", "sev7en")
        result = result.replace("eight", "eigh8t")
        result = result.replace("nine", "ni9ne")

        return result
    }

    fun partTwo() {
        var dataProcessed = data.map { digitize(it) }.map { it.filter { it.isDigit() } }.map { "" + it.first() + it.last() }.map { it.toLong() }.sum()

        println(dataProcessed)
    }
}

fun main() {
    val d = Day1("/home/duri/intellij-projects/AdventOfCode2023/data/day1_test2.txt")

    d.partTwo()
}