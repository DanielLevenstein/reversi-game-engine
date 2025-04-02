package org.esotericcode.reversi.gameengine.reversigameengine;

import org.esotericcode.reversi.gameengine.reversigameengine.model.ReversiGameEngineBoard;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameEngineMakeMoveTest {

    @Test
    void testInvalidMovesEdgeConditions(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();
        assertFalse(board.makeMove("A8", 'X'));
        assertFalse(board.makeMove("A1", 'X'));
        assertFalse(board.makeMove("H8", 'X'));
        assertFalse(board.makeMove("H1", 'X'));



        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |O|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|O| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testMakeMoveE3(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        board.makeMove("E3", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |O|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|X| | | |",boardRowsStr[5]);
        assertEquals("3| | | | |X| | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testValidMoveD6(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        assertTrue(board.makeMove("D6", 'X'));
        board.setPiece("D6", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | |X| | | | |",boardRowsStr[3]);
        assertEquals("5| | | |X|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|O| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testValidMoveC5(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        assertTrue(board.makeMove("C5", 'X'));
        board.setPiece("C5", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | |X|X|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|O| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testValidMoveF4(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        assertTrue(board.makeMove("F4", 'X'));
        board.setPiece("F4", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |O|X| | | |",boardRowsStr[4]);
        assertEquals("4| | | |X|X|X| | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }
    @Test
    void testSecondMove(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        board.makeMove("F4", 'X');
        board.makeMove("F5", 'O');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |O|O|O| | |",boardRowsStr[4]);
        assertEquals("4| | | |X|X|X| | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }


    @Test
    void testMoveThatFlipsMultiplePieces(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        board.makeMove("F4", 'X');
        board.makeMove("F5", 'O');
        board.makeMove("D6", 'X');

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | |X| | | | |",boardRowsStr[3]);
        assertEquals("5| | | |X|X|O| | |",boardRowsStr[4]);
        assertEquals("4| | | |X|X|X| | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testInvalidMovesNoPieceAtEndOfRow(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();
        board.makeMove("F4", 'X');
        board.makeMove("F5", 'O');
        board.makeMove("D6", 'X');

        assertFalse(board.isValidMove("E6", 'O'));

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | |X| | | | |",boardRowsStr[3]);
        assertEquals("5| | | |X|X|O| | |",boardRowsStr[4]);
        assertEquals("4| | | |X|X|X| | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

}
