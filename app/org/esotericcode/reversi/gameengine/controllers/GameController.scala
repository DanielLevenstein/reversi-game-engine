package org.esotericcode.reversi.gameengine.controllers

import javax.inject._
import org.esotericcode.reversi.gameengine.model.{GameBoard, ImmutableReversiBoard}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Action, _}
import org.esotericcode.reversi.gameengine.service.GameService
import play.api.libs.json.JsPath.\
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites

import scala.concurrent.ExecutionContext
import scala.util.Random

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

  def newGame: Action[AnyContent] = Action.async {
    val sampleBoard = GameBoard(Random.nextLong(), ImmutableReversiBoard.getEmptyBoard.gameBoard,
      "X", "O", isAIEnabled = true)
    gameService.insertGameBoard(sampleBoard).map { _ =>
      Ok(Json.toJson( sampleBoard.gameId))
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
  def isValidMove(gameId: Long, player: String, move: String): Action[AnyContent] = Action.async {
    gameService.isValidMove(gameId, player, move).map { isValid =>
      Ok(Json.obj("valid" -> isValid))
    }
  }

  def makeMove(gameId: Long): Action[JsValue] = Action.async(parse.json) { request =>
    val player = (request.body \ "player").as[String]
    val move = (request.body \ "move").as[String]
    println(s"Received move: $move by $player for game $gameId")
    gameService.makeMove(gameId, player, move).map {
      case Some(gameBoard) =>
        Ok(Json.toJson(gameBoard))
      case None =>
        NotFound(Json.obj("error" -> "Invalid move or game not found"))
    }.recover {
      case ex: Exception =>
        InternalServerError(Json.obj("error" -> ex.getMessage))
    }
  }

  def getAIMove(gameId: Long, player: String): Action[AnyContent] = Action.async {
    gameService.getAIMove(gameId, player).map {
      case Some(result) => Ok(Json.toJson(result))
      case None => NotFound(Json.obj("error" -> "No valid AI move found"))
    }
  }
  def makeAIMove(gameId: Long): Action[JsValue] = Action.async(parse.json) { request =>
    val player = (request.body \ "player").as[String]

    gameService.makeAIMove(gameId, player).map{
      case Some(result) => Ok(Json.toJson(result))
      case None => NotFound(Json.obj("error" -> "No valid AI move found"))
    }
  }
}