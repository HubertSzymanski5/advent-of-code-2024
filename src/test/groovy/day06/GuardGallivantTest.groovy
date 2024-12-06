package day06

import pl.szymanski.hubert.day06.GuardGallivant
import utils.DaySpecification

class GuardGallivantTest extends DaySpecification {

    def 'should pass part I example'() {
        given:
        def input = testInput("day06")
        def sut = new GuardGallivant(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 41
    }

    def 'should pass part II example'() {
        given:
        def input = testInput("day06")
        def sut = new GuardGallivant(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 6
    }
}
