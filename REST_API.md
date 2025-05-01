
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
