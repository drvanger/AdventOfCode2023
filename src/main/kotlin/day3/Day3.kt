package day3

import java.io.File

class Day3 (fileName : String) {
    private val data = File(fileName).readLines()
    private var numbers : MutableList<Number> = mutableListOf()

    private var symbols : MutableSet<Point> = mutableSetOf()
    private var stars : MutableList<Number> = mutableListOf()

    init {
        var nr : String = ""

        for (row in data.indices) {
            nr = ""

            for (col in data[0].indices) {
                if (data[row][col] != '.' && !data[row][col].isDigit()) {
                    symbols.add(Point(col, row))
                }

                if (data[row][col] == '*') {
                    val starTmp = Number(-1, row, col, col)
                    stars.add(starTmp)
                }

                if (!data[row][col].isDigit()) {
                    if (nr.length > 0) {
                        val nrTmp = Number(nr.toInt(), row, col - nr.length, col - 1)

                        numbers.add(nrTmp)

                        nr = ""

                        //println("Added: " + nrTmp)
                    }
                }
                else if (data[row][col].isDigit()) {
                    nr += data[row][col]
                }
            }

            if (nr.length > 0) {
                val nrTmp = Number(nr.toInt(), row, data[0].length - nr.length, data[0].length - 1)

                numbers.add(nrTmp)

                //println("Added: " + nrTmp)
            }
        }
    }

    fun partOne() {
        var result = 0

        for (number in numbers) {
            if (number.getNeighbours(data.size, data[0].length).intersect(symbols).size > 0) {
                //println(number)

                result += number.value
            }
        }

        println(result)
    }

    fun partTwo() {
        var result : Long = 0

        for (star in stars) {
            var neighbours : MutableList<Int> = mutableListOf()

            for (number in numbers) {
                if (star.getNeighbours(data.size, data[0].length).intersect(number.getCoords()).size > 0) {
                    //println("Star at " + star.getCoords().elementAt(0) + " is adjacent to " + number.value)

                    neighbours.add(number.value)
                }
            }

            if (neighbours.size == 2) {
                result += neighbours[0] * neighbours[1]
            }
        }

        println(result)
    }
}

fun main() {
    val d = Day3("/home/duri/intellij-projects/AdventOfCode2023/data/day3_levi.txt")
    d.partOne()
}