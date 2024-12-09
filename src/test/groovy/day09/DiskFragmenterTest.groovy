package day09

import pl.szymanski.hubert.day09.DiskFragmenter
import utils.DaySpecification

class DiskFragmenterTest extends DaySpecification {

    def 'should pass part I example'() {
        given:
        def input = testInput("day09")
        def sut = new DiskFragmenter(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 1928
    }

    def 'should pass part II example'() {
        given:
        def input = testInput("day09")
        def sut = new DiskFragmenter(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 2858
    }
}
