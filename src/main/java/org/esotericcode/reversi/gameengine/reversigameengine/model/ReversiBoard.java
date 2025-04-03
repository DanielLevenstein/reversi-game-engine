package org.esotericcode.reversi.gameengine.reversigameengine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data

public class ReversiBoard {
    private @Id
    @GeneratedValue Long id;

    @Setter
    @Getter
    @Lob
    private String gameBoardStr;

    public ReversiBoard(String boardStr){
        this.gameBoardStr = boardStr;
    }

    public ReversiBoard() {

    }

    public ReversiGameEngineBoard getBoard() {
        return gameBoardStr == null ? null : new ReversiGameEngineBoard(gameBoardStr);
    }
}
