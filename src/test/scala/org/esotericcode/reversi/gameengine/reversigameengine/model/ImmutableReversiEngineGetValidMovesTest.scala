package org.esotericcode.reversi.gameengine.reversigameengine.model

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class ImmutableReversiEngineGetValidMovesTest {
  @Test
  def testGetValidMovesAtGameStart(): Unit = {
    val board = ImmutableReversiBoard.getEmptyBoard
    val validMoves = board.getValidMoves('X')
    assertTrue(validMoves.contains("E3"))
    assertTrue(validMoves.contains("F4"))
    assertTrue(validMoves.contains("C5"))
    assertTrue(validMoves.contains("D6"))
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
  def testLastMove(): Unit = {
    val board = new ImmutableReversiBoard("XOOOOOOO\nOXOXOXXO\nOOXOXXXO\nOOOXXXOO\nOOOOXOXO\nOOOOOXOO\nOOOOOOOO\nOOOOOOO \n")
    val validMoves = board.getValidMoves('X')
    assertTrue(validMoves.contains("H1"), "X should be able to make the last move")
  }

  @Test
  def testEarlyGameOver(): Unit = {
    val board = new ImmutableReversiBoard("XOOOOOOO\nOXOXOXXO\nOOXOXXXO\nOOOXXXOO\nOOOOXOXO\nOOOOOXOO\nOOOOOOXO\nOOOOOOO \n")
    val validMoves = board.getValidMoves('O')
    assertTrue(validMoves.isEmpty, "Valid moves for O should be empty")
    assertTrue(board.isGameOver, "Game should be over")
    val winner = board.calculateWinner()
    assertEquals(winner._1, 'O', "Winner should be O")
  }
}
