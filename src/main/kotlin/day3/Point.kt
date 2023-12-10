package day3

data class Point(val col : Int, val row : Int) {
    override fun toString(): String {
        return "(" + row + ", " + col + ")"
    }
}