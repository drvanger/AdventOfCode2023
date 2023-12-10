package day8

class Instructor (val instructions : String) {
    var index = 0

    override fun toString(): String {
        return index.toString()
    }

    fun getNextInstruction() : Char {
        var result = instructions[index]

        index += 1

        if (index > instructions.lastIndex) index = 0

        return result
    }

    fun reset() {
        index = 0
    }
}