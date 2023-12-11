package day11

import kotlin.math.abs

class Point (var row : Int, var col : Int) {
    override fun toString(): String {
        return "(" + row + ", " + col + ")"
    }

    fun getDistanceFrom(other : Point) : Int {
        return abs(other.row - row) + abs(other.col - col)
    }
}