package  org.esotericcode.reversi.gameengine.service

import org.esotericcode.reversi.gameengine.ai.{Node, ScoredNode}
import org.esotericcode.reversi.gameengine.dao.GameBoardDAO
import org.esotericcode.reversi.gameengine.model.{GameBoard, ImmutableReversiBoard}
import javax.inject.{Inject, Singleton}
import play.api.mvc.ControllerComponents

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GameService @Inject()(
                             cc: ControllerComponents,
                             dao: GameBoardDAO
                           )(implicit ec: ExecutionContext) {

  def getBoard(gameId: Long): Future[Option[GameBoard]] = {
    // Load from DB or memory, return BoardState
    dao.getGameBoard(gameId)
  }

  // Insert a new GameBoard
  def insertGameBoard(gameBoard: GameBoard): Future[Int] = {
    dao.insertGameBoard(gameBoard)
  }

  def getValidMoves(gameId: Long, player: String): Future[Seq[String]] = {
    dao.getGameBoard(gameId).map {
      case Some(gameBoard: GameBoard) =>
        val reversiBoard = new ImmutableReversiBoard(gameBoard.boardState)
        reversiBoard.getValidMoves(player)
      case None =>
        Seq.empty
    }
  }

  def makeMove(gameId: Long, player: String, move: String): Future[Option[GameBoard]] = {
    dao.getGameBoard(gameId).map {
      case Some(gameBoard: GameBoard) =>
        makeMoveHelper(gameId, gameBoard, player, move)
      case None =>
        None
    }
  }

  def makeMoveHelper(gameId: Long, gameBoard: GameBoard, player: String, move: String): Some[GameBoard] = {
    val reversiBoard = new ImmutableReversiBoard(gameBoard.boardState)
    val updatedBoard = reversiBoard.makeMove(move, player)
    val nextPlayer = updatedBoard.getNextTurn(player.charAt(0)).toString
    dao.updateGameBoard(gameId, updatedBoard.gameBoard, nextPlayer)
    Some(GameBoard(gameId, updatedBoard.gameBoard, nextPlayer, gameBoard.aiPlayer, gameBoard.isAIEnabled))
  }


  def isValidMove(gameId: Long, player: String, move: String): Future[Boolean] = {
    dao.getGameBoard(gameId).map {
      case Some(gameBoard: GameBoard) =>
        val reversiBoard = new ImmutableReversiBoard(gameBoard.boardState)
        reversiBoard.isValidMove(move, player)
      case None =>
        false
    }
  }


  def getAIMove(gameId: Long, aiPlayer: String): Future[Option[String]] = {
    // Return AI move using Minimax tree
    dao.getGameBoard(gameId).map {
      case Some(gameBoard: GameBoard) =>
        getAiMoveHelper(gameBoard, aiPlayer)
      case None =>
        None
    }
  }

  def getAiMoveHelper(gameBoard: GameBoard, aiPlayer: String): Option[String] = {
    val reversiBoard = new ImmutableReversiBoard(gameBoard.boardState)
    val scoredNode: ScoredNode = Node(reversiBoard, aiPlayer.charAt(0), None).calculate(5)
    scoredNode.node.move
  }

  def makeAIMove(gameId: Long, aiPlayer: String): Future[Option[GameBoard]] = {
    // Return AI move using Minimax tree
    dao.getGameBoard(gameId).map {
      case Some(gameBoard: GameBoard) =>
        val move = getAiMoveHelper(gameBoard, aiPlayer).get
        makeMoveHelper(gameId, gameBoard, aiPlayer, move)
      case None => None
    }
  }
}