package day01

import pl.szymanski.hubert.day01.HistorianHysteria
import spock.lang.Specification
import utils.TestFileReader

class HistorianHysteriaTest extends Specification {

    private List<String> input = TestFileReader.readFile("day01")

    def 'should pass part I example'() {
        given:
        def sut = new HistorianHysteria(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 11
    }

    def 'should pass part II example'() {
        given:
        def sut = new HistorianHysteria(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 31
    }
}
