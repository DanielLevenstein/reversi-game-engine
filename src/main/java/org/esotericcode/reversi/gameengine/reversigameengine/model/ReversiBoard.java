package org.esotericcode.reversi.gameengine.reversigameengine.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import java.util.Arrays;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ReversiBoard {
    private @Id @GeneratedValue Long id;
    @Lob
    private String gameBoardVals;

    public char[][] getGameBoardValsAsArray() {
        return Arrays.stream(gameBoardVals.split(";"))
                     .map(String::toCharArray)
                     .toArray(char[][]::new);
    }

    public void setGameBoardValsAsArray(char[][] gameBoardArray) {
        this.gameBoardVals = Arrays.stream(gameBoardArray)
                                   .map(String::new)
                                   .reduce((row1, row2) -> row1 + "\n" + row2)
                                   .orElse("");
    }

    public String getPieceAt(int x, int y) {
        return this.gameBoardVals.split("\n")[x].charAt(y) + "";
    }
    public ReversiBoard() {}

    public ReversiBoard(char [][] gameBoardVals) {
        this.setGameBoardValsAsArray(gameBoardVals);
        System.out.println(this.toString());
    }

    public String toString() {
        String header = "-_-_-_-_-_-_-_-_-";
        StringBuilder prettyBoard = new StringBuilder(header + "\n");
        for(int i = 0; i < 8; i++) {
            prettyBoard.append("|");
            for(int j = 0; j < 8; j++) {
                prettyBoard.append(this.getPieceAt(i,j)).append("|");
            }
            prettyBoard.append("\n");
        }
        prettyBoard.append(header).append("\n");
        return prettyBoard.toString();
    }
}
