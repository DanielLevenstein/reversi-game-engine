package  org.esotericcode.reversi.gameengine.controllers

import org.mockito.Mockito._
import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test._
import org.esotericcode.reversi.gameengine.service.GameService

import scala.concurrent.{ExecutionContext, Future}

class GameControllerSpec extends PlaySpec with GuiceOneAppPerTest with MockitoSugar {

  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global
  implicit val timeout: Timeout = Timeout(5.seconds)

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