package day11

import java.io.File
import kotlin.math.max
import kotlin.math.min

class Day11  (fileName : String) {
    private val data = File(fileName).readLines()

    private var galaxies : MutableList<Point> = mutableListOf()
    private var emptyRows : MutableSet<Int> = mutableSetOf()
    private var emptyCols : MutableSet<Int> = mutableSetOf()

    init {
        for (row in data.indices) {
            var isEmpty = true

            for (col in data[row].indices) {
                if (data[row][col] == '#') {
                    galaxies.add(Point(row, col))
                    isEmpty = false
                }
            }

            if (isEmpty) {
                emptyRows.add(row)
            }
        }

        for (col in data[0].indices) {
            var isEmpty = true

            for (row in data.indices) {
                if (data[row][col] == '#') {
                    isEmpty = false
                }
            }

            if (isEmpty) {
                emptyCols.add(col)
            }
        }
    }

    fun getEmptyRowsBetween(start : Int, end : Int) : Int {
        var result = 0

        for (row in start .. end) {
            if (emptyRows.contains(row)) result++
        }

        return result
    }

    fun getEmptyColsBetween(start : Int, end : Int) : Int {
        var result = 0

        for (col in start .. end) {
            if (emptyCols.contains(col)) result++
        }

        return result
    }

    fun calculateExpansionTimes (times : Long) : Long {
        var result = 0.toLong()

        for (i in galaxies.indices) {
            for (j in (i + 1) .. galaxies.lastIndex) {
                var gal = galaxies[i]
                var gal2 = galaxies[j]

                if (gal != gal2) {
                    var distance = gal.getDistanceFrom(gal2).toLong()

                    var emptyRows = getEmptyRowsBetween(min(gal.row, gal2.row), max(gal.row, gal2.row))
                    var emptyCols = getEmptyColsBetween(min(gal.col, gal2.col), max(gal.col, gal2.col))

                    distance -= emptyRows
                    distance += emptyRows * times

                    distance -= emptyCols
                    distance += emptyCols * times

                    result += distance

                    //println("" + gal + " - " + gal2 + " distance: " + distance)
                }
            }
        }

        return  result
    }

    fun partOne() {
        println(calculateExpansionTimes(2))
    }

    fun partTwo() {
        println(calculateExpansionTimes(1000000))
    }
}

fun main() {
    val d = Day11("/home/duri/intellij-projects/AdventOfCode2023/data/day11.txt")
    d.partOne()
    d.partTwo()
}