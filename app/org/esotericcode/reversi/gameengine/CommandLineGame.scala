package org.esotericcode.reversi.gameengine

  import org.esotericcode.reversi.gameengine.ai.{Node, ScoredNode}
  import org.esotericcode.reversi.gameengine.model.ImmutableReversiBoard


  object CommandLineGame {
  def main(args: Array[String]): Unit = {
    var aiPlayer = None : Option[Char]
    var lookahead = None : Option[Int]
    var allAI = false
    if (args.length > 0 && args.contains("--all-ai") ) {
      allAI = true
    } else if (args.length > 0 && args.contains("--ai-player") ) {
      val aiPlayerStr = args.apply(args.indexOf("--ai-player") + 1): String
      aiPlayer =
        if (aiPlayerStr.length == 1) {
          Some(aiPlayerStr.charAt(0).toUpper)
        } else {
          None
        }
    } else if (args.length > 0 && args.contains("--lookahead") ) {
      lookahead = Some(args.apply(args.indexOf("--lookahead") + 1).toInt)
    }
    commandLineGame(aiPlayer, lookahead, allAI)
  }

  private def commandLineGame(aiPlayer: Option[Char], lookahead: Option[Int], allAI: Boolean): Unit = {
    var currentPlayer = 'X'
    var board = ImmutableReversiBoard.getEmptyBoard
    var quit = false
    while (!quit) {
      System.out.println("CurrentPlayer=" + currentPlayer)
      board.prettyPrint
      if(board.isGameOver){ quit = true}
      if (allAI  || aiPlayer.nonEmpty && currentPlayer == aiPlayer.get) {
        val scoredNode: ScoredNode = Node(board, currentPlayer, None).calculate(lookahead.getOrElse(5))
        val board2 = scoredNode.node.board
        currentPlayer = nextPlayer(board2, currentPlayer)
        board = board2
        System.out.println("The AI made move "+ scoredNode.node.move)
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
    System.out.println("Game over")
    val winner = board.calculateWinner()
    System.out.println("Winner is " + winner._1+" The Final Score is "+winner._2+":"+winner._3)
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
