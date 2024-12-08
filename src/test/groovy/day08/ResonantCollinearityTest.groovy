package day08

import pl.szymanski.hubert.day08.ResonantCollinearity
import utils.DaySpecification

class ResonantCollinearityTest extends DaySpecification {

    def 'should pass part I example'() {
        given:
        def input = testInput("day08")
        def sut = new ResonantCollinearity(input)
        
        when:
        def result = sut.runPartI()
        
        then:
        assert result == 14
    }

    def 'should pass part II example'() {
        given:
        def input = testInput("day08")
        def sut = new ResonantCollinearity(input)
        
        when:
        def result = sut.runPartII()
        
        then:
        assert result == 34
    }
}
