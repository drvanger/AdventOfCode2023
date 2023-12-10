package day10

import java.io.File
import java.util.ArrayDeque
import java.util.Queue
import java.util.Stack
import kotlin.math.ceil
import kotlin.math.floor

class Day10 (fileName : String) {
    private val data = File(fileName).readLines()

    private var posS : Pair<Int, Int> = Pair(-1, -1)

    init {
        for (row in data.indices) {
            for (col in data[row].indices) {
                if (data[row][col] == 'S') posS = Pair(row, col)
            }
        }
    }

    fun getNeighbors(position : Pair<Int, Int>) : List<Pair<Int, Int>> {
        var result : MutableList<Pair<Int, Int>> = mutableListOf()

        val currRow = position.first
        val currCol = position.second

        val ch = data[currRow][currCol]

        if (ch == '.') return result
        else if (ch == '|') {
            if (currRow > 0) result.add(Pair(currRow - 1, currCol))

            if (currRow < data.lastIndex) result.add(Pair (currRow + 1, currCol))
        }
        else if (ch == '-') {
            if (currCol > 0) result.add(Pair(currRow, currCol - 1))

            if (currCol < data[currRow].lastIndex) result.add(Pair(currRow, currCol + 1))
        }
        else if (ch == 'L') {
            if (currRow > 0) result.add(Pair(currRow - 1, currCol))

            if (currCol < data[currRow].lastIndex) result.add(Pair(currRow, currCol + 1))
        }
        else if (ch == 'J') {
            if (currRow > 0) result.add(Pair(currRow - 1, currCol))

            if (currCol > 0) result.add(Pair(currRow, currCol - 1))
        }
        else if (ch == '7') {
            if (currRow < data.lastIndex) result.add(Pair (currRow + 1, currCol))

            if (currCol > 0) result.add(Pair(currRow, currCol - 1))
        }
        else if (ch == 'F') {
            if (currRow < data.lastIndex) result.add(Pair (currRow + 1, currCol))

            if (currCol < data[currRow].lastIndex) result.add(Pair(currRow, currCol + 1))
        }
        else if (ch == 'S') {
            if (currRow > 0) result.add(Pair(currRow - 1, currCol))

            if (currRow < data.lastIndex) result.add(Pair (currRow + 1, currCol))

            if (currCol > 0) result.add(Pair(currRow, currCol - 1))

            if (currCol < data[currRow].lastIndex) result.add(Pair(currRow, currCol + 1))
        }
        else {
            println("Shouldn't be here at all. :(  ch = " + ch)
            return result
        }

        return result
    }

    private fun <T> printMatrix(matrix : List<List<T>>) {
        for (line in matrix) {
            println(line)
        }
    }

    private fun printMatrixStr(matrix : List<String>) {
        for (line in matrix) {
            println(line)
        }
    }

    private fun getEmptyTrack() : MutableList<MutableList<Pair<Int, Int>>> {
        var track : MutableList<MutableList<Pair<Int, Int>>> = mutableListOf()

        for (line in data) {
            var tmpRow : MutableList<Pair<Int, Int>> = mutableListOf()

            for (ch in line) {
                tmpRow.add(Pair(-1, -1))
            }

            track.add(tmpRow)
        }

        return track
    }

    fun findLoop() : List<List<Pair<Int, Int>>> {
        var stack : Stack<Pair<Int, Int>> = Stack()

        var visited : MutableSet<Pair<Int, Int>> = mutableSetOf()

        var current = posS

        var track = getEmptyTrack()

        //stack.push(current)

        for (neighborOfStart in getNeighbors(posS)) {
            if (!getNeighbors(neighborOfStart).contains(posS)) continue

            var found : Boolean = false

            var track = getEmptyTrack()

            //track[posS.first][posS.second] = neighborOfStart
            track[neighborOfStart.first][neighborOfStart.second] = posS

            current = neighborOfStart
            stack.push(current)

            while(stack.isNotEmpty()) {
                current = stack.pop()

                if (data[current.first][current.second] != 'S') {
                    visited.add(current)
                }

                for (neighbor in getNeighbors(current)) {


                    if (!visited.contains(neighbor) && getNeighbors(neighbor).contains(current)) {

                        if (neighbor == posS && current == neighborOfStart) continue

                        stack.push(neighbor)
                        visited.add(neighbor)

                        if (data[neighbor.first][neighbor.second] == 'S' && current != neighborOfStart) {
                            //println("Found it! - current: " + current)
                            found = true

                            track[neighbor.first][neighbor.second] = current

                            //printMatrix(track)

                            return track

                            //break
                        }

                        track[neighbor.first][neighbor.second] = current

                        if(found) break
                    }
                }
            }


            if (found) break
        }

        //printMatrix(track)

        return track
    }

    fun partOne() {
        var loop = findLoop()

        //printMatrix(loop)

        var loopLength = 0

        for (element in loop) {
            for (position in element) {
                if (position != Pair(-1, -1)) loopLength++
            }
        }

        println(loopLength / 2)
    }

    fun partTwo() {
        var cleanMap : MutableList<String> = mutableListOf()

        var loop = findLoop()

        //var rowLength = loop[0].size + 2

        //var paddingRow = ".".repeat(rowLength)

        //cleanMap.add(paddingRow)

        for (row in data.indices) {
            var tmpStr = ""

            for (col in data[row].indices) {
                if (loop[row][col] == Pair(-1, -1)) {
                    tmpStr += '.'
                }
                else {
                    tmpStr += data[row][col]
                }
            }

            //tmpStr += '.'

            cleanMap.add(tmpStr)
        }

        //cleanMap.add(paddingRow)

       //printMatrixStr(cleanMap)

        // Magnifying the map horizontally

        var magnified : MutableList<String> = mutableListOf()

        for (row in 0 .. cleanMap.lastIndex * 2) {
            var tmpRow = " "

            for (col in 0 .. cleanMap[0].lastIndex * 2) {
                //print("(" + (row / 2.0) + "," + (col / 2.0) + ")")
                //print(" ")

                var currRow = row / 2.0
                var currCol = col / 2.0

                if (currRow.equals(floor(currRow)) && currCol.equals(floor(currCol))) {
                    tmpRow += cleanMap[currRow.toInt()][currCol.toInt()]
                }
                else if (!currRow.equals(floor(currRow)) && !currCol.equals(floor(currCol))){
                    tmpRow += ' '
                }
                else if (currRow.equals(floor(currRow)) && !currCol.equals(floor(currCol))) {
                    var cLeft = floor(currCol).toInt()
                    var cRight = ceil(currCol).toInt()

                    var left = Pair(currRow.toInt(), cLeft)
                    var right = Pair(currRow.toInt(), cRight)

                    if (getNeighbors(left).contains(right) && cleanMap[left.first][left.second] != '.') {
                        tmpRow += '-'
                    }
                    else {
                        tmpRow += ' '
                    }
                }
                else {
                    var rUp = floor(currRow).toInt()
                    var rDown = ceil(currRow).toInt()

                    var up = Pair(rUp, currCol.toInt())
                    var down = Pair(rDown, currCol.toInt())

                    if (getNeighbors(up).contains(down) && cleanMap[up.first][up.second] != '.') {
                        tmpRow += '|'
                    }
                    else {
                        tmpRow += ' '
                    }
                }
            }
            //println()

            tmpRow += ' '

            magnified.add(tmpRow)
        }

        var paddingRow = " ".repeat(magnified[0].length)
        magnified.add(paddingRow)
        magnified.add(0, paddingRow)

        printMatrixStr(magnified)

        var visited = bfs(magnified, Pair(0,0))

        var result = 0

        for (row in magnified.indices) {
            for (col in magnified[row].indices) {
                if (magnified[row][col] == '.' && !visited.contains(Pair(row, col))) {
                    //println("Found " + Pair(row, col))
                    //print('O')
                    result++
                }
                else {
                    //print(magnified[row][col])
                }
            }
            //println()
        }

        println(result)

    }

    private fun bfs(map: List<String>, startPos: Pair<Int, Int>): Set<Pair<Int, Int>> {
        var visited : MutableSet<Pair<Int, Int>> = mutableSetOf()

        var current = startPos

        var queue : Queue<Pair<Int, Int>> = ArrayDeque()

        queue.add(current)
        visited.add(current)

        while(queue.isNotEmpty()) {
            current = queue.remove()

            for (neighbor in getEmptyNeighbour(current, map)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor)

                    queue.add(neighbor)
                }
            }
        }

        return visited
    }

    private fun getEmptyNeighbour(pos : Pair<Int, Int>, map : List<String>) : List<Pair<Int, Int>> {
        var result : MutableList<Pair<Int, Int>> = mutableListOf()

        // Left
        if (pos.second > 0 && (map[pos.first][pos.second - 1] == ' ' || map[pos.first][pos.second - 1] == '.')) {
            result.add(Pair(pos.first, pos.second - 1))
        }

        // Right
        if (pos.second < map[0].lastIndex && (map[pos.first][pos.second + 1] == ' ' || map[pos.first][pos.second + 1] == '.')) {
            result.add(Pair(pos.first, pos.second + 1))
        }

        // Up
        if (pos.first > 0 && (map[pos.first - 1][pos.second] == ' ' || map[pos.first - 1][pos.second] == '.')) {
            result.add(Pair(pos.first - 1, pos.second))
        }

        // Down
        if (pos.first < map.lastIndex && (map[pos.first + 1][pos.second] == ' ' || map[pos.first + 1][pos.second] == '.')) {
            result.add(Pair(pos.first + 1, pos.second))
        }

        return result
    }
}

fun main() {
    val d = Day10("/home/duri/intellij-projects/AdventOfCode2023/data/day10.txt")

    d.partTwo()
}