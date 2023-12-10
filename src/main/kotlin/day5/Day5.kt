package day5

import java.io.File

class Day5  (fileName : String) {
    private val data = File(fileName).readLines()

    private var seedRanges : MutableList<Long> = data[0].split(":")[1].strip().split(" ").
        map { it.toLong() }.toMutableList()

    private var converters : MutableList<Converter> = mutableListOf()

    private var seeds : MutableList<Long> = seedRanges

    init {


        //println(seeds)

        var dataProc = data.subList(1,data.lastIndex + 1).toMutableList()
        dataProc.add("")

        //println(dataProc)

        var tmpToConverter : MutableList<String> = mutableListOf()

        for (str in dataProc) {
            var splitted = str.split(" ")

            if (splitted.size > 1 && splitted[1] == "map:") {
                tmpToConverter = mutableListOf()
            }
            else if (str.length == 0) {
                if (tmpToConverter.size > 0) {
                    //println(tmpToConverter)

                    var tmp = Converter(tmpToConverter)
                    converters.add(tmp)
                }
            }
            else {
                // It contains numbers
                tmpToConverter.add(str)
                //println("Added " + str)
            }
        }
    }

    private fun fullConversion(source : Long) : Long {
        var current = source

        for (converter in converters) {
            current = converter.convert(current)
        }

        return current
    }

    fun partOne() {
        var minLocation = Long.MAX_VALUE

        for (i in seeds) {
            var location = fullConversion(i)

            if (location < minLocation) minLocation = location
        }

        println(minLocation)
    }

    fun partTwoBruteForce() {
        var minLocation = Long.MAX_VALUE

        var i = 0
        while (i < seedRanges.lastIndex) {
            //println("Next pair: " + seedRanges[i] + " + " + seedRanges[i + 1])

            var start = seedRanges[i]
            var size = seedRanges[i + 1]

            println("Started new range ")

            for (j in 0 until size) {
                //seeds.add(start + j)



                var location = fullConversion(start + j)

                //println("Seed: " + (start + j) + ", location: " + location)

                if (location < minLocation) minLocation = location
            }

            i += 2
        }

        println(minLocation)
    }

    fun partTwo() {
        // Make initial list of ranges

        var ranges : MutableList<Pair<Long, Long>> = mutableListOf()

        var i = 0
        while (i < seedRanges.lastIndex) {
            var start = seedRanges[i]
            var size = seedRanges[i + 1]

            var end = start + size - 1

            ranges.add(Pair(start, end))

            //println("Added " + Pair(start, end))

            i += 2
        }

        // ---------------

        println(converters[0].convertRange(ranges[0]))
    }
}

fun main() {
    val d = Day5("/home/duri/intellij-projects/AdventOfCode2023/data/day5_test_minimal.txt")
    d.partTwo()
}