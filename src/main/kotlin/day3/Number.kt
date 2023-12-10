package day3

class Number(val value : Int, val row : Int, val colStart : Int, val colEnd : Int) {
    fun getCoords() : Set<Point> {
        var result = mutableSetOf<Point>()

        for (col in colStart .. colEnd) {
            result.add(Point(col, row))
        }

        return result
    }

    fun getNeighbours(rows : Int, cols : Int) : Set<Point> {
        var result = mutableSetOf<Point>()

        val maxRow = rows -1
        val maxCol = cols - 1

        // To its left
        if (colStart > 0) {
            result.add(Point(colStart - 1, row))
        }

        // To its right
        if (colEnd < maxCol) {
            result.add(Point(colEnd + 1, row))
        }

        // Up
        if (row > 0) {
            for (col in colStart .. colEnd) {
                result.add(Point(col, row - 1))
            }
        }

        // Down
        if (row < maxRow) {
            for (col in colStart .. colEnd) {
                result.add(Point(col, row + 1))
            }
        }

        // Up left
        if (row > 0 && colStart > 0) {
            result.add(Point(colStart - 1, row - 1))
        }

        // Up right
        if (row > 0 && colEnd < maxCol) {
            result.add(Point(colEnd + 1, row - 1))
        }

        // Down left
        if (row < maxRow && colStart > 0) {
            result.add(Point(colStart - 1, row +1))
        }

        // Down right
        if (row < maxRow && colEnd < maxCol) {
            result.add(Point(colEnd + 1, row + 1))
        }

        return result
    }

    override fun toString(): String {
        return "" + value + " (" + row + ", " + colStart + " - " + colEnd + ")"
    }
}