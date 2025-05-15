package org.esotericcode.reversi.gameengine.reversigameengine.model


import play.api.libs.json.{Format, Json}

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