package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestWordAutomatonTest {

    private final TestWordAutomaton automaton =
            new TestWordAutomaton();

    @ParameterizedTest(name = "recognize(\"{0}\") = {1}")
    @CsvSource({
            "'', 0",
            "abc, 0",
            "abcdef, 0",
            "T, 1",
            "abcT, 1",
            "TE, 2",
            "abcTE, 2",
            "TES, 3",
            "abcTES, 3",
            "TEST, F",
            "abcTESTabc, F",
            "TESTTEST, F",
            "test, 0",
            "Test, 0"
    })
    void shouldReturnCorrectState(String input, String expected) {
        assertEquals(expected, automaton.recognize(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "TTEST",
            "TETEST",
            "TESTEST",
            "TTTEST",
            "abcTTESTxyz",
            "TETESTabc",
            "TESTTEST"
    })
    void shouldRecognizeTestAfterOverlappingPrefixes(String input) {
        assertEquals("F", automaton.recognize(input));
    }
}