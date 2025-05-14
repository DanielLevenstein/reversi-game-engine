package org.esotericcode.reversi.gameengine.reversigameengine.model

import play.api.libs.json.{Json, OFormat}

case class BoardResponse(board: String)
object BoardResponse {
  implicit val format: OFormat[BoardResponse] = Json.format[BoardResponse]
}
