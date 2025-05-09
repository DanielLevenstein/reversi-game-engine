  package org.esotericcode.reversi.gameengine.reversigameengine

  import org.esotericcode.reversi.gameengine.reversigameengine.model.{ImmutableReversiBoard, Node, ScoredNode}
  import org.springframework.stereotype.Component

  import java.util


@Component object CommandLineGame {
  def main(args: Array[String]): Unit = {
    var aiPlayer = None : Option[Char]
    if (args.length > 0 && args.contains("--ai-player")) {
      val aiPlayerStr = args.apply(args.indexOf("--ai-player") + 1): String
      aiPlayer =
      if(aiPlayerStr.length == 1){
        Some(aiPlayerStr.charAt(0).toUpper)
      } else {
        None
      }
    }
    if (args.length > 0 && args.contains("--command-line")) commandLineGame(aiPlayer)

  }

  def commandLineGame(aiPlayer: Option[Char]): Unit = {
    var currentPlayer = 'X'
    var board = ImmutableReversiBoard.getEmptyBoard
    var quit = false
    while (!quit) {
      System.out.println("CurrentPlayer=" + currentPlayer)
      board.prettyPrint
      if (aiPlayer.nonEmpty && currentPlayer == aiPlayer.get) {
        val scoredNode: ScoredNode = Node(board, aiPlayer.get, None).calculate(3)
        val board2 = scoredNode.node.board
        currentPlayer = nextPlayer(board2, currentPlayer)
        board = board2
        System.out.println("The AI made move "+ scoredNode.node.move);
        board.prettyPrint
      }
      else {
        System.out.println("Enter move in algebraic notation for player " + currentPlayer + ": ")
        val algebraicNotation = System.console.readLine
        if (algebraicNotation != "q") {
          val board2 = board.makeMove(algebraicNotation, currentPlayer)
          val opponent = ImmutableReversiBoard.getOpponent(currentPlayer)
          if (board2 != null && board2.getValidMoves(opponent).nonEmpty) {
            currentPlayer =
              if (currentPlayer == 'X') 'O'
              else 'X'
            board = board2
          }
          else System.out.println("Enter a valid move or enter 'q' to quit: " + algebraicNotation)
        } else
          quit = true
      }
    }
  }

  private def nextPlayer(board2: ImmutableReversiBoard, currentPlayer: Char): Char = {
    val opponent = ImmutableReversiBoard.getOpponent(currentPlayer)
    if (board2 != null && board2.getValidMoves(opponent).nonEmpty) {
      val nextPlayer =
        if (currentPlayer == 'X') 'O'
        else 'X'
      nextPlayer
    } else {
      currentPlayer
    }
  }

}
