package org.esotericcode.reversi.gameengine.reversigameengine;

import org.esotericcode.reversi.gameengine.reversigameengine.model.GameBoardRepository;
import org.esotericcode.reversi.gameengine.reversigameengine.model.ReversiBoard;
import org.esotericcode.reversi.gameengine.reversigameengine.model.ReversiGameEngineBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class ReversiGameEngineApplication implements CommandLineRunner {

    public final GameBoardRepository repository;

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--command-line")) {
            commandLineGame(args);
        } else {
            SpringApplication.run(ReversiGameEngineApplication.class, args);
        }
    }

    public static void commandLineGame(String[] args) {
        char currentPlayer = 'X';
        ReversiGameEngineBoard board = new ReversiGameEngineBoard();
        while (true) {
            System.out.println("CurrentPlayer=" + currentPlayer);
            board.prettyPrint();
            System.out.println("Enter move in algebraic notation for player " + currentPlayer + ": ");
            String algebraicNotation = System.console().readLine();
            if (algebraicNotation.equals("q")) break;

            if (board.makeMove(algebraicNotation, currentPlayer)) {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Enter a valid move or enter 'q' to quit: " + algebraicNotation);
            }
        }
    }

    public ReversiGameEngineApplication(GameBoardRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        this.repository.save(new ReversiBoard(ReversiGameEngineBoard.getEmptyBoard()));
    }
}
