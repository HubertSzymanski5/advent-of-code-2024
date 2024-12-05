package day05

import pl.szymanski.hubert.day05.PrintQueue
import utils.DaySpecification

class PrintQueueTest extends DaySpecification {

    def 'should pass part I example'() {
        given:
        def input = testInput("day05")
        def sut = new PrintQueue(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 143
    }

    def 'should pass part II example'() {
        given:
        def input = testInput("day05")
        def sut = new PrintQueue(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 123
    }
}
