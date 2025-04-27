package org.esotericcode.reversi.gameengine.reversigameengine.model

import org.esotericcode.reversi.gameengine.reversigameengine.model.ImmutableReversiBoard.{getPieceAlgebra, isOpponent, onBoard};
object ImmutableReversiBoard {

  private def onBoard(x: Int, y: Int) = {
    x >= 0 && x < 8 && y >= 0 && y < 8
  }

  def getPieceAlgebra(letterIndex: Int, numIndex: Int): String = {
    val invertedNum = 8 - numIndex;
    ('A' + letterIndex).asInstanceOf[Char].toString + ( '0' + invertedNum).asInstanceOf[Char].toString;
  }
  def isOpponent(neighborColor: Char, player: Char): Boolean = neighborColor != player && neighborColor != ' '
}

class ImmutableReversiBoard(val gameBoard: String) {

  def getPiece(letterIndex: Int, numIndex: Int): Char = {
    val charIndex = (numIndex * 9) + letterIndex;
    gameBoard.charAt(charIndex);
  }

  def isEmptySpace(ix: Int, iy: Int): Boolean = {
    this.getPiece(ix, iy) == ' ';
  }

  def isValidMove(algebraicNotation: String, player: Char): Boolean = {
    isValidMove(algebraicNotation.charAt(0) - 'A', 8 - (algebraicNotation.charAt(1) - '0'), player);
  }
  private def isValidMove(ix: Int, iy:Int, player: Char): Boolean = {

    if (!this.isEmptySpace(ix, iy)) {
      return false;
    }
    // Check 9 surrounding spaces for token belonging to other player.
    val neighbor = for {
      x <- (ix - 1) to (ix + 1)
      y <- (iy - 1) to (iy + 1)
      if x >= 0 && x < 8 && y >= 0 && y < 8 && (x != ix || y != iy)
    } yield {
      (x, y)
    }

    var isMove = false;
    neighbor.iterator.foreach { case (x, y) =>
      var neighborColor = this.getPiece(x, y);
      if (isOpponent(neighborColor, player)) {
        val xOffset = x - ix;
        val yOffset = y - iy;
        var xVal = x;
        var yVal = y;
        while (onBoard(xVal, yVal) && isOpponent(neighborColor, player)) {
          xVal = xVal + xOffset;
          yVal = yVal + yOffset;
          neighborColor = this.getPiece(xVal, yVal)
        }
        if (neighborColor == player) {
          isMove = true;
        }
      }
    }
    isMove;
  }

  def getValidMoves(player: Char): Seq[String] = {
    val validMoves = for {
      i <- 0 to 7;
      j <- 0 to 7;
      if (isValidMove(i, j, player))
    }
    yield { getPieceAlgebra(i, j)}
    validMoves;
  }
}
