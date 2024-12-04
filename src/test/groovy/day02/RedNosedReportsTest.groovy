package day02

import pl.szymanski.hubert.day02.RedNosedReports
import utils.DaySpecification

class RedNosedReportsTest extends DaySpecification {

    private List<String> input = testInput("day02")

    def 'should pass part I example'() {
        given:
        def sut = new RedNosedReports(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 2
    }

    def 'should pass part II example'() {
        given:
        def sut = new RedNosedReports(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 4
    }
}
