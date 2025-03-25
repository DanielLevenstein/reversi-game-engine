package org.esotericcode.reversi.gameengine.reversigameengine.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoLoader implements CommandLineRunner {
    private final GameBoardRepository repository;

    @Autowired
    public DemoLoader(GameBoardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        // Create an 8x8 array with only middle spaces occupied.
        var gameBoardVals = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 3 && j == 3) {
                    gameBoardVals[i][j] = 'X';
                } else if (i == 3 && j == 4) {
                    gameBoardVals[i][j] = 'O';
                } else if (i == 4 && j == 3) {
                    gameBoardVals[i][j] = 'O';
                } else if (i == 4 && j == 4) {
                    gameBoardVals[i][j] = 'X';
                } else {
                    gameBoardVals[i][j] = ' ';
                }
            }
        }
        this.repository.save(new ReversiBoard(gameBoardVals));
    }
}
