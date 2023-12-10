package day5

class Converter(input : List<String>) {
    var conversionMap : MutableMap<Long, Long> = mutableMapOf()

    init {
        conversionMap[0] = 0
        conversionMap[Long.MAX_VALUE] = 0

        for (range in input) {
            //println("Range: " + range)

            var splitted = range.strip().split(" ").map { it.toLong() }

            var sourceStart = splitted[1]
            var sourceEnd = splitted[1] + splitted[2] - 1
            var operation = splitted[0] - splitted[1]

            //println(operation)

            conversionMap[sourceStart] = operation
            if (!conversionMap.containsKey(sourceEnd + 1)) conversionMap[sourceEnd + 1] = 0

            //println(conversionMap)

            //println("Source start: " + splitted[1] + ", source end: " + (splitted[1] + splitted[2] - 1) + ", calculated by: " + (splitted[0] - splitted[1]))
        }

        //println(conversionMap)

        conversionMap = conversionMap.toSortedMap()
        //println(conversionMap)
    }

    fun convert(source : Long) : Long {
        var result : Long = -1
        var currentOperation : Long = 0

        for (key in conversionMap.keys) {
            if (source >= key) {
                currentOperation = conversionMap[key]!!
            }
            else {
                break
            }
        }

        result = source + currentOperation

        return result
    }

    private fun rangeIntersection(r1 : Pair<Long, Long>, r2 : Pair<Long, Long>) : Pair<Long, Long> {
        if (r1.first <= r2.first) {
            if (r1.second < r2.first) return Pair(-1, -1)

            if (r1.second >= r2.second) {
                return r2
            }
            else {
                return Pair(r2.first, r1.second)
            }
        }
        else {
            if (r2.first < r1.first && r2.second < r1.first) return Pair(-1, -1)

            if (r2.second <= r1.second) {
                return Pair(r1.first, r2.second)
            }
            else {
                return r1
            }
        }
    }

    fun convertRange(source : Pair<Long, Long>) : List<Pair<Long, Long>> {
        var result : MutableList<Pair<Long, Long>> = mutableListOf()

        var reamining = source

        println(reamining)
        println(conversionMap.keys)

        for (key in conversionMap.keys) {
            

            if (key in reamining.first .. reamining.second) {
                println(key)

                var tmpRange : Pair<Long, Long> = Pair(reamining.first + conversionMap[key]!!, key + conversionMap[key]!!)

                reamining = Pair(key + 1, reamining.second)

                result.add(tmpRange)

                println("Added " + tmpRange)
            }
        }

        return result
    }
}