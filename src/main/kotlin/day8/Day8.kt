package day8

import java.io.File

class Day8  (fileName : String) {
    private val data = File(fileName).readLines()
    private val instructions = Instructor(data[0])

    private val nodes : MutableList<Pair<String, String>?> = MutableList(17576) { it -> null }

    init {
        for (i in 2 .. data.lastIndex) {
            val splitted = data[i].split("=")

            var name = splitted[0].strip()
            var children = splitted[1].filter { it != '(' && it != ')' }.split(",").map { it.strip() }

            nodes[nodeHash(name)] = Pair(children[0], children[1])

            //println("Name = " + name + ", children = " + children)
        }
    }

    private fun nodeHash(str : String) : Int {
        if (str.length != 3) {
            println("Problem")
            return -1
        }

        return (str[2].code - 65) * 1 + (str[1].code - 65) * 26 + (str[0].code - 65) * 26 * 26
    }

    fun partOne() {
        var currentNode = "AAA"

        var steps = 0

        while (currentNode != "ZZZ") {
            steps++

            if (instructions.getNextInstruction() == 'L') currentNode = nodes[nodeHash(currentNode)]!!.first
            else currentNode = nodes[nodeHash(currentNode)]!!.second

            //println(currentNode)
        }

        println(steps)
    }

    fun partTwoBF() {
        // Find starting nodes

        var currents : MutableList<String> = mutableListOf()

       for (c1 in 65 .. 90) {
           for (c2 in 65 .. 90) {
               var str = "" + c1.toChar() + c2.toChar() + 'A'

               if (nodes[nodeHash(str)] != null) {
                   currents.add(str)
               }
           }
       }

       var steps : Long = 0

       while(true) {
           val instr = instructions.getNextInstruction()

           steps++

           var done = true

           for (i in currents.indices) {
               if (instr == 'L') {
                   currents[i] = nodes[nodeHash(currents[i])]!!.first
               }
               else {
                   currents[i] = nodes[nodeHash(currents[i])]!!.second
               }

               if (currents[i][2] != 'Z') done = false
           }

           if (done) break
       }

       println(steps)
    }

    fun isLCM(number : Long, numbers : List<Int>) : Boolean {
        for (nr in numbers) {
            if (number % nr.toLong() != 0.toLong()) return false
        }

        return true
    }

    fun lcmTwo(nr1 : Long, nr2 : Long) : Long {
        var multipliers1 : MutableSet<Long> = mutableSetOf()
        var multipliers2 : MutableSet<Long> = mutableSetOf()

        var i = 1

        while (multipliers1.intersect(multipliers2).size == 0) {
            multipliers1.add(i * nr1)
            multipliers2.add(i * nr2)

            i++
        }

        return multipliers1.intersect(multipliers2).min()
    }

    fun lcm(numbers : List<Int>) : Long {
        var lcm = numbers[0].toLong()

        for (i in 1 .. numbers.lastIndex) {
            println("Calculating: " + lcm + " - " + numbers[i])

            lcm = lcmTwo(lcm, numbers[i].toLong())
        }

        return lcm
    }

    fun partTwo() {
        // Find starting nodes

        var currents : MutableList<String> = mutableListOf()
        var currentsOriginal : MutableList<String> = mutableListOf()

        var stepList : MutableList<Int> = mutableListOf()

        for (c1 in 65 .. 90) {
            for (c2 in 65 .. 90) {
                var str = "" + c1.toChar() + c2.toChar() + 'Z'

                if (nodes[nodeHash(str)] != null) {
                    currents.add(str)
                    currentsOriginal.add(str)
                }
            }
        }

        for (i in currents.indices) {
            instructions.reset()

            println("Current starting node: " + currents[i])

            var steps = 0

            do {
                steps++

                var instr = instructions.getNextInstruction()

                var previous = currents[i]

                if (instr == 'L') currents[i] = nodes[nodeHash(currents[i])]!!.first
                else currents[i] = nodes[nodeHash(currents[i])]!!.second

                if (currents[i][2] == 'Z') println(currents[i])

                if (currents[i] == previous) break

            } while (currents[i] != currentsOriginal[i])

            /*while (currents[i][2] != 'Z') {
                var instr = instructions.getNextInstruction()

                steps++

                if (instr == 'L') currents[i] = nodes[nodeHash(currents[i])]!!.first
                else currents[i] = nodes[nodeHash(currents[i])]!!.second
            }*/

            stepList.add(steps)
        }

        println(stepList)
        println(lcm(stepList))
    }
}

fun main() {
    val d = Day8("/home/duri/intellij-projects/AdventOfCode2023/data/day8.txt")
    d.partTwo()
}