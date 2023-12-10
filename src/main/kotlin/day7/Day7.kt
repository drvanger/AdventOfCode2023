package day7

import java.io.File

class Day7  (fileName : String) {
    private val data = File(fileName).readLines()

    private var hands : MutableList<Pair<Hand, Int>> = mutableListOf()

    init {
        for (str in data) {
            var splitted = str.split(" ")
            var card = splitted[0]
            var bid = splitted[1].toInt()

            hands.add(Pair(Hand(card), bid))
        }
    }

    fun partOne() {
        var sortedHands = hands.sortedBy { it.first }

        var result = 0

        for (i in sortedHands.indices) {
            result += (i + 1) * sortedHands[i].second
        }

        println(result)
    }
}

fun main() {
    val d = Day7("/home/duri/intellij-projects/AdventOfCode2023/data/day7.txt")
    d.partOne()
}