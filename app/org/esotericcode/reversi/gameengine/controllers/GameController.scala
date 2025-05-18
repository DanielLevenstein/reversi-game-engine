package org.esotericcode.reversi.gameengine.controllers

import javax.inject._
import org.esotericcode.reversi.gameengine.model.{GameBoard, ImmutableReversiBoard}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, _}
import org.esotericcode.reversi.gameengine.service.GameService
import play.api.libs.json.JsPath.\

import scala.concurrent.ExecutionContext

@Singleton
class GameController @Inject()(cc: ControllerComponents, gameService: GameService)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {

  def createSample: Action[AnyContent] = Action.async {
    val sampleBoard = GameBoard(1L, ImmutableReversiBoard.getEmptyBoard.gameBoard,
      "X", "O", isAIEnabled = true)
    gameService.insertGameBoard(sampleBoard).map { _ =>
      Ok("Inserted sample game board")
    }
  }

  def getSample: Action[AnyContent] = Action.async {
    gameService.getBoard(1L).map {
      case Some(board) => Ok(s"Got board: $board")
      case None        => NotFound("No board found")
    }
  }

  def getBoard(gameId: Long): Action[AnyContent] = Action.async {
    gameService.getBoard(gameId).map {
      case Some(board) => Ok(Json.toJson(board))
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

  def getAIMove(gameId: Long): Action[AnyContent] = Action.async {
    gameService.getAIMove(gameId).map {
      case Some(result) => Ok(Json.toJson(result))
      case None => NotFound(Json.obj("error" -> "No valid AI move found"))
    }
  }
  def performAIMove(gameId: Long): Action[AnyContent] = Action.async {
    gameService.getAIMove(gameId).map {
      case Some(result) => Ok(Json.obj("aiMove" -> result))
      case None => NotFound(Json.obj("error" -> "No valid AI move found"))
    }
  }
}