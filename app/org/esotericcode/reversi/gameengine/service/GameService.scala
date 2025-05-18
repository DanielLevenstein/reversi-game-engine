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

  // TODO implement constructor
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

  def makeMove(gameId: Long, move: String, player: String): Future[Object] = {
    dao.getGameBoard(gameId).map {
      case Some(gameBoard: GameBoard) =>
        val reversiBoard = new ImmutableReversiBoard(gameBoard.boardState)
        reversiBoard.makeMove(move, player)
      case None =>
        Seq.empty
    }
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


  def getAIMove(gameId: Long): Future[Option[String]] = {
    // Return AI move using Minimax tree
    dao.getGameBoard(gameId).map {
      case Some(gameBoard: GameBoard) =>
        val reversiBoard = new ImmutableReversiBoard(gameBoard.boardState)
        val scoredNode: ScoredNode = Node(reversiBoard, gameBoard.aiPlayer.charAt(0), None).calculate(5)
        scoredNode.node.move.getOrElse(None).asInstanceOf[Option[String]]
      case None =>
        None
    }
  }

}