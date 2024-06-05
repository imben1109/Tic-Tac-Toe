package tictactoe


class BoardState (private val line: String) {

    init {
        require(line.length == 9) {
            "Required exactly 9 Characters"
        }
        require(line.all { it == 'X' || it == 'O' || it == '='  }) {
            "Unexpected Character - ${line.find { !(it == 'X' || it == 'O' || it == '=' ) }}"
        }
    }

    fun getWinner(): Player? =
        getHorizontalWinner() ?: getVerticalWinner() ?: getDiagonalWinner()

    fun getNextPlayer(): Player {
        val numberOfX = line.count{ it == Player.X.charValue}
        val numberOfO = line.count{ it == Player.O.charValue}
        return if (numberOfO > numberOfX) {
            Player.X
        } else {
            Player.O
        }
    }

    fun getNextWinner(): Player? {
        val nextPlayer = getNextPlayer()

        for (i in line.indices){
            if (line[i] == '=') {
                val nextPossibleBoardState = line.replaceRange(i, i+1, nextPlayer.toString())
                if (BoardState(nextPossibleBoardState).getWinner() == nextPlayer) {
                    return nextPlayer
                }
            }
        }

        return null
    }

    fun isCompletedGame(): Boolean {
        if (getWinner() != null ) {
            return true
        }

        return !line.any { it == '=' }
    }

    private fun getHorizontalWinner(): Player? {
        for (i in 0..2){
            val horizontal = listOf(line[i*3 + 0], line[i*3 + 1], line[i*3 + 2])
            if (horizontal.count{it == Player.X.charValue } == 3){
                return Player.X;
            }
            if (horizontal.count{it == Player.O.charValue } == 3){
                return Player.O
            }
        }
        return null;
    }

    private fun getVerticalWinner(): Player? {
        for (i in 0..2) {
            val vertical = listOf(line[i], line[3+i], line[6+i])
            if (vertical.count{it == Player.X.charValue } == 3){
                return Player.X
            }
            if (vertical.count{it == Player.O.charValue } == 3){
                return Player.O
            }
        }
        return null;
    }

    private fun getDiagonalWinner() : Player?{
        val diagonal1 = listOf(line[0], line[4], line[8])
        if (diagonal1.count{it == Player.X.charValue } == 3){
            return Player.X
        }
        if (diagonal1.count{it == Player.O.charValue } == 3){
            return Player.O
        }

        val diagonal2 = listOf(line[2], line[4], line[6])
        if (diagonal2.count{it == Player.X.charValue } == 3){
            return Player.X
        }
        if (diagonal2.count{it == Player.O.charValue } == 3){
            return Player.O
        }
        return null
    }

}