package org.esotericcode.reversi.gameengine.reversigameengine.model;

import org.esotericcode.reversi.gameengine.reversigameengine.ReversiGameEngineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ReversiGameEngineApplication.class);
        GameBoardRepository repository = context.getBean(GameBoardRepository.class);
        var gameID = repository.save(new ReversiBoard(ReversiGameEngineBoard.getEmptyBoard())).getId();
        repository.findById(gameID).get().getBoard().prettyPrint();
    }
}
