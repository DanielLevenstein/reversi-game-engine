package org.esotericcode.reversi.gameengine.reversigameengine.service


import org.esotericcode.reversi.gameengine.reversigameengine.model.{GameBoard, ImmutableReversiBoard}
import jakarta.inject.{Inject, Singleton}
import org.esotericcode.reversi.gameengine.reversigameengine.ai.{Node, ScoredNode}
import org.esotericcode.reversi.gameengine.reversigameengine.dao.GameBoardDAO
import play.api.mvc.ControllerComponents

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class GameService @Inject()(cc: ControllerComponents, dao: GameBoardDAO)  {

  // TODO implement constructor
  def getBoard(gameId: Long): Future[Option[String]] = {
    // Load from DB or memory, return BoardState
    dao.getGameBoard(gameId).map(_.map(_.boardState))
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

  def getLastMove(gameId: Long): Future[Option[String]] = {
    // Retrieve last move
    dao.getGameBoard(gameId).map(_.map(_.lastMove))
  }


  def getCurrentTurn(gameId: Long): Future[Option[String]] = {
    // Retrieve current player symbol
    dao.getGameBoard(gameId).map(_.map(_.currentTurn))
  }

  def getAIPlayer(gameId: Long): Future[Option[String]] = {
    // Retrieve AI player symbol
    dao.getGameBoard(gameId).map(_.map(_.aiPlayer))
  }

  def getAIMove(gameId: Long, player: String): Future[Option[String]] = {
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

  def isAIEnabled(gameId: Long): Future[Option[Boolean]] = {
    // Check flag
    dao.getGameBoard(gameId).map(_.map(_.isAIEnabled))
  }
}