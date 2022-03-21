package lesson7.task1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class TestsForPrintMultiplicationProcess {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        Assertions.assertEquals(expectedContent, content)
    }

    @Test
    fun printMultiplicationProcess() {
        fun test(lhv: Int, rhv: Int, res: String) {
            printMultiplicationProcess(lhv, rhv, "temp.txt")
            assertFileContent("temp.txt", res.trimIndent())
            File("temp.txt").delete()
        }

        test(
            12345,
            54321,
            """
                 12345
            *    54321
            ----------
                 12345
            +   24690
            +  37035
            + 49380
            +61725
            ----------
             670592745
             """
        )

        test(
            12345,
            543,
            """
                12345
             *    543
             --------
                37035
             + 49380
             +61725
             --------
              6703335
             """
        )

        test(
            12345,
            34,
            """
               12345
             *    34
             -------
               49380
             +37035
             -------
              419730
             """
        )

        test(
            12345,
            2,
            """
              12345
             *    2
             ------
              24690
             ------
              24690
             """
        )

        test(
            12345,
            0,
            """
                12345
                *0
                --
                 0
                --
                 0
             """
        )
    }
}
