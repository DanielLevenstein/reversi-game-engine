package org.esotericcode.reversi.gameengine.reversigameengine.controller
import org.esotericcode.reversi.gameengine.reversigameengine.service.GameService
import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.Json
import org.mockito.Mockito._
import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerTest

import scala.concurrent.{ExecutionContext, Future}

class GameControllerSpec extends PlaySpec with GuiceOneAppPerTest with MockitoSugar {

  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global
  implicit val timeout: Timeout = Timeout(5.seconds)

  "GameController GET /games/:id/valid-moves" should {
    "return valid player moves when sent player = X" in {
      val mockService = mock[GameService]

      val controller = new GameController(stubControllerComponents(), mockService)
      val gameId = 1L
      val player = "X"

      when(mockService.getValidMoves(gameId, player)).thenReturn(Future.successful(Seq("d3", "c4")))

      val request = FakeRequest(GET, s"/games/$gameId/valid-moves?player=$player")
      val result = controller.getValidMoves(gameId, player).apply(request)

      status(result) mustBe OK
      contentType(result) mustBe Some("application/json")
      contentAsJson(result) mustBe Json.toJson(Seq("d3", "c4"))
    }
  }


  "GameController GET /games/:id/valid-moves" should {
    "return valid ai moves when sent player = O" in {
      val mockService = mock[GameService]
      val controller = new GameController(stubControllerComponents(), mockService)

      val gameId = 1L
      val player = "O"
      when(mockService.getValidMoves(gameId, player))
        .thenReturn(Future.successful(Seq("d3", "c4")))

      val request = FakeRequest(GET, s"/games/$gameId/valid-moves?player=$player")
      val result = controller.getValidMoves(gameId, player).apply(request)

      status(result) mustBe OK
      contentType(result) mustBe Some("application/json")
    }
  }
}