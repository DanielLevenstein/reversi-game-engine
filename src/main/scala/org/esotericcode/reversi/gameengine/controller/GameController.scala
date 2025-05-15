package org.esotericcode.reversi.gameengine.controller

import jakarta.inject._
import org.esotericcode.reversi.gameengine.model.{BoardResponse, GameBoard}
import org.esotericcode.reversi.gameengine.service.GameService
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class GameController @Inject()(cc: ControllerComponents, gameService: GameService)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def createSample = Action.async {
    var sampleBoard = GameBoard(1L, "initialState", "X", "O", "none", isAIEnabled = false)
    gameService.insertGameBoard(sampleBoard).map { _ =>
      Ok("Inserted sample game board")
    }
  }

  def getSample = Action.async {
    gameService.getBoard(1L).map {
      case Some(board) => Ok(s"Got board: $board")
      case None        => NotFound("No board found")
    }
  }

  def getBoard(gameId: Long): Action[AnyContent] = Action.async {
    gameService.getBoard(gameId).map {
      case Some(board) => Ok(Json.toJson(BoardResponse(board)))
      case None => NotFound("Game not found")
    }
  }

  def getValidMoves(gameId: Long, player: String): Action[AnyContent] = Action.async {
    gameService.getValidMoves(gameId, player).map { moves =>
      Ok(Json.toJson(moves))
    }
  }

  def makeMove(gameId: Long): Action[JsValue] = Action.async(parse.json) { request =>
    val move = (request.body \ "move").as[String]
    val player = (request.body \ "player").as[String]

    gameService.makeMove(gameId, move, player).map {
      case Some(updatedBoard) =>
        Ok(Json.toJson(updatedBoard)(GameBoard.gameBoardFormat.asInstanceOf[Writes[Any]]))
      case None =>
        NotFound(Json.obj("error" -> "Invalid move or game not found"))
    }.recover {
      case ex: Exception =>
        InternalServerError(Json.obj("error" -> ex.getMessage))
    }
  }

  def isValidMove(gameId: Long, player: String, move: String): Action[AnyContent] = Action.async {
    gameService.isValidMove(gameId, player, move).map { isValid =>
      Ok(Json.obj("valid" -> isValid))
    }
  }

  def getLastMove(gameId: Long): Action[AnyContent] = Action.async {
    gameService.getLastMove(gameId).map {
      case Some(move) => Ok(Json.obj("lastMove" -> move))
      case None => NotFound(Json.obj("error" -> "Move not found"))
    }
  }

  def getCurrentTurn(gameId: Long): Action[AnyContent] = Action.async {
    gameService.getCurrentTurn(gameId).map {
      case Some(player) => Ok(Json.obj("currentTurn" -> player))
      case None => NotFound(Json.obj("error" -> "AI player not set"))
    }
  }

  def getAIPlayer(gameId: Long): Action[AnyContent] = Action.async {
    gameService.getAIPlayer(gameId).map {
      case Some(player) => Ok(Json.obj("aiPlayer" -> player))
      case None => NotFound(Json.obj("error" -> "AI player not set"))
    }
  }

  def performAIMove(gameId: Long, player: String): Action[AnyContent] = Action.async {
    gameService.getAIMove(gameId, player).map {
      case Some(result) => Ok(Json.obj("aiMove" -> result))
      case None => NotFound(Json.obj("error" -> "No valid AI move found"))
    }
  }

  def isAIEnabled(gameId: Long): Action[AnyContent] = Action.async {
    gameService.isAIEnabled(gameId).map { enabled =>
      Ok(Json.obj("aiEnabled" -> enabled))
    }
  }
}