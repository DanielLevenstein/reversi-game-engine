package org.esotericcode.reversi.gameengine.dao

import org.esotericcode.reversi.gameengine.model.{GameBoard, ImmutableReversiBoard}
import play.api.db.slick.DatabaseConfigProvider
import org.esotericcode.reversi.gameengine.repository.GameBoardRepository
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class GameBoardDAO @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends GameBoardRepository {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val db = dbConfig.db


  // Define the Table Schema
  class GameBoardsTable(tag: Tag) extends Table[GameBoard](tag, "game_board") {
    def gameId: Rep[Long] = column[Long]("game_id", O.PrimaryKey)
    def boardState: Rep[String] = column[String]("board_state")
    def currentTurn: Rep[String] = column[String]("current_turn")
    def lastMove: Rep[String] = column[String]("last_move")
    def aiPlayer: Rep[String] = column[String]("ai_player")
    def isAIEnabled: Rep[Boolean] = column[Boolean]("is_ai_enabled")

    def * = (gameId, boardState, currentTurn, lastMove, aiPlayer, isAIEnabled) <> (
      (GameBoard.apply _).tupled,
      GameBoard.unapply
    )
  }

  // Table Query
  private val gameBoards = TableQuery[GameBoardsTable]

  // Run schema creation ONCE, with proper callbacks
  createSchema().onComplete {
    case scala.util.Success(_) =>
      println("Schema created successfully.")
    case scala.util.Failure(ex) =>
      println(s"Failed to create schema: ${ex.getMessage}")
  }

  def createSchema(): Future[Unit] = {
    db.run(gameBoards.schema.createIfNotExists)
  }

  // Get GameBoard by gameId
  def getGameBoard(gameId: Long): Future[Option[GameBoard]] = {
    db.run(gameBoards.filter(_.gameId === gameId).result.headOption)
  }

  // Insert a new GameBoard
  def insertGameBoard(gameBoard: GameBoard): Future[Int] = {
    db.run(gameBoards += gameBoard)
  }

  // Update the boardState and currentTurn of a GameBoard
  def updateGameBoard(gameId: Long, boardState: String, currentTurn: String, lastMove: String): Future[Int] = {
    db.run(gameBoards.filter(_.gameId === gameId).map(gb => (gb.boardState, gb.currentTurn, gb.lastMove))
      .update(boardState, currentTurn, lastMove))
  }
}