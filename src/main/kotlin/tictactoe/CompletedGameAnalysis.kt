package tictactoe

import java.io.InputStream

fun generateCompletedGamesReport(inputStream: InputStream): CompletedGameReport {
    var numberOfOWinner = 0;
    var numberOfXWinner = 0;
    var numberOfDraw = 0
    inputStream.bufferedReader().forEachLine { line ->
        if (line.isNotEmpty()){
            when(BoardState(line).getWinner()) {
                Player.O -> numberOfOWinner++
                Player.X -> numberOfXWinner++
                else -> numberOfDraw++
            }
        }
    }

    return CompletedGameReport(
        numberOfXWinner, numberOfOWinner, numberOfDraw
    )
}

fun main() {
    val inputStream = object {}.javaClass.getResourceAsStream("/completed_games.txt")
    val result = generateCompletedGamesReport(inputStream)
    println(result.getContent())
}
