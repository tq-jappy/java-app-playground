package com.example.demo.stream

import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class SlidingWindowSpliteratorSpec extends Specification {

    @Unroll
    def "applySlidingWindow"() {
        expect:
        def result = SlidingWindowSpliterator.windowed(source, windowSize)
                .map({ s -> s.collect(Collectors.toList()) })
                .collect(Collectors.toList())
        result == expected

        where:
        source               | windowSize || expected
        [1, 2, 3, 4] as List | 3          || [[1, 2, 3] as List, [2, 3, 4] as List] as List
        [1, 2] as List       | 3          || [] as List
        [] as List           | 3          || [] as List
        [1, 2, 3, 4] as List | 0          || [] as List
        [1, 2, 3, 4] as List | 2          || [[1, 2] as List, [2, 3] as List, [3, 4] as List] as List
    }
}
