
* TODO: Create a rest API which interfaces with the ImmutableReversiBoard
* This is a very rough draft of the design and will be updated as we make progress
*
* Methods to implement:
* GET /games/{gameId}/board -> database.getBoard(gameID)
* GET /games/{gameId}/valid-moves?player=val -> board.getValidMoves(gameID,player)
* POST /games/{gameId}/move -> board.makeMove() -> saveBoardToDatabase(gameID, nextPlayer)
* GET /games/{gameId}/current-turn database.getCurrentTurn(gameID)
* GET /games/is-valid-move -> board.isValidMove(gameID, player)
* GET /games/{gameId}/last-move -> database.getLastMove(gameID)
* GET /games/{gameId}/ai-player -> database.getAIPlayer(gameID)
* POST /games/{gameId}/ai-move -> minMaxTree.getMoveFromAI(gameID, player)
* GET /games/{gameId}/ai-enabled -> database.isAIEnabled()

API Notes:
The game board is stored as a single string with \n as the delimiter separating rows.
The getValidMoves method current returns a list of String values using the chess algebra notation. 
Player and turn variables are currently stored as 'X' and 'O' values 
    with 'X' mapping to black pieces and 'O' mapping to white pieces and empty squares denoted by ' ' char. 

I don't know if I am getting the terminology right.
Please correct me if I am wrong.

Database board object should have the following fields
* gameId : Long
* boardStateID : Long
* gameState : String
* currentPlayer : Char
* aiPlayer : Char
* lastMove : String
* isAIEnabled : Boolean

}
