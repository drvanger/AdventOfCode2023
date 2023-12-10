package day2

class Bag(val red : Int, val green : Int, val blue : Int) {
    override fun toString(): String {
        return "{" + "R: " + red + ", G: " + green + ", B: " + blue + "}"
    }
}