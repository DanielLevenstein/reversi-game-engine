package org.esotericcode.reversi.gameengine.reversigameengine.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import java.util.Arrays;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
//@Document
public class ReversiBoard {
    private @Id @GeneratedValue Long id;

    @Setter
    @Getter
    @Lob
    private String gameBoardStr;

    public char[][] getGameBoardAsArray() {
        return Arrays.stream(gameBoardStr.split(";"))
                     .map(String::toCharArray)
                     .toArray(char[][]::new);
    }

    public void setGameBoardAsArray(char[][] gameBoardArray) {
        this.gameBoardStr = Arrays.stream(gameBoardArray)
                                   .map(String::new)
                                   .reduce((row1, row2) -> row1 + "\n" + row2)
                                   .orElse("");
    }

    public char getPiece(int x, int y) {

        int charIndex = (x * 9) + y;
        return this.gameBoardStr.charAt(charIndex);
    }

    public void setPiece(int x, int y, char piece) {
        // Calculate the index of the char to replace
        int charIndex = (x * 9) + y;
//        this.gameBoardStr = this.gameBoardStr.substring(0, charIndex) + "X" + this.gameBoardStr.substring(charIndex + 1);
        var sb = new StringBuilder(this.gameBoardStr);
        sb.setCharAt(charIndex, piece);
        this.gameBoardStr = sb.toString();
    }

    public ReversiBoard() {
        this.gameBoardStr = this.getEmptyBoard();
    }

    public ReversiBoard(char [][] gameBoardArray) {
        this.setGameBoardAsArray(gameBoardArray);
    }

    public ReversiBoard(String gameBoardStr) {
        this.gameBoardStr = gameBoardStr;
    }

    public String getEmptyBoard() {
        StringBuilder emptyBoard = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(i == 3 && j == 3) {
                    emptyBoard.append("X");
                } else if(i == 3 && j == 4) {
                    emptyBoard.append("O");
                } else if(i == 4 && j == 3) {
                    emptyBoard.append("O");
                } else if(i == 4 && j == 4) {
                    emptyBoard.append("X");
                } else {
                    emptyBoard.append(" ");
                }
            }
            emptyBoard.append("\n");
        }
        return emptyBoard.toString();
    }

    public String toString() {
        String header = "-_-_-_-_-_-_-_-_-";
        StringBuilder prettyBoard = new StringBuilder(header + "\n");
        for(int i = 0; i < 8; i++) {
            prettyBoard.append("|");
            for(int j = 0; j < 8; j++) {
                prettyBoard.append(this.getPiece(i,j)).append("|");
            }
            prettyBoard.append("\n");
        }
        prettyBoard.append(header).append("\n");
        return prettyBoard.toString();
    }
}
