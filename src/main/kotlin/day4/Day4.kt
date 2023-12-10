package day4

import java.io.File
import kotlin.math.pow

class Day4  (fileName : String) {
    var cards : MutableList<Card> = mutableListOf()

    private val data = File(fileName).readLines().map { it.split(":") }.map { it -> it[1] }.map { it.split("|") }.
        map { it.map { it.strip() } }

    init {
        for (str in data) {
            //println(str[1].split(" ").filter { it.length > 0 })

            var winningList = str[0].split(" ").filter { it.length > 0 }.map { it.toInt() }.toSet()
            var numberSet = str[1].split(" ").filter { it.length > 0 }.map { it.toInt() }.toSet()

            var card = Card(winningList, numberSet)

            cards.add(card)
        }
    }

    fun partOne() {
        var result = 0

        for (card in cards) {
            var winningNumbers = card.winning.intersect(card.numbers).size

            var value = (2.0).pow(winningNumbers - 1).toInt()

            //println(value)

            result += value
        }

        println(result)
    }

    fun partTwo() {
        var quantities : MutableList<Int> = mutableListOf()

        for (card in cards) {
            quantities.add(1)
        }

        for (i in cards.indices) {
            var card = cards[i]

            var winningNumbers = card.winning.intersect(card.numbers).size

            for (j in (i + 1) .. (i + winningNumbers)) {
                quantities[j] += quantities[i]
            }

            //println("After playing card " + (i + 1) + ", quantities are: " + quantities)
        }


        var result = 0
        for (card in quantities) {
            result += card
        }

        println(result)
    }
}

fun main() {
    val d = Day4("/home/duri/intellij-projects/AdventOfCode2023/data/day4.txt")

    d.partTwo()
}