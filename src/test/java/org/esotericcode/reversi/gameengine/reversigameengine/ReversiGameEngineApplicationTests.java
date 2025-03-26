package org.esotericcode.reversi.gameengine.reversigameengine;

import org.esotericcode.reversi.gameengine.reversigameengine.model.ReversiBoard;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReversiGameEngineApplicationTests {

    @Test
    void testEmptyBoardToString() {
        ReversiBoard board = new ReversiBoard();
        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |X|O| | | |",boardRowsStr[4]);
        assertEquals("4| | | |O|X| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testSetPieceD6(){
        ReversiBoard board = new ReversiBoard();
        board.setPiece("D6",'O');
        assertEquals('O', board.getPiece("D6"));

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | |O| | | | |",boardRowsStr[3]);
        assertEquals("5| | | |X|O| | | |",boardRowsStr[4]);
        assertEquals("4| | | |O|X| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testSetPieceF5(){
        ReversiBoard board = new ReversiBoard();
        board.setPiece("F5",'X');
        assertEquals('X', board.getPiece("F5"));

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |X|O|X| | |",boardRowsStr[4]);
        assertEquals("4| | | |O|X| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }


    @Test
    void testSetPieceD3(){
        ReversiBoard board = new ReversiBoard();
        board.setPiece("D3",'X');
        assertEquals('X', board.getPiece("D3"));

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| | | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |X|O| | | |",boardRowsStr[4]);
        assertEquals("4| | | |O|X| | | |",boardRowsStr[5]);
        assertEquals("3| | | |X| | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }

    @Test
    void testGetPieceAlgebraA8(){
        ReversiBoard board = new ReversiBoard();
        board.setPiece("A8",'X');
        assertEquals('X', board.getPiece("A8"));

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8|X| | | | | | | |",boardRowsStr[1]);
        assertEquals("7| | | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |X|O| | | |",boardRowsStr[4]);
        assertEquals("4| | | |O|X| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }


    @Test
    void testGetPieceAlgebraB8(){
        ReversiBoard board = new ReversiBoard();
        board.setPiece("B8",'X');
        board.setPiece("A7",'O');
        assertEquals('X', board.getPiece("B8"));

        String[] boardRowsStr = board.prettyPrint().split("\n");
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[0]);
        assertEquals("8| |X| | | | | | |",boardRowsStr[1]);
        assertEquals("7|O| | | | | | | |",boardRowsStr[2]);
        assertEquals("6| | | | | | | | |",boardRowsStr[3]);
        assertEquals("5| | | |X|O| | | |",boardRowsStr[4]);
        assertEquals("4| | | |O|X| | | |",boardRowsStr[5]);
        assertEquals("3| | | | | | | | |",boardRowsStr[6]);
        assertEquals("2| | | | | | | | |",boardRowsStr[7]);
        assertEquals("1| | | | | | | | |",boardRowsStr[8]);
        assertEquals("_-A-B-C-D-E-F-G-H-",boardRowsStr[9]);
    }
}
