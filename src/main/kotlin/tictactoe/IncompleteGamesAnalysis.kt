package tictactoe

fun main(){
    val inputStream = object {}.javaClass.getResourceAsStream("/incompleted_games.txt")

    inputStream.bufferedReader().forEachLine {
        val boardState = BoardState(it)
        val message = if (boardState.getNextWinner() == null) "Next player cannot win" else "Next player can win"
        println("$it $message")
    }
}