package day12

import pl.szymanski.hubert.day12.GardenGroups
import utils.DaySpecification

class GardenGroupsTest extends DaySpecification {

    def 'should pass simple example'() {
        given:
        def input = List.of(
                "AAAA",
                "BBCD",
                "BBCC",
                "EEEC")
        def sut = new GardenGroups(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 140
    }

    def 'should pass part I example'() {
        given:
        def input = testInput("day12")
        def sut = new GardenGroups(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 1930
    }

    def 'should pass part II example'() {
        given:
        def input = testInput("day12")
        def sut = new GardenGroups(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 1206
    }
}
