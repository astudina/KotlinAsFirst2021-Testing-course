package lesson5.task1

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class TestsForContainsIn {

    @Test
    fun testsForContainsInTrue() {
        assertTrue(containsIn(mapOf(), mapOf()))
        assertTrue(containsIn(mapOf(), mapOf("a" to "n", "c" to "cc")))
        assertTrue(containsIn(mapOf("a" to "n"), mapOf("a" to "n")))
        assertTrue(containsIn(mapOf("c" to "cc"), mapOf("a" to "n", "c" to "cc")))
        assertTrue(
            containsIn(
                mapOf("a" to "aa", "b" to "bb", "h" to "heh"),
                mapOf("a" to "aa", "b" to "bb", "h" to "heh")
            )
        )
    }

    @Test
    fun testsForContainsInFalse() {
        assertFalse(containsIn(mapOf("a" to "n"), mapOf()))
        assertFalse(containsIn(mapOf("a" to "n"), mapOf("a" to "l")))
        assertFalse(containsIn(mapOf("a" to "n"), mapOf("l" to "n")))
        assertFalse(containsIn(mapOf("a" to "n"), mapOf("b" to "z")))
        assertFalse(containsIn(mapOf("c" to "mda"), mapOf("cc" to "mda")))
        assertFalse(
            containsIn(
                mapOf("a" to "aa", "b" to "bb", "he" to "heh"),
                mapOf("a" to "aa", "b" to "bb", "h" to "heh")
            )
        )
    }
}
