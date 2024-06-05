package ticktactoe

import tictactoe.CompletedGamesReport
import tictactoe.generateCompletedGamesReport
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CompletedGamesAnalysisTest {

    @Test
    fun `can generate report`(){
        val lines = listOf("XX=OOO===", "XX=OOO===", "XX=OOO===", "XOX=OX=O=", "XX=OOO===", "XOXOXOXOX", "=========")
        val result = generateCompletedGamesReport(lines.joinToString("\n").byteInputStream())
        assertEquals(CompletedGamesReport(1, 5, 1), result)
        assertEquals("""
            Winner Summary 
            ==============
            Player X: 1 
            Player O: 5 
            Draw: 1 
        """.trimIndent(), result.getContent())
    }

    @Test
    fun `can generate report for huge file`(){
        val inputStream = CompletedGamesAnalysisTest::class.java.getResourceAsStream("/huge_completed_games.txt")
        val result = generateCompletedGamesReport(inputStream)
        assertEquals(CompletedGamesReport(1267200, 1224960, 464640), result)
        assertEquals("""
            Winner Summary 
            ==============
            Player X: 1267200 
            Player O: 1224960 
            Draw: 464640 
        """.trimIndent(), result.getContent())
    }
}