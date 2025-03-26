package org.esotericcode.reversi.gameengine.reversigameengine.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import java.util.Arrays;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Data
@Document
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

    private char getPiece(int letterIndex, int numIndex) {

        int charIndex = (numIndex * 9) + letterIndex;
        return this.gameBoardStr.charAt(charIndex);
    }

    public char getPiece(String algebraicNotation) {
        int letterIndex = algebraicNotation.charAt(0) - 'A';
        int numberIndex = 8 - (algebraicNotation.charAt(1) - '0');
        return getPiece(letterIndex, numberIndex);
    }

    private void setPiece(int letterIndex, int numIndex, char piece) {
        // Calculate the index of the char to replace
        int charIndex = (numIndex * 9) + letterIndex;
        var sb = new StringBuilder(this.gameBoardStr);
        sb.setCharAt(charIndex, piece);
        this.gameBoardStr = sb.toString();
    }

    public void setPiece(String algebraicNotation, char piece) {
        int letter = algebraicNotation.charAt(0) - 'A';
        int number = 8 - (algebraicNotation.charAt(1) - '0');
        setPiece(letter, number, piece);
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

    public String prettyPrint() {
        String header = "_-A-B-C-D-E-F-G-H-";
        int row = 8;
        StringBuilder prettyBoard = new StringBuilder(header + "\n");
        for(int i = 0; i < 8; i++) {
            prettyBoard.append(row).append("|");
            for(int j = 0; j < 8; j++) {
                prettyBoard.append(this.getPiece(j, i)).append("|");
            }
            prettyBoard.append("\n");
            row --;
        }
        prettyBoard.append(header).append("\n");
        System.out.println(prettyBoard);
        return prettyBoard.toString();
    }

    public String toString() {
        return this.gameBoardStr;
    }
}
