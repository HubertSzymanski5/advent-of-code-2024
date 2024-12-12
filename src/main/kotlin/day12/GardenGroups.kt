package pl.szymanski.hubert.day12

import pl.szymanski.hubert.runner.Runner
import pl.szymanski.hubert.utils.Map2D
import pl.szymanski.hubert.utils.WordDirection
import pl.szymanski.hubert.utils.Vector as Point

class GardenGroups(input: List<String>) : Runner {
    private val map = Map2D.from(input) { line -> line.toCharArray().map { GardenSquare(it) } }

    override fun runPartI(): Long {
        return map.createSectors().values.sumOf { section ->
            section.size.toLong() * section.sumOf { it.gardenSquare.otherNeighbours!!.toLong() }
        }
    }

    override fun runPartII(): Long {
        return 0
    }

    private fun Map2D<GardenSquare>.createSectors(): Map<Int, List<Field>> {
        val result = mutableMapOf<Int, List<Field>>()
        elementsWithCoords()
            .map { Field(Point.from(it.first), it.second) }
            .forEach { field ->
                if (field.gardenSquare.sectorId != null) return@forEach
                val sectionId = result.size
                result[sectionId] = field.searchSection(sectionId)
            }
        return result
    }

    private fun Field.searchSection(sectionId: Int): List<Field> {
        if (gardenSquare.sectorId != null) return emptyList()
        val result = WordDirection.mainDirections()
            .map { it.vector + point to map.getIfExists(it.vector + point) }
            .filter { it.second != null }
            .map { Field(it.first, it.second!!) }
            .filter { it.gardenSquare.type == gardenSquare.type }
            .toList()
        this.gardenSquare.sectorId = sectionId
        this.gardenSquare.otherNeighbours = 4 - result.size

        return listOf(this) + result.map { it.searchSection(sectionId) }
            .flatten()
    }
}

data class GardenSquare(val type: Char, var sectorId: Int? = null, var otherNeighbours: Int? = null)
data class Field(val point: Point, val gardenSquare: GardenSquare)