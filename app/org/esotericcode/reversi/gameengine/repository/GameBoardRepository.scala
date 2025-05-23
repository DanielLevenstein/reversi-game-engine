package org.esotericcode.reversi.gameengine.repository

import org.esotericcode.reversi.gameengine.model.GameBoard
import scala.concurrent.Future


// Trait (interface) - no constructor params
trait GameBoardRepository {
  def getGameBoard(gameId: Long): Future[Option[GameBoard]]
  def insertGameBoard(gameBoard: GameBoard): Future[Int]
  def updateGameBoard(gameId: Long, boardState: String, currentTurn: String, lastMove: String): Future[Int]
}
