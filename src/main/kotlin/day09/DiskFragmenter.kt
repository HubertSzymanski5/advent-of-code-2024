package pl.szymanski.hubert.day09

import pl.szymanski.hubert.runner.Runner

class DiskFragmenter(rawInput: List<String>) : Runner {

    val disk = rawInput.first().toDisk()

    override fun runPartI(): Long {
        return disk.defragmentBlocks().calculateFilesystemChecksum()
    }

    override fun runPartII(): Long {
        return disk.defragmentSegments().calculateFilesystemChecksum()
    }

    private fun List<Long>.calculateFilesystemChecksum(): Long {
        return mapIndexed { index, l -> index * l }
            .sum()
    }

    private fun String.toDisk() =
        windowed(size = 2, step = 2) { it[0].digitToInt() to it[1].digitToInt() }
            .unzip()
            .let { Disk(it.first + last().digitToInt(), it.second) }
}

data class Disk(val fragments: List<Int>, val gaps: List<Int>) {

    override fun toString(): String {
        return fragments.drop(1).foldIndexed("0" * fragments.first()) { index, res, i ->
            return@foldIndexed res + "." * gaps[index] + (index + 1).toString() * i
        }.toString()
    }

    fun defragmentBlocks(): List<Long> {
        val blocks = mutableListOf<Block>()
        repeat(fragments.first()) { blocks.add(Block(0)) }
        fragments.drop(1).forEachIndexed { index, i ->
            repeat(gaps[index]) { blocks.add(Block(null)) }
            repeat(i) { blocks.add(Block(index + 1L)) }
        }
        blocks.forEach {
            if (it.id == null && !it.moved) {
                val lastUnmoved = blocks.lastUnmoved()
                it.id = lastUnmoved.id
                lastUnmoved.moved = true
            }
        }
        return blocks
            .filter { !it.moved }
            .map { it.id!! }
    }

    fun defragmentSegments(): List<Long> {
        val segments = mutableListOf<Segment>(Segment.File(0, fragments.first()))
        fragments.drop(1).zip(gaps).forEachIndexed { index, it ->
            segments.add(Segment.Gap(it.second))
            segments.add(Segment.File(index + 1L, it.first))
        }
        val nFiles = segments
            .last { it is Segment.File }
            .let { it as Segment.File }.id
        return segments.replacingFile(nFiles).map { it.toList() }.flatten()
    }

    private fun List<Segment>.replacingFile(fileId: Long): List<Segment> {
        if (fileId == 0L) return this
        val (fileLoc, file) = mapIndexed { i, segment -> i to segment }
            .filter { it.second is Segment.File }
            .map { it.first to it.second as Segment.File }
            .first { it.second.id == fileId }

        val (gapLoc, gap) = mapIndexed { i, segment -> i to segment }
            .filter { it.second is Segment.Gap }
            .map { it.first to it.second as Segment.Gap }
            .firstOrNull { it.second.size >= file.size } ?: (null to null)

        if (gap == null || gapLoc == null || gapLoc > fileLoc) return this.replacingFile(fileId - 1)
        gap.size -= file.size

        val withMoved = subList(0, gapLoc) + file + toMutableList().also { it[fileLoc] = Disk.Segment.Gap(file.size) }.subList(gapLoc, size)
        return withMoved
            .filter{ !(it is Disk.Segment.Gap && it.size == 0) }
            .replacingFile(fileId - 1)
    }

    private fun List<Block>.lastUnmoved(): Block =
        last { it.id != null && !it.moved }

    data class Block(var id: Long?, var moved: Boolean = false)

    sealed class Segment {
        data class File(val id: Long, val size: Int) : Segment() {
            override fun toString() = "$id" * size
        }
        data class Gap(var size: Int) : Segment() {
            override fun toString() = "." * size
        }

        fun toList(): List<Long> {
            return when (this) {
                is File -> MutableList(size) { id }
                is Gap -> MutableList(size) { 0L }
            }
        }
    }
}

private operator fun String.times(i: Int): String {
    return this.repeat(i)
}
