package org.esotericcode.reversi.gameengine.reversigameengine.model
import scala.concurrent.{ExecutionContext, Future}
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.json.{Format, Json}
import slick.jdbc.JdbcProfile

case class GameBoard(
                      gameId: Long,
                      boardState: String,
                      currentTurn: String,
                      aiPlayer: String,
                      lastMove: String,
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
    def gameId = column[Long]("game_id", O.PrimaryKey)
    def boardState = column[String]("board_state")
    def currentTurn = column[String]("current_turn")
    def aiPlayer = column[String]("ai_player")
    def lastMove = column[String]("last_move")
    def isAIEnabled = column[Boolean]("ai_enabled")

    def * = (gameId, boardState, currentTurn, aiPlayer, lastMove, isAIEnabled) <> (GameBoard.tupled, GameBoard.unapply)
  }

  private val gameBoards = TableQuery[GameBoardTable]

  def getGameBoard(gameId: Long): Future[Option[GameBoard]] =
    db.run(gameBoards.filter(_.gameId === gameId).result.headOption)

  def insertGameBoard(gameBoard: GameBoard): Future[Int] =
    db.run(gameBoards += gameBoard)

  def updateGameBoard(gameId: Long, boardState: String, currentTurn: String,
                      aiPlayer: String, lastMove: String, isAIEnabled: Boolean): Future[Int] = {
    val query = gameBoards.filter(_.gameId === gameId)
      .map(gb => (gb.boardState, gb.currentTurn, gb.aiPlayer, gb.lastMove, gb.isAIEnabled))
      .update((boardState, currentTurn, aiPlayer, lastMove, isAIEnabled))
    db.run(query)
  }
}