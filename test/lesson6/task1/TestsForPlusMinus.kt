package lesson6.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test


class TestsForPlusMinus {

    @Test
    fun testForPlusMinusEquals() {
        assertEquals(0, plusMinus("0"))
        assertEquals(0, plusMinus("0 + 0"))
        assertEquals(0, plusMinus("0 - 0"))
        assertEquals(-12, plusMinus("0 - 12"))
        assertEquals(24, plusMinus("12 + 12"))
        assertEquals(109, plusMinus("12 + 21 + 42 + 34"))
        assertEquals(-85, plusMinus("12 - 21 - 42 - 34"))
        assertEquals(0, plusMinus("12 - 12 + 45 - 45"))
    }

    @Test
    fun testForPlusMinusException() {
        assertThrows(IllegalArgumentException::class.java) { plusMinus("") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("   ") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("-") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("- 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+ 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("-12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12++12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12 * 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12 + + 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12 + - 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12 + 12.5") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12.5 + 12.5") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12 + 12 + 12 -") }

        //должно выбрасываться исключение, но на деле этого не происходит
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12+12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12-12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12+21+42+34") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12-21-42-34") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("12  21") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus(" 12") }
    }
}