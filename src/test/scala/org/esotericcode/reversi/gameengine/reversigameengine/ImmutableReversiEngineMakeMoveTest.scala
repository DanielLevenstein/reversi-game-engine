package org.esotericcode.reversi.gameengine.reversigameengine

class ImmutableReversiEngineMakeMoveTest {

  import org.esotericcode.reversi.gameengine.reversigameengine.model.ImmutableReversiBoard
  import org.junit.jupiter.api.Test
  import org.junit.jupiter.api.Assertions._
  import org.junit.jupiter.api.Assertions.assertEquals

    @Test
    def testInvalidMovesEdgeConditions(): Unit = {
      val board = ImmutableReversiBoard.getEmptyBoard
      assertNull(board.makeMove("A8", 'X'))
      assertNull(board.makeMove("A1", 'X'))
      assertNull(board.makeMove("H8", 'X'))
      assertNull(board.makeMove("H1", 'X'))
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
}
