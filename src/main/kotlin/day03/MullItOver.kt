package pl.szymanski.hubert.day03

import pl.szymanski.hubert.runner.Runner

class MullItOver(private val input: List<String>) : Runner {
    private val oneLineInput by lazy { input.joinToString("") }

    override fun runPartI(): Long {
        return oneLineInput.findAllMulCommands().sumOf { it.execute() }
    }

    override fun runPartII(): Long {
        return oneLineInput.findAllEnabledMulCommands().sumOf { it.execute() }
    }

    private fun String.findAllMulCommands(): List<MulCommand> {
        return MUL_REGEX.findAll(this)
            .map { it.value to it.range.first }
            .map { MulCommand.from(it.first, it.second) }
            .toList()
    }

    private fun String.findAllEnabledMulCommands(): List<MulCommand> {
        val enabledFrom = listOf(0) + findAllDoCommands().map { it.at }.toList()
        val disabledFrom = findAllDontCommands().map { it.at }.toList()
        return findAllMulCommands()
            .filter { it.isEnabled(enabledFrom, disabledFrom) }
    }

    private fun MulCommand.isEnabled(doFrom: List<Int>, dontFrom: List<Int>): Boolean {
        return doFrom.findFirstLessThan(at) > dontFrom.findFirstLessThan(at)
    }

    private fun List<Int>.findFirstLessThan(n: Int) = this.lastOrNull { it <= n } ?: Int.MIN_VALUE

    private fun String.findAllDoCommands(): List<Command.Do> {
        return DO_REGEX.findAll(this)
            .map { it.range.first }
            .map { Command.Do(it) }
            .toList()
    }

    private fun String.findAllDontCommands(): List<Command.Dont> {
        return DONT_REGEX.findAll(this)
            .map { it.range.first }
            .map { Command.Dont(it) }
            .toList()
    }

    sealed interface Command {
        class Do(val at: Int) : Command
        class Dont(val at: Int) : Command
    }

    data class MulCommand(private val x1: Long, private val x2: Long, val at: Int) {
        fun execute(): Long = x1 * x2

        companion object {
            fun from(string: String, at: Int): MulCommand {
                val (x1, x2) = string.substringAfter('(')
                    .substringBefore(')')
                    .split(',')
                    .map { it.toLong() }
                return MulCommand(x1, x2, at)
            }
        }
    }

    companion object {
        private val MUL_REGEX = "mul\\([0-9]{1,3},[0-9]{1,3}\\)".toRegex()
        private val DO_REGEX = "do\\(\\)".toRegex()
        private val DONT_REGEX = "don't\\(\\)".toRegex()
    }
}