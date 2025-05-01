package org.esotericcode.reversi.gameengine.reversigameengine.model

import org.esotericcode.reversi.gameengine.reversigameengine.model.ImmutableReversiBoard.{getPieceAlgebra, isOpponent, onBoard}
object ImmutableReversiBoard {

  private def onBoard(x: Int, y: Int) = {
    x >= 0 && x < 8 && y >= 0 && y < 8
  }

  def getPieceAlgebra(letterIndex: Int, numIndex: Int): String = {
    val invertedNum = 8 - numIndex
    ('A' + letterIndex).asInstanceOf[Char].toString + ( '0' + invertedNum).asInstanceOf[Char].toString
  }
  def isOpponent(neighborColor: Char, player: Char): Boolean = neighborColor != player && neighborColor != ' '
}

class ImmutableReversiBoard(val gameBoard: String) {

  def getPiece(letterIndex: Int, numIndex: Int): Char = {
    val charIndex = (numIndex * 9) + letterIndex
    gameBoard.charAt(charIndex)
  }

  def setPiece(boardString: StringBuilder, letterIndex: Int, numIndex: Int, player: Char): StringBuilder = {
    val charIndex = (numIndex * 9) + letterIndex
    boardString.setCharAt(charIndex, player)
  }


  def isEmptySpace(ix: Int, iy: Int): Boolean = {
    this.getPiece(ix, iy) == ' '
  }

  def isValidMove(algebraicNotation: String, player: Char): Boolean = {
    isValidMove(algebraicNotation.charAt(0) - 'A', 8 - (algebraicNotation.charAt(1) - '0'), player)
  }
  private def isValidMove(ix: Int, iy:Int, player: Char): Boolean = {

    if (!this.isEmptySpace(ix, iy)) {
      return false
    }
    // Check 9 surrounding spaces for token belonging to other player.
    val neighbor = for {
      x <- (ix - 1) to (ix + 1)
      y <- (iy - 1) to (iy + 1)
      if x >= 0 && x < 8 && y >= 0 && y < 8 && (x != ix || y != iy)
    } yield {
      (x, y)
    }

    var isMove = false
    neighbor.iterator.foreach { case (x, y) =>
      var neighborColor = this.getPiece(x, y)
      if (isOpponent(neighborColor, player)) {
        val xOffset = x - ix
        val yOffset = y - iy
        if(isRowBounded(player, x, y, xOffset, yOffset)) {
          isMove = true
        }
      }
    }
    isMove
  }

  private def isRowBounded(player: Char, x: Int, y: Int, xOffset: Int, yOffset: Int): Boolean = {
    var xVal = x
    var yVal = y
    var currentColor = this.getPiece(xVal, yVal);
    while (onBoard(xVal, yVal) && isOpponent(currentColor, player)) {
      xVal = xVal + xOffset
      yVal = yVal + yOffset
      currentColor = this.getPiece(xVal, yVal)
    }
    currentColor == player
  }

  private def flipRow(boardString: StringBuilder, player: Char, x: Int, y: Int, xOffset: Int, yOffset: Int): StringBuilder = {
    var xVal = x
    var yVal = y
    var currentColor = this.getPiece(xVal, yVal);
    this.setPiece(boardString, x, y, player)
    while (onBoard(xVal, yVal) && isOpponent(currentColor, player)) {
      xVal = xVal + xOffset
      yVal = yVal + yOffset
      currentColor = this.getPiece(xVal, yVal)
      this.setPiece(boardString, xVal, yVal, player)
    }
    currentColor == player
    boardString
  }


  def getValidMoves(player: Char): Seq[String] = {
    val validMoves = for {
      i <- 0 to 7
      j <- 0 to 7
      if isValidMove(i, j, player)
    }
    yield { getPieceAlgebra(i, j)}
    validMoves
  }


  def makeMove(algebra: String, player: Char): ImmutableReversiBoard = {
    val ix = algebra.charAt(0) - 'A'
    val iy = 8 - (algebra.charAt(1) - '0')
    var boardStr2: StringBuilder = new StringBuilder(gameBoard);

    if(!isValidMove(getPieceAlgebra(ix, iy), player)) {
      return null
    }
    setPiece(boardStr2, ix, iy, player)
    // Check 9 surrounding spaces for token belonging to other player.
    val neighbor = for {
      x <- (ix - 1) to (ix + 1)
      y <- (iy - 1) to (iy + 1)
      if x >= 0 && x < 8 && y >= 0 && y < 8 && (x != ix || y != iy)
    } yield {
      (x, y)
    }

    neighbor.iterator.foreach { case (x, y) =>
      val neighborColor = this.getPiece(x, y)
      if (isOpponent(neighborColor, player)) {
        val xOffset = x - ix
        val yOffset = y - iy

        if(isRowBounded(player, x, y, xOffset, yOffset)) {
          boardStr2 = flipRow(boardStr2, player, x, y, xOffset, yOffset);
        }
      }
    }
    new ImmutableReversiBoard(boardStr2.toString)
  }

  override def toString: String = this.gameBoard

  def prettyPrint: String = {
    val header = "_-A-B-C-D-E-F-G-H-"
    var row = 8
    val prettyBoard = new StringBuilder(header + "\n")
    for (i <- 0 until 8) {
      prettyBoard.append(row).append("|")
      for (j <- 0 until 8) {
        prettyBoard.append(this.getPiece(j, i)).append("|")
      }
      prettyBoard.append("\n")
      row -= 1
    }
    prettyBoard.append(header).append("\n")
    System.out.println(prettyBoard)
    prettyBoard.toString
  }
}
