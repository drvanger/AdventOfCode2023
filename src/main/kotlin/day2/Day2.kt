package day2

import java.io.File

class Day2 (fileName : String) {
    private val data = File(fileName).readLines().map { it.split(':') }.map { it -> it[1] }

    private val startingBag = Bag(12, 13, 14)
    private val games : MutableList<Game> = mutableListOf()

    init {
        for (element in data) {
            val tmpGame = Game(element)
            games.add(tmpGame)
        }
    }

    fun partOne() {
        var result = 0

        for (i in games.indices) {
            //println("" + (i + 1) + " - " + games[i].isPossible(startingBag))

            if (games[i].isPossible(startingBag)) result += (i + 1)
        }

        println(result)
    }

    fun partTwo() {
        var result = 0

        for (game in games) {
            result += game.getPower()
        }

        println(result)
    }
}

fun main() {
    val d = Day2("/home/duri/intellij-projects/AdventOfCode2023/data/day2.txt")

    d.partTwo()
}