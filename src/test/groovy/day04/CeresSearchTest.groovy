package day04

import pl.szymanski.hubert.day04.CeresSearch
import utils.DaySpecification

class CeresSearchTest extends DaySpecification {

    def 'should pass part I example'() {
        given:
        def input = testInput("day04A")
        def sut = new CeresSearch(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 18
    }

    def 'should pass part II example'() {
        given:
        def input = testInput("day04B")
        def sut = new CeresSearch(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 9
    }
}
