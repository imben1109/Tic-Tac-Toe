package tictactoe

/**
 * Completed Games Report
 */
data class CompletedGamesReport(
    val numberOfXWinner: Int,
    val numberOfOWinner: Int,
    val numberOfDraw: Int
) {
    fun getContent() = """
        Winner Summary 
        ==============
        Player X: $numberOfXWinner 
        Player O: $numberOfOWinner 
        Draw: $numberOfDraw 
    """.trimIndent()
}