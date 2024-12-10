package pl.szymanski.hubert.runner

import pl.szymanski.hubert.day01.HistorianHysteria
import pl.szymanski.hubert.day02.RedNosedReports
import pl.szymanski.hubert.day03.MullItOver
import pl.szymanski.hubert.day04.CeresSearch
import pl.szymanski.hubert.day05.PrintQueue
import pl.szymanski.hubert.day06.GuardGallivant
import pl.szymanski.hubert.day07.BridgeRepair
import pl.szymanski.hubert.day08.ResonantCollinearity
import pl.szymanski.hubert.day09.DiskFragmenter
import pl.szymanski.hubert.day10.HoofIt
import pl.szymanski.hubert.utils.readFile
import kotlin.reflect.KClass

class DayRunner {
    companion object {
        fun run(day: Day): DayResult {
            val runnerClass: KClass<out Runner> = when (day) {
                Day.DAY01 -> HistorianHysteria::class
                Day.DAY02 -> RedNosedReports::class
                Day.DAY03 -> MullItOver::class
                Day.DAY04 -> CeresSearch::class
                Day.DAY05 -> PrintQueue::class
                Day.DAY06 -> GuardGallivant::class
                Day.DAY07 -> BridgeRepair::class
                Day.DAY08 -> ResonantCollinearity::class
                Day.DAY09 -> DiskFragmenter::class
                Day.DAY10 -> HoofIt::class
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
            val input = readFile(day.name.lowercase())
            return runDay(day, runnerClass.constructors.first().call(input))
        }

        private fun runDay(day: Day, runner: Runner): DayResult {
            return DayResult(day, runner.runPartI(), runner.runPartII())
        }
    }
}
