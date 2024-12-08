package pl.szymanski.hubert.utils

class Map2D<T>(private val map: List<List<T>>) {
    val xSize by lazy { map[0].size }
    val ySize by lazy { map.size }

    fun get(x: Int, y: Int): T {
        return map[y][x]
    }

    fun getIfExists(x: Int, y: Int): T? {
        return if (isWithingCoords(x, y)) map[y][x] else null
    }

    fun getIfExists(v: Vector): T? {
        return getIfExists(v.x, v.y)
    }

    fun isWithingCoords(x: Int, y: Int): Boolean {
        return x in 0 until xSize && y in 0 until ySize
    }

    fun elements(): List<T> = map.flatten()

    fun elementsWithCoords(): List<Pair<Pair<Int, Int>, T>> =
        map.mapIndexed { y, line ->
            line.mapIndexed { x, el ->
                x to el
            }.map { it.first to y to it.second }
        }.flatten()

    fun deepCopy(copy: (T) -> T): Map2D<T> {
        return Map2D(
            map.map { line ->
                line.map { copy(it) }.toList()
            }.toList()
        )
    }

    override fun toString(): String {
        return map.joinToString(separator = System.lineSeparator()) { line ->
            line.joinToString(separator = "") { it.toString() }
        }
    }

    companion object {
        fun <T> from(rawInput: List<String>, mapLine: (String) -> List<T>): Map2D<T> {
            if (rawInput.areStringsValid()) throw IllegalArgumentException("Strings have different lengths")
            return Map2D(rawInput.map { mapLine(it) })
        }

        private fun List<String>.areStringsValid() = map { it.length }
            .zipWithNext { l1, l2 -> l1 == l2 }
            .any { !it }
    }
}