package pl.szymanski.hubert.day04

class Map2D(private val map: List<List<Char>>) {
    val xSize by lazy { map[0].size }
    val ySize by lazy { map.size }

    fun get(x: Int, y: Int): Char {
        return map[y][x]
    }

    override fun toString(): String {
        return map.toString()
    }

    companion object {
        fun from(rawInput: List<String>): Map2D {
            if (rawInput.areStringsValid()) throw IllegalArgumentException("Strings have different lengths")
            return Map2D(rawInput.map { it.toCharArray().toList() })
        }

        private fun List<String>.areStringsValid() = map { it.length }
            .zipWithNext { l1, l2 -> l1 == l2 }
            .any { !it }
    }
}