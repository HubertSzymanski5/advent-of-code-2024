package pl.szymanski.hubert.runner

import pl.szymanski.hubert.day01.HistorianHysteria
import pl.szymanski.hubert.utils.readFile

class DayRunner {
    companion object {
        fun run(day: Day): DayResult {
            val input = readFile(day.name.lowercase())
            return when (day) {
                Day.DAY01 -> runDay(day, HistorianHysteria(input))
                Day.DAY02 -> TODO()
                Day.DAY03 -> TODO()
                Day.DAY04 -> TODO()
                Day.DAY05 -> TODO()
                Day.DAY06 -> TODO()
                Day.DAY07 -> TODO()
                Day.DAY08 -> TODO()
                Day.DAY09 -> TODO()
                Day.DAY10 -> TODO()
                Day.DAY11 -> TODO()
                Day.DAY12 -> TODO()
                Day.DAY13 -> TODO()
                Day.DAY14 -> TODO()
                Day.DAY15 -> TODO()
                Day.DAY16 -> TODO()
                Day.DAY17 -> TODO()
                Day.DAY18 -> TODO()
                Day.DAY19 -> TODO()
                Day.DAY20 -> TODO()
                Day.DAY21 -> TODO()
                Day.DAY23 -> TODO()
                Day.DAY24 -> TODO()
                Day.DAY25 -> TODO()
            }
        }

        private fun runDay(day: Day, runner: Runner): DayResult {
            return DayResult(day, runner.runPartI(), runner.runPartII())
        }
    }
}
