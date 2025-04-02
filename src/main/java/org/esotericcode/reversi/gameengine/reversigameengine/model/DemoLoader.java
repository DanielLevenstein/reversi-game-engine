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
    public void run(String... strings){
        this.repository.save(new ReversiBoard(ReversiGameEngineBoard.getEmptyBoard()));
    }
}
