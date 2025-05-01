package org.esotericcode.reversi.gameengine.reversigameengine;

import org.esotericcode.reversi.gameengine.reversigameengine.model.ImmutableReversiBoard;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineGameEngine{

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--command-line")) {
            commandLineGame(args);
        }
    }

    public static void commandLineGame(String[] args) {
        char currentPlayer = 'X';
        ImmutableReversiBoard board = ImmutableReversiBoard.getEmptyBoard();
        while (true) {
            System.out.println("CurrentPlayer=" + currentPlayer);
            board.prettyPrint();

            if(currentPlayer == 'O' && Arrays.stream(args).toList().contains("--ai-player")) { // TODO: implement AI

            } else {
                System.out.println("Enter move in algebraic notation for player " + currentPlayer + ": ");
                String algebraicNotation = System.console().readLine();
                if (algebraicNotation.equals("q")) break;
                ImmutableReversiBoard board2 = board.makeMove(algebraicNotation, currentPlayer);
                char opponent = ImmutableReversiBoard.getOpponent(currentPlayer);
                if (board2 != null && !board2.getValidMoves(opponent).isEmpty()) {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    board = board2;
                } else {
                    System.out.println("Enter a valid move or enter 'q' to quit: " + algebraicNotation);
                }
            }
        }
    }
}
