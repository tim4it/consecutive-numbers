package com.tim4it

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ConsecutiveNumbersSpec extends Specification {

    def "Consecutive numbers - ascending - OK"() {
        given:
        def consecutiveNumbers = new ConsecutiveNumbers(consecutiveNumber)

        when:
        def result = consecutiveNumbers.apply()
        def resultStream = consecutiveNumbers.applyStream()
        def applyStreamSimple = consecutiveNumbers.applyStreamSimple()
        def applyFastLimited = consecutiveNumbers.applyFastLimited()

        then:
        result == resultStream
        applyStreamSimple == resultStream
        applyFastLimited == applyStreamSimple
        result == expectedResult

        where:
        consecutiveNumber           | expectedResult
        "012345678910"              | true
        "9101112131415161718192021" | true
        "99100101"                  | true
        "99910001001"               | true
        "99991000010001"            | true
        "1223312234122351223612237" | true
        "12131415"                  | true
    }

    def "Consecutive numbers - descending - OK"() {
        given:
        def consecutiveNumbers = new ConsecutiveNumbers(consecutiveNumber)

        when:
        def result = consecutiveNumbers.apply()
        def resultStream = consecutiveNumbers.applyStream()
        def applyStreamSimple = consecutiveNumbers.applyStreamSimple()
        def applyFastLimited = consecutiveNumbers.applyFastLimited()

        then:
        result == resultStream
        applyStreamSimple == resultStream
        applyFastLimited == applyStreamSimple
        result == expectedResult

        where:
        consecutiveNumber                    | expectedResult
        "1211109876543210"                   | true
        "1051041031021011009998979695949392" | true
        "10011000999998997996"               | true
        "100021000110000999999989997"        | true
        "10000110000099999"                  | true
        "1009998979695949392919089"          | true
        "122121120119118"                    | true
    }

    def "Consecutive numbers - not OK"() {
        given:
        def consecutiveNumbers = new ConsecutiveNumbers(consecutiveNumber)

        when:
        def result = consecutiveNumbers.apply()
        def resultStream = consecutiveNumbers.applyStream()
        def applyStreamSimple = consecutiveNumbers.applyStreamSimple()
        def applyFastLimited = consecutiveNumbers.applyFastLimited()

        then:
        result == resultStream
        applyStreamSimple == resultStream
        applyFastLimited == applyStreamSimple
        result == expectedResult

        where:
        consecutiveNumber                     | expectedResult
        "12111098765432101"                   | false
        "12312412516"                         | false
        "21051041031021011009998979695949392" | false
        "1001100099999899799655"              | false
        "10002100011000099999998999743"       | false
        "100001100000999991"                  | false
        "51009998979695949392919089"          | false
        "348762098374075927682763552"         | false
        "09372675648109034769123683"          | false
        "09372675648109034769123683asadf"     | false
        "348762098374075df927682763552"       | false
    }

    def "Consecutive numbers - can not be parsed to number"() {
        given:
        def consecutiveNumbers = new ConsecutiveNumbers(consecutiveNumber)

        when:
        consecutiveNumbers.apply()

        then:
        thrown(IllegalStateException)

        where:
        consecutiveNumber                        | expectedResult
        "1211s10987654sd32101"                   | false
        "21051041031021wer011009998979695949392" | false
        "1001w100099999899799655"                | false
        "100021000a110dfeeer00099999998999743"   | false
        "10000l11000i00999991"                   | false
        "51009998979ad695949392919089"           | false
        "a348762098374075927682763552"           | false
        "093fd72675648109034769123683"           | false
    }
}
