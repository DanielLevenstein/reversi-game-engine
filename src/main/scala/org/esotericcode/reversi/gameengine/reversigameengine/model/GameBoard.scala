package org.esotericcode.reversi.gameengine.reversigameengine.model


import play.api.libs.json.{Format, Json, OFormat, Writes}

case class GameBoard(
                       gameId: Long,
                       boardState: String,
                       currentPlayer: String,
                       aiPlayer: String,
                       lastMove: String,
                       isAIEnabled: Boolean
                     )

object GameBoard {
  implicit val gameBoardFormat: Format[GameBoard] = Json.format[GameBoard]
}