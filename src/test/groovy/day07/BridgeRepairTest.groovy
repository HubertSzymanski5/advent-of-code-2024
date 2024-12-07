package day07

import pl.szymanski.hubert.day07.BridgeRepair
import utils.DaySpecification

class BridgeRepairTest extends DaySpecification {

    def 'should pass part I example'() {
        given:
        def input = testInput("day07")
        def sut = new BridgeRepair(input)

        when:
        def result = sut.runPartI()

        then:
        assert result == 3749
    }

    def 'should pass part II example'() {
        given:
        def input = testInput("day07")
        def sut = new BridgeRepair(input)

        when:
        def result = sut.runPartII()

        then:
        assert result == 11387
    }

}
