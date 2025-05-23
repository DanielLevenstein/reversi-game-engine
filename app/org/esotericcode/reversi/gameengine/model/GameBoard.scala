package org.esotericcode.reversi.gameengine.model

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json.{Format, Json}
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

case class GameBoard(
                      gameId: Long,
                      boardState: String,
                      currentTurn: String,
                      lastMove: String,
                      aiPlayer: String,
                      isAIEnabled: Boolean
                    )
object GameBoard {
  implicit val gameBoardFormat: Format[GameBoard] = Json.format[GameBoard]
}

@Singleton
class GameBoardRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                                   (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private class GameBoardTable(tag: Tag) extends Table[GameBoard](tag, "game_boards") {
    def gameId: Rep[Long] = column[Long]("game_id", O.PrimaryKey)
    def boardState: Rep[String] = column[String]("board_state")
    def currentTurn: Rep[String] = column[String]("current_turn")
    def lastMove: Rep[String] = column[String]("last_move")
    def aiPlayer: Rep[String] = column[String]("ai_player")
    def isAIEnabled: Rep[Boolean] = column[Boolean]("ai_enabled")

    def * = (gameId, boardState, currentTurn, lastMove, aiPlayer, isAIEnabled) <> (
      (GameBoard.apply _).tupled,
      GameBoard.unapply
    )

  }

  private val gameBoards = TableQuery[GameBoardTable]

  def getGameBoard(gameId: Long): Future[Option[GameBoard]] =
    db.run(gameBoards.filter(_.gameId === gameId).result.headOption)

  def insertGameBoard(gameBoard: GameBoard): Future[Int] =
    db.run(gameBoards += gameBoard)

  def updateGameBoard(gameId: Long, boardState: String, currentTurn: String, lastMove: String,
                      aiPlayer: String, isAIEnabled: Boolean): Future[Int] = {
    val query = gameBoards.filter(_.gameId === gameId)
      .map(gb => (gb.boardState, gb.currentTurn, gb.lastMove, gb.aiPlayer, gb.isAIEnabled))
      .update((boardState, currentTurn, lastMove, aiPlayer, isAIEnabled))
    db.run(query)
  }
}