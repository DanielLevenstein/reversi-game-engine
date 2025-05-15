package  org.esotericcode.reversi.gameengine.model

import org.esotericcode.reversi.gameengine.model.ImmutableReversiBoard

class ImmutableReversiEngineMakeMoveTest {

  import org.junit.jupiter.api.Assertions._
  import org.junit.jupiter.api.Test

    @Test
    def testInvalidMovesEdgeConditions(): Unit = {
      val board = ImmutableReversiBoard.getEmptyBoard
      assertNull(board.makeMove("A8", 'X'))
      assertNull(board.makeMove("A1", 'X'))
      assertNull(board.makeMove("H8", 'X'))
      assertNull(board.makeMove("H1", 'X'))
      assertEquals("        \n        \n        \n   OX   \n   XO   \n        \n        \n        \n", board.gameBoard);
      val boardRowsStr = board.prettyPrint.split("\n")
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(0))
      assertEquals("8| | | | | | | | |", boardRowsStr(1))
      assertEquals("7| | | | | | | | |", boardRowsStr(2))
      assertEquals("6| | | | | | | | |", boardRowsStr(3))
      assertEquals("5| | | |O|X| | | |", boardRowsStr(4))
      assertEquals("4| | | |X|O| | | |", boardRowsStr(5))
      assertEquals("3| | | | | | | | |", boardRowsStr(6))
      assertEquals("2| | | | | | | | |", boardRowsStr(7))
      assertEquals("1| | | | | | | | |", boardRowsStr(8))
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(9))
    }

    @Test
    def testMakeMoveE3(): Unit = {
      val board = ImmutableReversiBoard.getEmptyBoard
      val board2 = board.makeMove("E3", 'X')
      assertEquals("        \n        \n        \n   OX   \n   XX   \n    X   \n        \n        \n", board2.gameBoard);
      val boardRowsStr = board2.prettyPrint.split("\n")
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(0))
      assertEquals("8| | | | | | | | |", boardRowsStr(1))
      assertEquals("7| | | | | | | | |", boardRowsStr(2))
      assertEquals("6| | | | | | | | |", boardRowsStr(3))
      assertEquals("5| | | |O|X| | | |", boardRowsStr(4))
      assertEquals("4| | | |X|X| | | |", boardRowsStr(5))
      assertEquals("3| | | | |X| | | |", boardRowsStr(6))
      assertEquals("2| | | | | | | | |", boardRowsStr(7))
      assertEquals("1| | | | | | | | |", boardRowsStr(8))
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(9))
    }

    @Test
    def testValidMoveD6(): Unit = {
      val board = ImmutableReversiBoard.getEmptyBoard
      assertNotNull(board.makeMove("D6", 'X'))
      val board2 =  board.makeMove("D6", 'X')
      assertEquals("        \n        \n   X    \n   XX   \n   XO   \n        \n        \n        \n", board2.gameBoard);
      val boardRowsStr = board2.prettyPrint.split("\n")
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(0))
      assertEquals("8| | | | | | | | |", boardRowsStr(1))
      assertEquals("7| | | | | | | | |", boardRowsStr(2))
      assertEquals("6| | | |X| | | | |", boardRowsStr(3))
      assertEquals("5| | | |X|X| | | |", boardRowsStr(4))
      assertEquals("4| | | |X|O| | | |", boardRowsStr(5))
      assertEquals("3| | | | | | | | |", boardRowsStr(6))
      assertEquals("2| | | | | | | | |", boardRowsStr(7))
      assertEquals("1| | | | | | | | |", boardRowsStr(8))
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(9))
    }

    @Test
    def testValidMoveC5(): Unit = {
      val board = ImmutableReversiBoard.getEmptyBoard
      assertNotNull(board.makeMove("C5", 'X'))
      val board2 =  board.makeMove("C5", 'X')
      assertEquals("        \n        \n        \n  XXX   \n   XO   \n        \n        \n        \n", board2.gameBoard);
      val boardRowsStr = board2.prettyPrint.split("\n")
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(0))
      assertEquals("8| | | | | | | | |", boardRowsStr(1))
      assertEquals("7| | | | | | | | |", boardRowsStr(2))
      assertEquals("6| | | | | | | | |", boardRowsStr(3))
      assertEquals("5| | |X|X|X| | | |", boardRowsStr(4))
      assertEquals("4| | | |X|O| | | |", boardRowsStr(5))
      assertEquals("3| | | | | | | | |", boardRowsStr(6))
      assertEquals("2| | | | | | | | |", boardRowsStr(7))
      assertEquals("1| | | | | | | | |", boardRowsStr(8))
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(9))
    }

    @Test
    def testValidMoveF4(): Unit = {
      val board = ImmutableReversiBoard.getEmptyBoard
      assertNotNull(board.makeMove("F4", 'X'))
      val board2 = board.makeMove("F4", 'X')
      assertEquals("        \n        \n        \n   OX   \n   XXX  \n        \n        \n        \n", board2.gameBoard);
      val boardRowsStr = board2.prettyPrint.split("\n")
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(0))
      assertEquals("8| | | | | | | | |", boardRowsStr(1))
      assertEquals("7| | | | | | | | |", boardRowsStr(2))
      assertEquals("6| | | | | | | | |", boardRowsStr(3))
      assertEquals("5| | | |O|X| | | |", boardRowsStr(4))
      assertEquals("4| | | |X|X|X| | |", boardRowsStr(5))
      assertEquals("3| | | | | | | | |", boardRowsStr(6))
      assertEquals("2| | | | | | | | |", boardRowsStr(7))
      assertEquals("1| | | | | | | | |", boardRowsStr(8))
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(9))
    }

    @Test
    def testSecondMove(): Unit = {
      val board = ImmutableReversiBoard.getEmptyBoard
      val board2 = board.makeMove("F4", 'X')
      val board3 = board2.makeMove("F5", 'O')
      assertEquals("        \n        \n        \n   OOO  \n   XXX  \n        \n        \n        \n", board3.gameBoard);
      val boardRowsStr = board3.prettyPrint.split("\n")
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(0))
      assertEquals("8| | | | | | | | |", boardRowsStr(1))
      assertEquals("7| | | | | | | | |", boardRowsStr(2))
      assertEquals("6| | | | | | | | |", boardRowsStr(3))
      assertEquals("5| | | |O|O|O| | |", boardRowsStr(4))
      assertEquals("4| | | |X|X|X| | |", boardRowsStr(5))
      assertEquals("3| | | | | | | | |", boardRowsStr(6))
      assertEquals("2| | | | | | | | |", boardRowsStr(7))
      assertEquals("1| | | | | | | | |", boardRowsStr(8))
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(9))
    }

    @Test
    def testMoveThatFlipsMultiplePieces(): Unit = {
      val board = ImmutableReversiBoard.getEmptyBoard
      val board2 = board.makeMove("F4", 'X')
      val board3 = board2.makeMove("F5", 'O')
      val board4 = board3.makeMove("D6", 'X')
      assertEquals("        \n        \n   X    \n   XXO  \n   XXX  \n        \n        \n        \n", board4.gameBoard);
      val boardRowsStr = board4.prettyPrint.split("\n")
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(0))
      assertEquals("8| | | | | | | | |", boardRowsStr(1))
      assertEquals("7| | | | | | | | |", boardRowsStr(2))
      assertEquals("6| | | |X| | | | |", boardRowsStr(3))
      assertEquals("5| | | |X|X|O| | |", boardRowsStr(4))
      assertEquals("4| | | |X|X|X| | |", boardRowsStr(5))
      assertEquals("3| | | | | | | | |", boardRowsStr(6))
      assertEquals("2| | | | | | | | |", boardRowsStr(7))
      assertEquals("1| | | | | | | | |", boardRowsStr(8))
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(9))
    }

    @Test
    def testInvalidMovesNoPieceAtEndOfRow(): Unit = {
      val board = ImmutableReversiBoard.getEmptyBoard
      val board2 = board.makeMove("F4", 'X')
      val board3 = board2.makeMove("F5", 'O')
      val board4 = board3.makeMove("D6", 'X')
      assertFalse(board4.isValidMove("E6", 'O'))
      assertEquals("        \n        \n   X    \n   XXO  \n   XXX  \n        \n        \n        \n", board4.gameBoard);
      val boardRowsStr = board4.prettyPrint.split("\n")
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(0))
      assertEquals("8| | | | | | | | |", boardRowsStr(1))
      assertEquals("7| | | | | | | | |", boardRowsStr(2))
      assertEquals("6| | | |X| | | | |", boardRowsStr(3))
      assertEquals("5| | | |X|X|O| | |", boardRowsStr(4))
      assertEquals("4| | | |X|X|X| | |", boardRowsStr(5))
      assertEquals("3| | | | | | | | |", boardRowsStr(6))
      assertEquals("2| | | | | | | | |", boardRowsStr(7))
      assertEquals("1| | | | | | | | |", boardRowsStr(8))
      assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(9))
    }


  /**
   * This test case was throwing StringIndexOutOfBoundsException
   */
  @Test
  def testPiecesInLastRow(): Unit = {
    val board = new ImmutableReversiBoard("        \n        \nX  O    \nXXOOO   \nXXXXXXX \n OOOO   \n   O    \n        \n")
    val boardRowsStr = board.prettyPrint.split("\n")
    assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(0))
    assertEquals("8| | | | | | | | |", boardRowsStr(1))
    assertEquals("7| | | | | | | | |", boardRowsStr(2))
    assertEquals("6|X| | |O| | | | |", boardRowsStr(3))
    assertEquals("5|X|X|O|O|O| | | |", boardRowsStr(4))
    assertEquals("4|X|X|X|X|X|X|X| |", boardRowsStr(5))
    assertEquals("3| |O|O|O|O| | | |", boardRowsStr(6))
    assertEquals("2| | | |O| | | | |", boardRowsStr(7))
    assertEquals("1| | | | | | | | |", boardRowsStr(8))
    assertEquals("_-A-B-C-D-E-F-G-H-", boardRowsStr(9))
  }
}
