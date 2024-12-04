package pl.szymanski.hubert.day04

import pl.szymanski.hubert.runner.Runner

class CeresSearch(private val rawInput: List<String>) : Runner {
    private val map by lazy { Map2D.from(rawInput) }

    override fun runPartI(): Long {
        var sum = 0
        for (x in 0 until map.xSize) {
            for (y in 0 until map.ySize) {
                sum += map.xmasCountAt(x, y)
            }
        }
        return sum.toLong()
    }

    override fun runPartII(): Long {
        var sum = 0L
        for (x in 1 until map.xSize - 1) {
            for (y in 1 until map.ySize - 1) {
                sum += map.crossedMasCountAt(x, y)
            }
        }
        return sum
    }

    private fun Map2D.crossedMasCountAt(x: Int, y: Int): Int {
        if (get(x, y) != 'A') return 0
        if (checkForWords(x - 1, y - 1, Vector(1, 1), setOf("MAS", "SAM"))
            && checkForWords(x + 1, y - 1, Vector(-1, 1), setOf("MAS", "SAM"))
        ) return 1
        return 0
    }

    private fun Map2D.xmasCountAt(x: Int, y: Int): Int {
        if (get(x, y) != 'X') return 0
        return getPossibleWordDirectionsFrom(x, y)
            .count { checkForWords(x, y, it.vector) }
    }

    private fun Map2D.checkForWords(x: Int, y: Int, v: Vector, words: Set<String> = setOf("XMAS")): Boolean {
        val word = (0 until words.first().length).fold("") { acc, i -> acc + get(x + i * v.x, y + i * v.y) }
        return word in words
    }

    private fun Map2D.getPossibleWordDirectionsFrom(x: Int, y: Int): Set<WordDirection> {
        val result = mutableSetOf<WordDirection>()
        if (x >= 3)
            result.add(WordDirection.W)
        if (x < xSize - 3)
            result.add(WordDirection.E)
        if (y >= 3)
            result.add(WordDirection.N)
        if (y < ySize - 3)
            result.add(WordDirection.S)

        if (result.contains(WordDirection.N) && result.contains(WordDirection.W))
            result.add(WordDirection.NW)
        if (result.contains(WordDirection.N) && result.contains(WordDirection.E))
            result.add(WordDirection.NE)
        if (result.contains(WordDirection.S) && result.contains(WordDirection.W))
            result.add(WordDirection.SW)
        if (result.contains(WordDirection.S) && result.contains(WordDirection.E))
            result.add(WordDirection.SE)

        return result
    }
}