//package org.esotericcode.reversi.gameengine.reversigameengine.service
//import akka.util.Timeout
//import org.esotericcode.reversi.gameengine.reversigameengine.dao.GameBoardDAO
//import org.esotericcode.reversi.gameengine.reversigameengine.model.{GameBoard, ImmutableReversiBoard}
//import org.scalatestplus.play._
//import org.mockito.Mockito._
//import org.scalatest.time.SpanSugar.convertIntToGrainOfTime
//import org.scalatestplus.mockito.MockitoSugar
//import play.api.test.Helpers.{await, stubControllerComponents}
//
//import scala.concurrent.{ExecutionContext, Future}
//
//class GameServiceSpec extends PlaySpec with MockitoSugar {
//
//
//  val mockDAO = mock[GameBoardDAO]
//  val service = new GameService(mockDAO)
//  implicit val timeout: Timeout = Timeout(5.seconds)
//  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global
//
//  "GameService#getValidMoves" should {
//    "return a list of valid moves for a given board and player" in {
//      val mockDAO = mock[GameBoardDAO]
//      val service = new GameService(mockDAO)
//      val gameId = 1L
//      val player = "X"
//      val aiPlayer = "O"
//      val boardState= ImmutableReversiBoard.getEmptyBoard.gameBoard
//
//      val emptyGameBoard = GameBoard(gameId, boardState, player, aiPlayer,"", true)
//
//      when(mockDAO.getGameBoard(gameId)).thenReturn(Future.successful(Some(emptyGameBoard)))
//
//      val result = await(service.getValidMoves(gameId, player))
//      result mustBe a[Seq[_]]
//    }
//  }
//}