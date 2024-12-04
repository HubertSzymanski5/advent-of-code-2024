package day03

import pl.szymanski.hubert.day03.MullItOver
import utils.DaySpecification

class MullItOverTest extends DaySpecification {

    def 'should pass part I example'() {
        given:
        List<String> input = testInput("day03A")
        def sut = new MullItOver(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 161
    }

    def 'should pass part II example'() {
        given:
        List<String> input = testInput("day03B")
        def sut = new MullItOver(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 48
    }
}
