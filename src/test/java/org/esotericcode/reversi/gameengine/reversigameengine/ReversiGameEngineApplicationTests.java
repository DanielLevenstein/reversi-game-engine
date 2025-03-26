package org.esotericcode.reversi.gameengine.reversigameengine;

import org.esotericcode.reversi.gameengine.reversigameengine.model.ReversiBoard;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReversiGameEngineApplicationTests {

    @Test
    void testEmptyBoard() {
        ReversiBoard board = new ReversiBoard();
        assertEquals('X', board.getPiece(3,3));
        assertEquals('O', board.getPiece(3,4));
        assertEquals('O', board.getPiece(4,3));
        assertEquals('X', board.getPiece(4,4));
        assert(board.toString().contains("|X|O|"));
        assert(board.toString().contains("|O|X|"));
        System.out.println(board);
    }
    @Test
    void testEmptyBoardToString() {
        ReversiBoard board = new ReversiBoard();
        String[] boardRowsStr = board.toString().split("\n");
        assertEquals("-_-_-_-_-_-_-_-_-",boardRowsStr[0]);
        assertEquals("| | | | | | | | |",boardRowsStr[1]);
        assertEquals("| | | | | | | | |",boardRowsStr[2]);
        assertEquals("| | | | | | | | |",boardRowsStr[3]);
        assertEquals("| | | |X|O| | | |",boardRowsStr[4]);
        assertEquals("| | | |O|X| | | |",boardRowsStr[5]);
        assertEquals("| | | | | | | | |",boardRowsStr[6]);
        assertEquals("| | | | | | | | |",boardRowsStr[7]);
        assertEquals("| | | | | | | | |",boardRowsStr[8]);
        assertEquals("-_-_-_-_-_-_-_-_-",boardRowsStr[9]);
    }


    @Test
    void testSetPiece(){
        ReversiBoard board = new ReversiBoard();
        board.setPiece(3,5,'X');
        assertEquals('X', board.getPiece(3,5));

        String[] boardRowsStr = board.toString().split("\n");
        assertEquals("-_-_-_-_-_-_-_-_-",boardRowsStr[0]);
        assertEquals("| | | | | | | | |",boardRowsStr[1]);
        assertEquals("| | | | | | | | |",boardRowsStr[2]);
        assertEquals("| | | | | | | | |",boardRowsStr[3]);
        assertEquals("| | | |X|O|X| | |",boardRowsStr[4]);
        assertEquals("| | | |O|X| | | |",boardRowsStr[5]);
        assertEquals("| | | | | | | | |",boardRowsStr[6]);
        assertEquals("| | | | | | | | |",boardRowsStr[7]);
        assertEquals("| | | | | | | | |",boardRowsStr[8]);
        assertEquals("-_-_-_-_-_-_-_-_-",boardRowsStr[9]);
    }
}
