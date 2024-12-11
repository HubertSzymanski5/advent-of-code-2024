package day11

import pl.szymanski.hubert.day11.PlutonianPebbles
import utils.DaySpecification

class PlutonianPebblesTest extends DaySpecification {

    def 'should pass part I example'() {
        given:
        def input = testInput("day11")
        def sut = new PlutonianPebbles(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 55312
    }
}
