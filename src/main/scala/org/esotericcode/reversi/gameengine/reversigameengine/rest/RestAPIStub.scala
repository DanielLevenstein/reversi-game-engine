package org.esotericcode.reversi.gameengine.reversigameengine.rest

/**
 * TODO: Create a rest API which interfaces with the ImmutableReversiBoard
 * This is a very rough draft of the design and will be updated as we make progress
 *
 * Methods to implement:
 * POST getBoard -> database.getBoard(gameID)
 * POST getValidMoves -> board.getValidMoves(gameID,player)
 * POST makeMove -> board.makeMove() -> saveBoardToDatabase(gameID, nextPlayer)
 * GET currentTurn database.getCurrentTurn(gameID)
 * GET isValidMove -> board.isValidMove(gameID, player)
 * GET getLastMove -> database.getLastMove(gameID)
 * GET getAIPlayer -> database.getAIPlayer(gameID)
 * POST getMoveFromAI -> minMaxTree.getMoveFromAI(gameID, player)
 * GET isAIEnabled -> database.isAIEnabled()
 *
 * I don't know if I am getting the terminology right.
 * Please correct me if I am wrong.
 *
 * Database board object should have the following fields
 * gameId : Long
 * boardStateID : Long
 * gameState : String
 * currentPlayer : Char
 * aiPlayer : Char
 * lastMove : String
 * isAIEnabled : Boolean
 */
class RestAPIStub {

}
