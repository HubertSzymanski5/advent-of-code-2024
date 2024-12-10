package day10

import pl.szymanski.hubert.day10.HoofIt
import utils.DaySpecification

class HoofItTest extends DaySpecification {

    def 'should pass part I example'() {
        given:
        def input = testInput("day10")
        def sut = new HoofIt(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 36
    }

    def 'should pass part II example'() {
        given:
        def input = testInput("day10")
        def sut = new HoofIt(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 81
    }
}
