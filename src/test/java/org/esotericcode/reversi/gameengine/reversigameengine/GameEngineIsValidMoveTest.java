package org.esotericcode.reversi.gameengine.reversigameengine;

import org.esotericcode.reversi.gameengine.reversigameengine.model.ImmutableReversiBoard;
import org.esotericcode.reversi.gameengine.reversigameengine.model.ReversiGameEngineBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameEngineIsValidMoveTest {
    @Test
    void testInvalidMovesEdgeConditions(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();
        assertFalse(board.isValidMove("A8", 'X'));
        assertFalse(board.isValidMove("A1", 'X'));
        assertFalse(board.isValidMove("H8", 'X'));
        assertFalse(board.isValidMove("H1", 'X'));

        board.setPiece("A8", 'X');
        board.setPiece("A1", 'X');
        board.setPiece("H8", 'X');
        board.setPiece("H1", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8|X| | | | | | |X|",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |O|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|O| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1|X| | | | | | |X|",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testValidMoveE3(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        ImmutableReversiBoard immutableReversiBoard = new ImmutableReversiBoard(board.getGameBoardStr());
        assertTrue(board.isValidMove("E3", 'X'));
        assertTrue(immutableReversiBoard.isValidMove("E3", 'X'));

        board.setPiece("E3", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |O|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|O| | | |",boardRowsStr[5]);
        assertEquals("3| | | | |X| | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testValidMoveD6(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        assertTrue(board.isValidMove("D6", 'X'));
        board.setPiece("D6", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | |X| | | | |",boardRowsStr[3]);
        assertEquals("5| | | |O|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|O| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testValidMoveC5(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        assertTrue(board.isValidMove("C5", 'X'));
        board.setPiece("C5", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | |X|O|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|O| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testValidMoveF4(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        assertTrue(board.isValidMove("F4", 'X'));
        board.setPiece("F4", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |O|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|O|X| | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void ValidMoveD6() {
        String boardRowsStr =
                "        \n" +
                "        \n" +
                "        \n" +
                "  XXX   \n" +
                "   XX   \n" +
                "   OX   \n" +
                "        \n" +
                "        \n";
        ReversiGameEngineBoard board = new ReversiGameEngineBoard(boardRowsStr);

        assertTrue(board.isValidMove("D6", 'O'));
        board.makeMove("D6", 'O');
        String[] prettyBoardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",prettyBoardRowsStr[0]);
        assertEquals("8| | | | | | | | |",prettyBoardRowsStr[1]);
        assertEquals("7| | | | | | | | |",prettyBoardRowsStr[2]);
        assertEquals("6| | | |O| | | | |",prettyBoardRowsStr[3]);
        assertEquals("5| | |X|O|X| | | |",prettyBoardRowsStr[4]);
        assertEquals("4| | | |O|X| | | |",prettyBoardRowsStr[5]);
        assertEquals("3| | | |O|X| | | |",prettyBoardRowsStr[6]);
        assertEquals("2| | | | | | | | |",prettyBoardRowsStr[7]);
        assertEquals("1| | | | | | | | |",prettyBoardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",prettyBoardRowsStr[9]);
    }
}
