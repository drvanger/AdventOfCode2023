package day2

class Handful (val red : Int, val green : Int, val blue : Int) {
    override fun toString(): String {
        return "{" + "R: " + red + ", G: " + green + ", B: " + blue + "}"
    }

    fun isPossible (bag : Bag) : Boolean {
        if (bag.red < red) return false
        else if (bag.green < green) return false
        else if (bag.blue < blue) return false
        else return true
    }
}