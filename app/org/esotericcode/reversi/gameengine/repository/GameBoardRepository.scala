package org.esotericcode.reversi.gameengine.repository

import org.esotericcode.reversi.gameengine.model.GameBoard
import scala.concurrent.Future


// Trait (interface) - no constructor params
trait GameBoardRepository {
  def getGameBoard(gameId: Long): Future[Option[GameBoard]]
  def insertGameBoard(gameBoard: GameBoard): Future[Int]
  def updateGameBoard(gameId: Long, boardState: String, currentTurn: String): Future[Int]
}
//
//@Singleton
//class GameBoardRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
//                                   (implicit ec: ExecutionContext)
//  extends HasDatabaseConfigProvider[JdbcProfile] {
//
//  import profile.api._
//
//  // Table schema definition
//  private class GameBoardTable(tag: Tag) extends Table[GameBoard](tag, "game_boards") {
//    def gameId = column[Long]("game_id", O.PrimaryKey)
//    def boardState = column[String]("board_state")
//    def currentTurn = column[String]("current_turn")
//    def aiPlayer = column[String]("ai_player")
//    def lastMove = column[String]("last_move")
//    def isAIEnabled = column[Boolean]("ai_enabled")
//
//    def * = (gameId, boardState, currentTurn, aiPlayer, lastMove, isAIEnabled) <> (GameBoard.tupled, GameBoard.unapply)
//  }
//
//  private val gameBoards = TableQuery[GameBoardTable]
//
//  // Get a game board by ID
//  def getGameBoard(gameId: Long): Future[Option[GameBoard]] = {
//    db.run(gameBoards.filter(_.gameId === gameId).result.headOption)
//  }
//
//  // Insert a new game board
//  def insertGameBoard(gameBoard: GameBoard): Future[Int] = {
//    db.run(gameBoards += gameBoard)
//  }
//
//  // Update the boardState and currentTurn for a given gameId
//  def updateGameBoard(gameId: Long, boardState: String, currentTurn: String): Future[Int] = {
//    val query = gameBoards.filter(_.gameId === gameId)
//      .map(gb => (gb.boardState, gb.currentTurn))
//      .update((boardState, currentTurn))
//    db.run(query)
//  }
//}