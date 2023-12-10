package day7

class Hand (var cards : String) : Comparable<Hand> {
    val cardStrength : Map<Char, Int> = mapOf(
        'A' to 1000,
        'K' to 100,
        'Q' to 50,
        'J' to 1,
        'T' to 30,
        '9' to 9,
        '8' to 8,
        '7' to 7,
        '6' to 6,
        '5' to 5,
        '4' to 4,
        '3' to 3,
        '2' to 2
    )

    /*
    Five of a kind - 6
    Four of a kind - 5
    Full house - 4
    Three of a kind - 3
    Two pair - 2
    One pair - 1
    High card - 0
     */
    fun getType() : Int {
        var cardMap : MutableMap<Char, Int> = mutableMapOf()

        for (ch in cards) {
            if (cardMap.containsKey(ch)) {
                cardMap[ch] = cardMap[ch]!! + 1
            }
            else {
                cardMap[ch] = 1
            }
        }

        if (!cardMap.containsKey('J')) {
            if (cardMap.size == 5) return 0
            else if (cardMap.size == 4) return 1
            else if (cardMap.size == 1) return 6
            else if (cardMap.size == 3) {
                if (cardMap.containsValue(3)) return 3
                else return 2
            }
            else if (cardMap.size == 2) {
                if (cardMap.containsValue(4)) return 5
                else return 4
            }
            else {
                println("Shouldn't be here at all")
                return -1
            }
        }
        else {
            var indexOfJ = cards.indexOf('J')

            var maxType = -1

            for (ch in arrayOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2')) {
                var replacedCards = cards.toMutableList()
                replacedCards[indexOfJ] = ch

                var tmpHand = Hand(replacedCards.joinToString(""))

                var currTypeVal = tmpHand.getType()

                if (currTypeVal > maxType) maxType = currTypeVal
            }

            return maxType
        }
    }

    /*
    A > B -> A has a higher value than B
     */
    override fun compareTo(other: Hand): Int {
        if (this.getType() > other.getType()) return 1
        else if (this.getType() < other.getType()) return -1
        else {
            for (i in cards.indices) {
                if (cardStrength[this.cards[i]]!! > cardStrength[other.cards[i]]!!) return 1
                else if (cardStrength[this.cards[i]]!! < cardStrength[other.cards[i]]!!) return -1
            }

            return 0
        }
    }

    override fun toString(): String {
        return cards
    }
}