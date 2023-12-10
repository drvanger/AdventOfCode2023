package day2

import kotlin.math.min

class Game (handfulStr : String) {
    var handfulList : MutableList<Handful> = mutableListOf()

    init {
        val handfuls = handfulStr.split(";")



        for (hand in handfuls) {
            val rawData = hand.split(",").map { it.strip() }.map { it.split(" ") }

            var red = 0
            var green = 0
            var blue = 0

            for (color in rawData) {
                val number = color[0].toInt()

                if (color[1] == "red") red = number
                else if (color[1] == "green") green = number
                else if (color[1] == "blue") blue = number
                else println("ERROR")
            }

            var tmpHand = Handful(red, green, blue)
            handfulList.add(tmpHand)

        }
    }

    override fun toString(): String {
        return handfulList.toString()
    }

    fun isPossible(bag : Bag) : Boolean {
        for (hand in handfulList) {
            if (!hand.isPossible(bag)) return false
        }

        return true
    }

    fun minimumPossibleBag() : Bag {
        var red = 0
        var green = 0
        var blue = 0

        for (hand in handfulList) {
            if (hand.red > red) red = hand.red

            if (hand.green > green) green = hand.green

            if (hand.blue > blue) blue = hand.blue
        }

        return Bag(red, green, blue)
    }

    fun getPower() : Int {
        val minBag = minimumPossibleBag()

        return minBag.red * minBag.green * minBag.blue
    }
}