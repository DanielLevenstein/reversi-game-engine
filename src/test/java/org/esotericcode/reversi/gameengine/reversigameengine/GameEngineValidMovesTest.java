package org.esotericcode.reversi.gameengine.reversigameengine;

import org.esotericcode.reversi.gameengine.reversigameengine.model.ReversiBoard;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameEngineValidMovesTest {
    @Test
    void testInvalidMovesEdgeConditions(){
        ReversiBoard board = new ReversiBoard();
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
        ReversiBoard board = new ReversiBoard();

        assertTrue(board.isValidMove("E3", 'X'));
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
        ReversiBoard board = new ReversiBoard();

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
        ReversiBoard board = new ReversiBoard();

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
        ReversiBoard board = new ReversiBoard();

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

}
