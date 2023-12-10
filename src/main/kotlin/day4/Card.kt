package day4

class Card (var winning : Set<Int>, var numbers : Set<Int>) {
    override fun toString(): String {
        return "Winning numbers: " + winning + ", own numbers: " + numbers
    }
}