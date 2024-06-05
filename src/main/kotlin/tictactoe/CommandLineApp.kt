package tictactoe

import java.io.File
import java.util.*

class CommandLineApp {
    private val tempGamesInputFile: File

    init {
        val incompleteGames = this::class.java.getResourceAsStream("/incompleted_games.txt")
        val file = File.createTempFile("tictactoe", ".txt")
        file.deleteOnExit()
        println("Tic Tac Toe Analysis Temporary Store: $file")

        incompleteGames.copyTo(file.outputStream())
        tempGamesInputFile = file
    }

    fun printHelp() {
        println("""
            Please enter following command or Board State (e.g. =========) for analysis.
            REPORT          Generate Completed Game Analysis
            HELP            HELP
            EXIT            Exit
        """.trimIndent())
    }
    
    fun printCompletedGamesReport() {
        tempGamesInputFile.inputStream().use {
            val report = generateCompletedGamesReport(it)
            println(report.getContent())
        }
    }

    fun analyzeBoardState(line: String) {
        try {
            val boardState = BoardState(line)
            val isCompletedGame = boardState.isCompletedGame()
            if (isCompletedGame){
                val winner = boardState.getWinner()
                if (winner != null ){
                    println("This game is completed. The winner is player $winner")
                } else {
                    println("This game is completed. No one win this game")
                }
            } else {
                val message = if (boardState.getNextWinner() == null) "Next player cannot win" else "Next player can win"
                println(message)
            }
            tempGamesInputFile.appendText("\n" + line )
        } catch (e: Exception){
            println(e.message)
        }
    }
}

fun main() {
    val app = CommandLineApp()
    println("""
        ===============================
        Welcome to Tic Tac Toe Analysis
        ===============================
    """.trimIndent())
    app.printHelp()
    while(true){
        when(val line = readln().uppercase(Locale.getDefault())) {
            "REPORT" -> app.printCompletedGamesReport()
            "EXIT" -> return
            "" -> app.printHelp()
            "HELP" -> app.printHelp()
            else -> app.analyzeBoardState(line)
        }
    }
}
