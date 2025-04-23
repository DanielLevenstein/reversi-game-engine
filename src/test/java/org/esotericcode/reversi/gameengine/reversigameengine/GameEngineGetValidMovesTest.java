package org.esotericcode.reversi.gameengine.reversigameengine;

import org.esotericcode.reversi.gameengine.reversigameengine.model.ImmutableReversiBoard;
import org.esotericcode.reversi.gameengine.reversigameengine.model.ReversiGameEngineBoard;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameEngineGetValidMovesTest {

    @Test
    void testGetValidMovesAtGameStart(){
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();

        Set<String> validMoves = board.getValidMoves('X');

        assertTrue(validMoves.contains("E3"));
        assertTrue(validMoves.contains("F4"));
        assertTrue(validMoves.contains("C5"));
        assertTrue(validMoves.contains("D6"));
        ImmutableReversiBoard immutableReversiBoard = new ImmutableReversiBoard(board.getGameBoardStr());
        var immutability = immutableReversiBoard.getValidMoves('X').toList();
        System.out.println(immutability);
        assertTrue(immutability.contains("E3"));
        assertTrue(immutability.contains("F4"));
        assertTrue(immutability.contains("C5"));
        assertTrue(immutability.contains("D6"));

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
}
