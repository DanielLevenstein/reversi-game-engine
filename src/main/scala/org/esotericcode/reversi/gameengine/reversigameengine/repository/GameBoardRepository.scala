package org.esotericcode.reversi.gameengine.reversigameengine.repository

import org.esotericcode.reversi.gameengine.reversigameengine.model.GameBoard

import scala.concurrent.Future

trait GameBoardRepository {
  def getGameBoard(gameId: Long): Future[Option[GameBoard]]
  def insertGameBoard(gameBoard: GameBoard): Future[Int]
  def updateGameBoard(gameId: Long, boardState: String, currentTurn: String): Future[Int]
}