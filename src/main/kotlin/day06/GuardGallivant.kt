package pl.szymanski.hubert.day06

import pl.szymanski.hubert.runner.Runner
import pl.szymanski.hubert.utils.Map2D
import pl.szymanski.hubert.utils.WordDirection

class GuardGallivant(rawInput: List<String>) : Runner {
    private val map = Map2D.from(rawInput) { it.toCharArray().toFields() }

    override fun runPartI(): Long {
        return map.deepCopy { it.deepCopy() }
            .also { it.walkthrough(it.getStartingPosition(), WordDirection.N) }
            .elements()
            .count { it.visited }
            .toLong()
    }

    override fun runPartII(): Long {
        var obstacles = 0L
        val startingPosition = map.getStartingPosition()
        for (x in 0 until map.xSize) {
            for (y in 0 until map.ySize) {
                if (map.get(x, y).type == Field.Type.OBSTACLE) continue
                if (x == startingPosition.first && y == startingPosition.second) continue
                val newMap = map.deepCopy { it.deepCopy() }
                newMap.get(x, y).type = Field.Type.OBSTACLE
                if (newMap.walkthrough(startingPosition, WordDirection.N) == WalkResult.LOOP) {
                    obstacles++
                }
            }
        }
        return obstacles
    }

    private fun Map2D<Field>.walkthrough(
        startingPosition: Pair<Int, Int>,
        startingDirection: WordDirection
    ): WalkResult {
        var position = startingPosition
        var direction = startingDirection
        while (true) {
            val nextCoords = position.first + direction.vector.x to position.second + direction.vector.y
            if (!isWithingCoords(nextCoords.first, nextCoords.second)) return WalkResult.NORMAL
            val nextField = get(nextCoords.first, nextCoords.second)
            when (nextField.type) {
                Field.Type.OBSTACLE -> direction = direction.turnRight()
                Field.Type.FREE -> {
                    position = nextCoords
                    val field = get(position.first, position.second)
                    if (field.hasBeenVisitedInDirectionBefore(direction)) return WalkResult.LOOP
                    field.visit(direction = direction)
                }
            }
        }
    }

    private fun WordDirection.turnRight(): WordDirection =
        when (this) {
            WordDirection.N -> WordDirection.E
            WordDirection.E -> WordDirection.S
            WordDirection.S -> WordDirection.W
            WordDirection.W -> WordDirection.N
            else -> throw IllegalArgumentException("Cannot turn right from diagonal direction")
        }

    private fun Map2D<Field>.getStartingPosition(): Pair<Int, Int> {
        return elementsWithCoords()
            .firstOrNull { it.second.visited }
            ?.first ?: throw IllegalStateException("No starting position has been found")
    }

    private fun CharArray.toFields() = map {
        return@map when (it) {
            '#' -> Field(
                type = Field.Type.OBSTACLE,
                visited = false
            )

            '.' -> Field(
                type = Field.Type.FREE,
                visited = false
            )

            '^' -> Field(
                type = Field.Type.FREE,
                visited = true,
                visitDirections = mutableSetOf(WordDirection.N)
            )

            else -> throw IllegalArgumentException("Unknown field char: $it")
        }
    }.toList()
}

data class Field(
    var type: Type,
    var visited: Boolean = false,
    val visitDirections: MutableSet<WordDirection> = mutableSetOf()
) {
    override fun toString(): String = when (type) {
        Type.OBSTACLE -> "#"
        Type.FREE -> if (visited) "x" else "."
    }

    fun hasBeenVisitedInDirectionBefore(direction: WordDirection) =
        visited && visitDirections.contains(direction)

    fun visit(direction: WordDirection) {
        visited = true
        visitDirections.add(direction)
    }

    fun deepCopy(): Field {
        return copy(
            type = type,
            visited = visited,
            visitDirections = visitDirections.toMutableSet()
        )
    }

    enum class Type {
        FREE, OBSTACLE
    }
}

private enum class WalkResult {
    NORMAL, LOOP
}
